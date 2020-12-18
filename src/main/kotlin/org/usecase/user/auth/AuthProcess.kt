package org.usecase.user.auth

import org.usecase.app.Cast
import org.usecase.app.Facade.Env
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.exception.response.UnauthorizedException
import org.usecase.model.param.DefaultParam
import org.usecase.model.resource.User
import org.usecase.user.context.RequestContext
import org.usecase.user.mail.RecoverPasswordMail
import org.usecase.user.request.AuthRequest
import org.usecase.user.request.ChangePasswordRequest
import org.usecase.user.request.ResetPasswordRequest
import org.usecase.user.request.RecoverPasswordByMailRequest
import org.usecase.user.response.AuthResponse
import br.com.simpli.tools.SecurityUtils
import org.usecase.dao.UserDao
import org.usecase.exception.response.InternalServerErrorException
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.USER_READ_ALL
import org.usecase.user.context.Permission.Companion.USER_UPDATE_ALL
import org.usecase.user.mail.manager.MailManager
import java.util.regex.Pattern
import java.util.Date
import java.util.Calendar

/**
 * Responsible for Authentication operations
 * @author Simpli CLI generator
 */
class AuthProcess(val context: RequestContext) {
    val dao = UserDao(context.con)

    /**
     * Get the authentication information by the token
     */
    fun authenticate(param: DefaultParam): AuthResponse {
        val token = extractToken(param.authorization ?: "") ?: throw UnauthorizedException(context.lang["error.invalidToken"])
        try {
            val request = tokenToRequest(token)
            val id = getId(request)
            val user = getUser(id)

            return AuthResponse(token, user)
        } catch (e: BadRequestException) {
            throw UnauthorizedException(context.lang["error.pleaseLogin"])
        } catch (e: NotFoundException) {
            throw UnauthorizedException(context.lang["error.pleaseLogin"])
        }
    }

    /**
     * Get the authentication information according to a login attempt
     */
    fun signIn(request: AuthRequest): AuthResponse {
        try {
            val token = requestToToken(request)
            val id = getId(request)
            val user = getUser(id)

            return AuthResponse(token, user)
        } catch (e: NotFoundException) {
            throw UnauthorizedException(context.lang["error.invalidLogin"])
        }
    }

    class TokenForgottenPassword(val email: String, val date: Date = Date())

    /**
     * Send an e-mail in order to reset the password
     */
    fun recoverPasswordByMail(request: RecoverPasswordByMailRequest): Long {
        request.validate(context.lang)
        val permission = Permission(USER_READ_ALL)

        val user = request.email?.let { email ->
            dao.getIdOfUser(email, null, permission)?.let { id ->
                dao.getOne(id, permission)
            }
        } ?: throw BadRequestException(context.lang["error.emailNotFound"])

        val json = Cast.classToJson(TokenForgottenPassword("${user.email}"))
        val hash = SecurityUtils.encrypt(json, Env.ENCRYPT_HASH, encode = true, urlSafe = true) ?: throw InternalServerErrorException()

        MailManager.use(context.con) {
            RecoverPasswordMail(context.lang, user, hash).sendWithManager(this)
        }

        return 1L
    }

    /**
     * Reset the password
     */
    fun resetPassword(request: ResetPasswordRequest): String {
        request.validate(context.lang)

        val permission = Permission(USER_UPDATE_ALL)

        val token = SecurityUtils.decrypt("${request.hash}", Env.ENCRYPT_HASH, decode = true, urlSafe = true) ?: throw BadRequestException(context.lang["error.invalidToken"])

        val tokenForgottenPassword = Cast.jsonToClass(token, TokenForgottenPassword::class.java)

        val calendar = Calendar.getInstance()
        calendar.time = tokenForgottenPassword.date
        calendar.add(Calendar.DAY_OF_MONTH, Env.FORGOTTEN_PASSWORD_TOKEN_LIFE)

        // token expires after x days
        if (calendar.time.before(Date())) throw BadRequestException(context.lang["error.expiredToken"])

        request.newPassword?.also {
            dao.updateUserPassword(tokenForgottenPassword.email, it, permission)
        }

        return requestToToken(AuthRequest(tokenForgottenPassword.email, request.newPassword))
    }

    /**
     * Change the password
     */
    fun changePassword(request: ChangePasswordRequest): Long {
        val id = context.auth?.id
        val user = context.auth?.user
        val email = user?.email ?: throw BadRequestException()

        request.validate(context.lang)

        val permission = Permission(USER_READ_ALL, USER_UPDATE_ALL)

        request.newPassword?.also { newPassword ->
            request.currentPassword?.also { currentPassword ->
                val idVerify = dao.getIdOfUser(email, currentPassword, permission)
                if (id != idVerify) throw BadRequestException(context.lang["error.wrongPassword"])

                dao.updateUserPassword(email, newPassword, permission)
            }
        }

        return 1L
    }

    /**
     * Get the ID by auth request
     */
    fun getId(request: AuthRequest): Long {
        request.validate(context.lang)

        val permission = Permission(USER_READ_ALL)

        return dao.getIdOfUser("${request.email}", "${request.senha}", permission) ?: throw NotFoundException(context.lang["error.userIdNotFound"])
    }

    /**
     * Get the user by ID
     */
    fun getUser(idUserPk: Long): User {
        val permission = Permission(USER_READ_ALL)
        return dao.getOne(idUserPk, permission) ?: throw NotFoundException(context.lang["error.userNotFound"])
    }

    companion object {
        /**
         * Transforms AuthRequest object into token string
         */
        fun requestToToken(request: AuthRequest): String {
            val empty = "invalid_token"
            return try {
                val json = Cast.classToJson(request)
                val encrypted = SecurityUtils.encrypt(json, Env.ENCRYPT_HASH) ?: empty
                val token = SecurityUtils.encode(encrypted, "UTF-8") ?: empty

                token
            } catch (e: Exception) {
                empty
            }
        }

        /**
         * Transforms token string into AuthRequest object
         */
        fun tokenToRequest(token: String): AuthRequest {
            val empty = AuthRequest(null, null)
            return try {
                val encrypted = SecurityUtils.decode(token, "UTF-8")
                val json = SecurityUtils.decrypt(encrypted ?: return empty, Env.ENCRYPT_HASH)
                val request = Cast.jsonToClass(json ?: return empty, AuthRequest::class.java)

                request
            } catch (e: Exception) {
                empty
            }
        }

        /**
         * Extracts token from the header
         */
        fun extractToken(authorization: String): String? {
            val matcher = Pattern.compile("Bearer (.*)").matcher(authorization)
            return if (matcher.find()) matcher.group(1) else null
        }
    }
}

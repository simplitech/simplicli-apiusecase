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
import java.util.regex.Pattern
import java.util.Date
import java.util.Calendar

/**
 * Responsible for Authentication operations
 * @author Simpli CLI generator
 */
class AuthProcess(val context: RequestContext) {
    val dao = AuthDao(context.con)

    /**
     * Get the authentication information by the token
     */
    fun authenticate(param: DefaultParam.Auth): AuthResponse {
        val token = extractToken(param.authorization ?: "") ?: throw UnauthorizedException(context.lang["invalid_token"])
        try {
            val request = tokenToRequest(token)
            val id = getId(request)
            val user = getUser(id)

            return AuthResponse(token, user)
        } catch (e: BadRequestException) {
            throw UnauthorizedException(context.lang.pleaseLogin())
        } catch (e: NotFoundException) {
            throw UnauthorizedException(context.lang.pleaseLogin())
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
            throw UnauthorizedException(context.lang.invalidLogin())
        }
    }

    class TokenForgottenPassword(val email: String, val date: Date = Date())

    /**
     * Send an e-mail in order to reset the password
     */
    fun recoverPasswordByMail(request: RecoverPasswordByMailRequest): Long {
        request.validate(context.lang)

        val user = dao.getUserByEmail("${request.email}") ?: throw BadRequestException(context.lang.emailNotFound())

        val json = Cast.classToJson(TokenForgottenPassword("${user.email}"))
        val encrypted = SecurityUtils.encrypt(json, Env.ENCRYPT_HASH)
        val hash = encrypted?.replace("/", "%2F") ?: "invalid_hash"

        RecoverPasswordMail(context.lang, user, hash).send()

        return 1L
    }

    /**
     * Reset the password
     */
    fun resetPassword(request: ResetPasswordRequest): String {
        request.validate(context.lang)

        val hashResolved = request.hash?.replace(" ", "+") ?: ""
        val token = SecurityUtils.decrypt(hashResolved, Env.ENCRYPT_HASH) ?: throw BadRequestException(context.lang.invalidToken())

        val tokenForgottenPassword = Cast.jsonToClass(token, TokenForgottenPassword::class.java)

        val calendar = Calendar.getInstance()
        calendar.time = tokenForgottenPassword.date
        calendar.add(Calendar.DAY_OF_MONTH, Env.FORGOTTEN_PASSWORD_TOKEN_LIFE)

        // token expires after x days
        if (calendar.time.before(Date())) throw BadRequestException(context.lang.expiredToken())

        request.newPassword?.also {
            dao.updateUserPassword(tokenForgottenPassword.email, it)
        }

        return requestToToken(AuthRequest(tokenForgottenPassword.email, request.newPassword))
    }

    /**
     * Change the password
     */
    fun changePassword(request: ChangePasswordRequest, auth: AuthResponse): Long {
        val id = auth.id
        val user = auth.user
        val email = user.email ?: throw BadRequestException()

        request.validate(context.lang)

        request.newPassword?.also { newPassword ->
            request.currentPassword?.also { currentPassword ->
                val idVerify = dao.getIdOfUser(email, currentPassword)
                if (id != idVerify) throw BadRequestException(context.lang["wrong_password"])

                dao.updateUserPassword(email, newPassword)
            }
        }

        return 1L
    }

    /**
     * Get the ID by auth request
     */
    fun getId(request: AuthRequest): Long {
        request.validate(context.lang)

        return dao.getIdOfUser("${request.email}", "${request.senha}") ?: throw NotFoundException(context.lang["user_id_not_found"])
    }

    /**
     * Get the user by ID
     */
    fun getUser(idUserPk: Long): User {
        return dao.getUser(idUserPk) ?: throw NotFoundException(context.lang["user_not_found"])
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

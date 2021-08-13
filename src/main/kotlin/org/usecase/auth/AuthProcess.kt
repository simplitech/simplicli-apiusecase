package org.usecase.auth

import org.usecase.app.Cast
import org.usecase.app.Facade.Env
import org.usecase.model.param.DefaultParam
import org.usecase.model.resource.User
import org.usecase.context.RequestContext
import org.usecase.mail.RecoverPasswordMail
import org.usecase.model.request.AuthRequest
import org.usecase.model.request.ChangePasswordRequest
import org.usecase.model.request.ResetPasswordRequest
import org.usecase.model.request.RecoverPasswordByMailRequest
import org.usecase.model.response.AuthResponse
import br.com.simpli.tools.SecurityUtils
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.usecase.context.PermissionGroup
import org.usecase.dao.*
import org.usecase.exception.response.*
import org.usecase.model.resource.Permission
import org.usecase.mail.manager.MailManager
import java.util.regex.Pattern

/**
 * Responsible for Authentication operations
 * @author Simpli CLI generator
 */
class AuthProcess(val context: RequestContext) {
    val dao = UserDao(context.con, context.permission)

    /**
     * Get the authentication information by the token
     */
    fun authenticate(param: DefaultParam): AuthResponse {
        var token = extractToken(param.authorization ?: "") ?: throw UnauthorizedException(context.lang["error.invalidToken"])
        val request = tokenToRequest(token) ?: throw UnauthorizedException(context.lang["error.invalidToken"])

        val isDirectAuth = request.options.isDirectAuth == true

        try {
            request.validate(context.lang)

            request.options.apply {
                expirationDate?.also {
                    if (LocalDateTime(it).isBefore(LocalDateTime())) {
                        throw NotAcceptableException(context.lang["expiredToken"])
                    }
                }

                isAnonymous?.also {
                    if (it) return AuthResponse(token, User(), this)
                }
            }

            val user = getUserByLoginInfo(request.email, request.senha, !isDirectAuth)

            if (request.options.isDirectAuth == true) {
                // Create another direct auth token that has the same expiration of session token
                token = createDirectAuthToken(user.id, Env.SESSION_AUTH_TOKEN_LIFE, request.options.permission)
            }

            return AuthResponse(token, user, request.options).apply { context.auth = this }
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
        request.options = AuthRequest.Options() // it can't be provided by client

        try {
            request.validate(context.lang)

            val token = requestToToken(request)
            val user = getUserByLoginInfo(request.email, request.senha)

            return AuthResponse(token, user, request.options)
        } catch (e: NotFoundException) {
            throw UnauthorizedException(context.lang["error.invalidLogin"])
        }
    }

    /**
     * Send an e-mail in order to reset the password
     */
    fun recoverPasswordByMail(request: RecoverPasswordByMailRequest): Long {
        request.validate(context.lang)

        val user = getUserByLoginInfo(request.email, null)
        val token = createDirectAuthToken(user.id, Env.FORGOTTEN_PASSWORD_TOKEN_LIFE, Permission.groupOf(Permission.USER_UPDATE_SENHA))

        MailManager.use(context.con) {
            RecoverPasswordMail(context.lang, user, token).sendWithManager(this)
        }

        return 1L
    }

    /**
     * Reset the password
     */
    fun resetPassword(request: ResetPasswordRequest): Int {
        request.validate(context.lang)

        val authRequest = tokenToRequest("${request.hash}")?.apply {
            validate(context.lang)

            options.apply {
                expirationDate?.also {
                    if (LocalDateTime(it).isBefore(LocalDateTime())) {
                        throw NotAcceptableException(context.lang["expiredToken"])
                    }
                }
            }
        } ?: throw UnauthorizedException(context.lang["error.invalidToken"])

        val isDirectAuth = authRequest.options.isDirectAuth == true

        val user = getUserByLoginInfo(authRequest.email, authRequest.senha, !isDirectAuth).apply {
            senha = request.password
        }

        return dao.update(user)
    }

    /**
     * Change the password
     */
    fun changePassword(request: ChangePasswordRequest): Int {
        request.validate(context.lang)

        val email = context.auth?.user?.email ?: throw InternalServerErrorException()

        try {
            val user = getUserByLoginInfo(email, request.currentPassword).apply {
                senha = request.newPassword
            }

            return dao.update(user)
        } catch (e: NotFoundException) {
            throw UnauthorizedException(context.lang["error.invalidLogin"])
        }
    }

    /**
     * Get user by login info
     */
    fun getUserByLoginInfo(email: String?, senha: String?, useSha2: Boolean = true): User {
        if (email == null) throw NotFoundException(context.lang["error.userNotFound"])

        return dao.getOneByLoginInfo(email, senha, useSha2)?.apply {
            permissions = UserPermissionDao(context.con, context.permission)
                    .listPermissionOfUser(id)
                    .apply {
                        forEach { permission ->
                            populatePermissions(permission)
                        }
                    }

            roles = UserRoleDao(context.con, context.permission)
                    .listRoleOfUser(id)
                    .apply {
                        forEach { role ->
                            role.permissions = RolePermissionDao(context.con, context.permission)
                                    .listPermissionOfRole(role.id)
                                    .apply {
                                        forEach { permission ->
                                            populatePermissions(permission)
                                        }
                                    }
                            role.applyScope()
                        }
                    }

            applyScope()
        } ?: throw NotFoundException(context.lang["error.userNotFound"])
    }

    private fun populatePermissions(permission: Permission, deep: Int = 5) {
        permission.permissions = PermissionPermissionDao(context.con, context.permission)
                .listPermissionChildOfPermissionParent(permission.id)
                .apply {
                    forEach {
                        if (deep > 0) populatePermissions(it, deep - 1)
                    }
                }
        permission.applyScope()
    }

    /**
     * Creates authentication token for direct links
     */
    fun createDirectAuthToken(
            idUser: Long,
            validationDays: Int = Env.DIRECT_LINK_AUTH_TOKEN_LIFE,
            tokenPermission: PermissionGroup? = null
    ): String {
        return dao.getLoginInfo(idUser)?.run {
            AuthRequest(this.first, this.second).run {
                options.apply {
                    expirationDate = LocalDate().plusDays(validationDays).toDate()
                    isDirectAuth = true
                    this.permission = tokenPermission
                }

                toToken()
            }
        } ?: throw NotFoundException()
    }

    companion object {
        /**
         * Creates anonymous authentication token
         */
        fun createAnonymousToken(
                validationDays: Int = Env.DIRECT_LINK_AUTH_TOKEN_LIFE,
                tokenPermission: PermissionGroup? = null
        ): String {
            return AuthRequest().run {
                options.apply {
                    expirationDate = LocalDate().plusDays(validationDays).toDate()
                    isAnonymous = true
                    isDirectAuth = true
                    this.permission = tokenPermission
                }

                toToken()
            }
        }

        /**
         * Transforms AuthRequest object into token string
         */
        fun requestToToken(request: AuthRequest): String {
            return try {
                Cast.classToJson(request).let { json ->
                    SecurityUtils.encrypt(json, Env.ENCRYPT_HASH)?.let { encrypted ->
                        SecurityUtils.encode(encrypted, "UTF-8")
                    }
                } ?: throw InternalServerErrorException()
            } catch (e: Exception) {
                throw InternalServerErrorException()
            }
        }

        /**
         * Transforms token string into AuthRequest object
         */
        fun tokenToRequest(token: String): AuthRequest? {
            return try {
                SecurityUtils.decode(token, "UTF-8")?.let { encrypted ->
                    SecurityUtils.decrypt(encrypted, Env.ENCRYPT_HASH)?.let { json ->
                        Cast.jsonToClass(json, AuthRequest::class.java)
                    }
                }
            } catch (e: Exception) {
                null
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

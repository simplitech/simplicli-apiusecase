package org.usecase.model.request

import io.swagger.v3.oas.annotations.media.Schema
import org.joda.time.LocalDate
import org.usecase.app.Facade.Env
import org.usecase.locale.LangDefinition
import org.usecase.auth.AuthProcess
import org.usecase.context.PermissionGroup
import org.valiktor.functions.*
import org.valiktor.validate
import java.util.*

/**
 * Authentication Request Model
 * @author Simpli CLI generator
 */
class AuthRequest(var email: String? = null, var senha: String? = null) {
    var options = Options()

    fun validate(lang: LangDefinition) = lang.handleValidation("modelAuthRequest") {
        validate(this) {
            validate(AuthRequest::email).isNotBlank().isEmail()
            validate(AuthRequest::senha).isNotBlank()
        }
    }

    @Schema(hidden = true)
    fun toToken() = AuthProcess.requestToToken(this)

    class Options {
        /**
         * Specifies permissions that this token may have
         */
        var permission: PermissionGroup? = null

        /**
         * Authentication permission until this date
         * Null value never expire
         */
        var expirationDate: Date? = LocalDate().plusDays(Env.SESSION_AUTH_TOKEN_LIFE).toDate()

        /**
         * Authentication without user login
         */
        var isAnonymous: Boolean? = null

        /**
         * Authentication by link
         */
        var isDirectAuth: Boolean? = null
    }
}

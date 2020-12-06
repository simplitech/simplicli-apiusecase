package org.usecase.user.request

import org.usecase.locale.LangDefinition
import io.swagger.v3.oas.annotations.media.Schema
import org.usecase.user.auth.AuthProcess
import org.valiktor.functions.*
import org.valiktor.validate

/**
 * Authentication Request Model
 * @author Simpli CLI generator
 */
class AuthRequest(var email: String?, var senha: String?) {
    fun validate(lang: LangDefinition) = lang.handleValidation("modelAuthRequest") {
        validate(this) {
            validate(AuthRequest::email).isNotBlank().isEmail()
            validate(AuthRequest::senha).isNotBlank()
        }
    }

    @Schema(hidden = true)
    fun toToken() = AuthProcess.requestToToken(this)
}

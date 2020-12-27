package org.usecase.model.request

import org.usecase.locale.LangDefinition
import org.valiktor.functions.*
import org.valiktor.validate

/**
 * Reset Password Request Model
 * @author Simpli CLI generator
 */
class ResetPasswordRequest(var password: String?, var hash: String?) {
    fun validate(lang: LangDefinition) = lang.handleValidation("modelResetPasswordRequest") {
        validate(this) {
            validate(ResetPasswordRequest::password).isNotBlank()
            validate(ResetPasswordRequest::hash).isNotBlank()
        }
    }
}

package org.usecase.user.request

import org.usecase.locale.LangDefinition
import org.valiktor.functions.*
import org.valiktor.validate

/**
 * Reset Password Request Model
 * @author Simpli CLI generator
 */
class ResetPasswordRequest(var newPassword: String?, var confirmPassword: String?, var hash: String?) {
    fun validate(lang: LangDefinition) = lang.handleValidation("modelResetPasswordRequest") {
        validate(this) {
            validate(ResetPasswordRequest::newPassword).isNotBlank()
            validate(ResetPasswordRequest::confirmPassword).isNotBlank()
            validate(ResetPasswordRequest::confirmPassword).isNotBlank()
            validate(ResetPasswordRequest::newPassword).isEqualTo(confirmPassword)
            validate(ResetPasswordRequest::hash).isNotBlank()
        }
    }
}

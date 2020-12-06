package org.usecase.user.request

import org.usecase.locale.LangDefinition
import org.valiktor.functions.*
import org.valiktor.validate

/**
 * Change Password Request Model
 * @author Simpli CLI generator
 */
class ChangePasswordRequest(var currentPassword: String?, var newPassword: String?, var confirmPassword: String?) {
    fun validate(lang: LangDefinition) = lang.handleValidation("modelChangePasswordRequest") {
        validate(this) {
            validate(ChangePasswordRequest::currentPassword).isNotBlank()
            validate(ChangePasswordRequest::newPassword).isNotBlank()
            validate(ChangePasswordRequest::confirmPassword).isNotBlank()
            validate(ChangePasswordRequest::newPassword).isEqualTo(confirmPassword)
        }
    }
}

package org.usecase.user.request

import org.usecase.exception.response.BadRequestException
import br.com.simpli.model.LanguageHolder
import javax.xml.bind.annotation.XmlRootElement

/**
 * Change Password Request Model
 * @author Simpli CLI generator
 */
class ChangePasswordRequest(var currentPassword: String?, var newPassword: String?, var confirmPassword: String?) {
    fun validate(lang: LanguageHolder) {
        if (currentPassword.isNullOrEmpty()) {
            throw BadRequestException(lang.cannotBeNull("Current Password"))
        }

        if (newPassword.isNullOrEmpty()) {
            throw BadRequestException(lang.cannotBeNull("New Password"))
        }

        if (confirmPassword.isNullOrEmpty()) {
            throw BadRequestException(lang.cannotBeNull("Confirm Password"))
        }

        if (newPassword != confirmPassword) {
            throw BadRequestException(lang["password_no_match"])
        }
    }
}

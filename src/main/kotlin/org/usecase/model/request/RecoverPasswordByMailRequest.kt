package org.usecase.model.request

import org.usecase.locale.LangDefinition
import org.valiktor.functions.*
import org.valiktor.validate

/**
 * Recover Password By Mail Request Model
 * @author Simpli CLI generator
 */
class RecoverPasswordByMailRequest(var email: String?) {
    fun validate(lang: LangDefinition) = lang.handleValidation("modelRecoverPasswordByMailRequest") {
        validate(this) {
            validate(RecoverPasswordByMailRequest::email).isNotBlank().isEmail()
        }
    }
}

package org.usecase.user.request

import br.com.simpli.model.LanguageHolder
import br.com.simpli.tools.Validator
import io.swagger.v3.oas.annotations.media.Schema
import javax.xml.bind.annotation.XmlRootElement
import org.usecase.user.auth.AuthProcess
import org.usecase.exception.response.BadRequestException

/**
 * Authentication Request Model
 * @author Simpli CLI generator
 */
@XmlRootElement
class AuthRequest(var email: String?, var senha: String?) {
    fun validate(lang: LanguageHolder) {
        if (email.isNullOrEmpty()) {
            throw BadRequestException(lang.cannotBeNull("Email"))
        }

        if (!Validator.isEmail(email)) {
            throw BadRequestException(lang.isNotAValidEmail("Email"))
        }

        if (senha.isNullOrEmpty()) {
            throw BadRequestException(lang.cannotBeNull("Senha"))
        }
    }

    @Schema(hidden = true)
    fun toToken() = AuthProcess.requestToToken(this)
}

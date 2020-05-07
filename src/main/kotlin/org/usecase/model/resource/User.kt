package org.usecase.model.resource

import org.usecase.exception.response.BadRequestException
import org.usecase.model.param.DefaultParam
import br.com.simpli.model.LanguageHolder
import br.com.simpli.tools.Validator
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.PathParam

/**
 * Reference model of table user
 * @author Simpli CLI generator
 */
class User {
    @Schema(required = true) var idUserPk: Long = 0

    @Schema(required = true, maxLength = 45)
    var email: String? = null

    @Schema(required = true, maxLength = 200)
    var senha: String? = null

    var id
        @Schema(hidden = true)
        get() = idUserPk
        set(value) {
            idUserPk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (email.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["User.email"]))
        }
        if (email?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["User.email"], 45))
        }
        if (email != null && !Validator.isEmail(email)) {
            throw BadRequestException(lang.isNotAValidEmail(lang["User.email"]))
        }
        if (senha.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["User.senha"]))
        }
        if (senha?.length ?: 0 > 200) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["User.senha"], 200))
        }
    }
}

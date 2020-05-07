package org.usecase.model.resource

import org.usecase.exception.response.BadRequestException
import org.usecase.model.param.DefaultParam
import br.com.simpli.model.LanguageHolder
import br.com.simpli.tools.Validator
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.PathParam

/**
 * Reference model of table conectado
 * @author Simpli CLI generator
 */
class Conectado {
    @Schema(required = true) var idConectadoPk: Long = 0

    @Schema(maxLength = 45) var titulo: String? = null

    var id
        @Schema(hidden = true)
        get() = idConectadoPk
        set(value) {
            idConectadoPk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (titulo?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Conectado.titulo"], 45))
        }
    }
}

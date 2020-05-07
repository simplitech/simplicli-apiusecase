package org.usecase.model.resource

import org.usecase.exception.response.BadRequestException
import org.usecase.model.param.DefaultParam
import br.com.simpli.model.LanguageHolder
import br.com.simpli.tools.Validator
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.PathParam

/**
 * Reference model of table extensao_do_principal
 * @author Simpli CLI generator
 */
class ExtensaoDoPrincipal {
    @Schema(required = true) var idPrincipalFk: Long = 0

    var principal: Principal? = null

    @Schema(required = true, maxLength = 45)
    var titulo: String? = null

    var id
        @Schema(hidden = true)
        get() = idPrincipalFk
        set(value) {
            idPrincipalFk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (titulo.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["ExtensaoDoPrincipal.titulo"]))
        }
        if (titulo?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["ExtensaoDoPrincipal.titulo"], 45))
        }
    }
}

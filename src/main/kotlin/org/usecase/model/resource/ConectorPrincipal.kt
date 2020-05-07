package org.usecase.model.resource

import org.usecase.exception.response.BadRequestException
import org.usecase.model.param.DefaultParam
import br.com.simpli.model.LanguageHolder
import br.com.simpli.tools.Validator
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.PathParam

/**
 * Reference model of table conector_principal
 * @author Simpli CLI generator
 */
class ConectorPrincipal {
    @Schema(required = true) var idPrincipalFk: Long = 0
    @Schema(required = true) var idConectadoFk: Long = 0

    var conectado: Conectado? = null
    var principal: Principal? = null

    @Schema(required = true, maxLength = 45)
    var titulo: String? = null

    open class RequiredPathId : DefaultParam.Auth() {
        @PathParam("id1")
        @Schema(required = true)
        var id1: Long? = null

        @PathParam("id2")
        @Schema(required = true)
        var id2: Long? = null
    }

    var id1
        @Schema(hidden = true)
        get() = idPrincipalFk
        set(value) {
            idPrincipalFk = value
        }

    var id2
        @Schema(hidden = true)
        get() = idConectadoFk
        set(value) {
            idConectadoFk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (titulo.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["ConectorPrincipal.titulo"]))
        }
        if (titulo?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["ConectorPrincipal.titulo"], 45))
        }
    }
}

package org.usecase.model.resource

import org.usecase.exception.response.BadRequestException
import org.usecase.model.param.DefaultParam
import br.com.simpli.model.LanguageHolder
import br.com.simpli.tools.Validator
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.PathParam

/**
 * Reference model of table item_do_principal
 * @author Simpli CLI generator
 */
class ItemDoPrincipal {
    @Schema(required = true) var idItemDoPrincipalPk: Long = 0

    var principal: Principal? = null

    @Schema(required = true, maxLength = 45)
    var titulo: String? = null

    var id
        @Schema(hidden = true)
        get() = idItemDoPrincipalPk
        set(value) {
            idItemDoPrincipalPk = value
        }

    var idPrincipalFk: Long
        @Schema(required = true)
        get() = principal?.idPrincipalPk ?: 0
        set(value) {
            if (value == 0L) {
                principal = null
                return
            }
            if (principal == null) {
                principal = Principal()
            }
            principal?.idPrincipalPk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (titulo.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["ItemDoPrincipal.titulo"]))
        }
        if (titulo?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["ItemDoPrincipal.titulo"], 45))
        }
        if (idPrincipalFk == 0L) {
            throw BadRequestException(lang.cannotBeNull(lang["ItemDoPrincipal.idPrincipalFk"]))
        }
    }
}

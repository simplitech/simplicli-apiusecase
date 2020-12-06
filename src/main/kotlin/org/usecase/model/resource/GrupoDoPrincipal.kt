package org.usecase.model.resource

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Reference model of table grupo_do_principal
 * @author Simpli CLI generator
 */
class GrupoDoPrincipal {
    @Schema(required = true) var idGrupoDoPrincipalPk: Long = 0

    @Schema(required = true, maxLength = 45)
    var titulo: String? = null

    var id
        @Schema(hidden = true)
        get() = idGrupoDoPrincipalPk
        set(value) {
            idGrupoDoPrincipalPk = value
        }
}

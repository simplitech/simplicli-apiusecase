package org.usecase.model.resource

import io.swagger.v3.oas.annotations.media.Schema

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
}

package org.usecase.model.resource

import io.swagger.v3.oas.annotations.media.Schema

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
}

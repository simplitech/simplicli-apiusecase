package org.usecase.model.resource

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Reference model of table tag
 * @author Simpli CLI generator
 */
class Tag {
    @Schema(required = true) var idTagPk: Long = 0

    var tagPrincipal: MutableList<Principal>? = null

    @Schema(required = true, maxLength = 45)
    var titulo: String? = null

    var id
        @Schema(hidden = true)
        get() = idTagPk
        set(value) {
            idTagPk = value
        }
}

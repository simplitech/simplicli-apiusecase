package org.usecase.model.resource

import io.swagger.v3.oas.annotations.media.Schema

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
}

package org.usecase.model.resource

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Reference model of table endereco
 * @author Simpli CLI generator
 */
class Endereco {
    @Schema(required = true) var idEnderecoPk: Long = 0

    @Schema(maxLength = 45) var cep: String? = null
    @Schema(maxLength = 45) var zipcode: String? = null
    @Schema(maxLength = 45) var rua: String? = null
    @Schema(maxLength = 45) var cidade: String? = null
    @Schema(maxLength = 45) var uf: String? = null

    var nro: Long? = null
    var latitude: Double? = null
    var longitude: Double? = null

    var id
        @Schema(hidden = true)
        get() = idEnderecoPk
        set(value) {
            idEnderecoPk = value
        }
}

package org.usecase.model.resource

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Reference model of table user
 * @author Simpli CLI generator
 */
class User {
    @Schema(required = true) var idUserPk: Long = 0

    @Schema(required = true, maxLength = 45)
    var email: String? = null

    @Schema(required = true, maxLength = 200)
    var senha: String? = null

    var id
        @Schema(hidden = true)
        get() = idUserPk
        set(value) {
            idUserPk = value
        }
}

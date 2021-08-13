package org.usecase.model.resource

import io.swagger.v3.oas.annotations.media.Schema
import org.usecase.context.PermissionGroup

/**
 * Reference model of table user
 * @author Simpli CLI generator
 */
class User : PermissionGroup() {
    var permissions: List<Permission>? = null
    var roles: List<Role>? = null

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

    fun applyScope() {
        val roles = (roles ?: emptyList())
                .map { it.scopes }
                .flatten()
                .distinct()
                .toMutableList()

        val permissions = (permissions ?: emptyList())
                .map { it.scopes }
                .flatten()
                .distinct()
                .toMutableList()

        scopes = (roles + permissions).distinct().toMutableList()
    }
}

package org.usecase.model.resource

import io.swagger.v3.oas.annotations.media.Schema
import org.usecase.context.PermissionGroup

/**
 * Reference model of table role
 *
 * @author Simpli CLI generator
 */
class Role : PermissionGroup() {
    @Schema(required = true) var idRolePk: Long = 0

    var permissions: List<Permission>? = null
    var users: List<User>? = null

    @Schema(required = true, maxLength = 127) var slug: String? = null

    @Schema(maxLength = 127) var name: String? = null
    @Schema(maxLength = 255) var description: String? = null

    @Schema(required = true) var level: Int? = null
    @Schema(required = true) var active: Boolean? = null

    var id
        @Schema(hidden = true)
        get() = idRolePk
        set(value) {
            idRolePk = value
        }

    override val scopes
        @Schema(hidden = true)
        get() = (permissions ?: emptyList())
                .map { it.scopes }
                .flatten()
                .distinct()
                .toMutableList()

    companion object {
        const val GUEST = "guest"
        const val VIEWER = "viewer"
        const val MANAGER = "manager"
        const val ADMIN = "admin"
    }
}

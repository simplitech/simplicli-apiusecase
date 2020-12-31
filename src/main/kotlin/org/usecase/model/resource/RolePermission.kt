package org.usecase.model.resource

import org.usecase.model.param.DefaultParam
import io.swagger.v3.oas.annotations.media.Schema
import javax.ws.rs.PathParam

/**
 * Reference model of table role_permission
 * @author Simpli CLI generator
 */
class RolePermission {
    var role: Role? = null
    var permission: Permission? = null

    var idRoleFk: Long
        @Schema(required = true)
        get() = role?.id ?: 0
        set(value) {
            role = if (value == 0L) { null } else {
                (role ?: Role()).apply {
                    id = value
                }
            }
        }

    var idPermissionFk: Long
        @Schema(required = true)
        get() = permission?.id ?: 0
        set(value) {
            permission = if (value == 0L) { null } else {
                (permission ?: Permission()).apply {
                    id = value
                }
            }
        }

    open class RequiredPathId : DefaultParam() {
        @PathParam("idRoleFk")
        @Schema(required = true)
        var idRoleFk: Long? = null

        @PathParam("idPermissionFk")
        @Schema(required = true)
        var idPermissionFk: Long? = null
    }
}

package org.usecase.model.resource

import org.usecase.model.param.DefaultParam
import io.swagger.v3.oas.annotations.media.Schema
import javax.ws.rs.PathParam

/**
 * Reference model of table user_permission
 * @author Simpli CLI generator
 */
class UserPermission {
    var user: User? = null
    var permission: Permission? = null

    var idUserFk: Long
        @Schema(required = true)
        get() = user?.id ?: 0
        set(value) {
            user = if (value == 0L) { null } else {
                (user ?: User()).apply {
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
        @PathParam("idUserFk")
        @Schema(required = true)
        var idUserFk: Long? = null

        @PathParam("idPermissionFk")
        @Schema(required = true)
        var idPermissionFk: Long? = null
    }
}

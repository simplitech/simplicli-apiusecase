package org.usecase.model.resource

import org.usecase.model.param.DefaultParam
import io.swagger.v3.oas.annotations.media.Schema
import javax.ws.rs.PathParam

/**
 * Reference model of table permission_permission
 * @author Simpli CLI generator
 */
class PermissionPermission {
    var permissionParent: Permission? = null
    var permissionChild: Permission? = null

    var idPermissionParentFk: Long
        @Schema(required = true)
        get() = permissionParent?.id ?: 0
        set(value) {
            permissionParent = if (value == 0L) { null } else {
                (permissionParent ?: Permission()).apply {
                    id = value
                }
            }
        }

    var idPermissionChildFk: Long
        @Schema(required = true)
        get() = permissionChild?.id ?: 0
        set(value) {
            permissionChild = if (value == 0L) { null } else {
                (permissionChild ?: Permission()).apply {
                    id = value
                }
            }
        }

    open class RequiredPathId : DefaultParam() {
        @PathParam("idPermissionParentFk")
        @Schema(required = true)
        var idPermissionParentFk: Long? = null

        @PathParam("idPermissionChildFk")
        @Schema(required = true)
        var idPermissionChildFk: Long? = null
    }
}

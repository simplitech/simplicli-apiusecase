package org.usecase.model.resource

import org.usecase.model.param.DefaultParam
import io.swagger.v3.oas.annotations.media.Schema
import javax.ws.rs.PathParam

/**
 * Reference model of table user_role
 * @author Simpli CLI generator
 */
class UserRole {
    var user: User? = null
    var role: Role? = null

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

    open class RequiredPathId : DefaultParam() {
        @PathParam("idUserFk")
        @Schema(required = true)
        var idUserFk: Long? = null

        @PathParam("idRoleFk")
        @Schema(required = true)
        var idRoleFk: Long? = null
    }
}

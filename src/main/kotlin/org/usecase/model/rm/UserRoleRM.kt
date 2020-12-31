package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission
import org.usecase.model.resource.UserRole
import java.sql.ResultSet

/**
 * Relational Mapping of UserRole from table user_role
 * @author Simpli CLI generator
 */
class UserRoleRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<UserRole>() {
    override val table = "user_role"

    val idUserFk = col("idUserFk",
            { idUserFk },
            { idUserFk = it.value() })

    val idRoleFk = col("idRoleFk",
            { idRoleFk },
            { idRoleFk = it.value() })

    fun build(rs: ResultSet) = UserRole().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<UserRole>>
        get() = permission.buildArray {
            Permission.apply {
                add(idUserFk, FULL_CONTROL, USER_ROLE_FULL_CONTROL, USER_ROLE_READ_ALL, USER_ROLE_READ_ID_USER_FK)
                add(idRoleFk, FULL_CONTROL, USER_ROLE_FULL_CONTROL, USER_ROLE_READ_ALL, USER_ROLE_READ_ID_ROLE_FK)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<UserRole>>
        get() = permission.buildArray {
            Permission.apply {
                add(idUserFk, FULL_CONTROL, USER_ROLE_FULL_CONTROL, USER_ROLE_READ_ALL, USER_ROLE_READ_ID_USER_FK)
                add(idRoleFk, FULL_CONTROL, USER_ROLE_FULL_CONTROL, USER_ROLE_READ_ALL, USER_ROLE_READ_ID_ROLE_FK)
            }
        }

    val orderMap: Map<String, VirtualColumn<UserRole>>
        get() = permission.buildMap {
            Permission.apply {
                add("idUserFk" to idUserFk, FULL_CONTROL, USER_ROLE_FULL_CONTROL, USER_ROLE_READ_ALL, USER_ROLE_READ_ID_USER_FK)
                add("idRoleFk" to idRoleFk, FULL_CONTROL, USER_ROLE_FULL_CONTROL, USER_ROLE_READ_ALL, USER_ROLE_READ_ID_ROLE_FK)
            }
        }

    fun insertValues(userRole: UserRole) = colsToMap(userRole, *permission.buildArray {
        Permission.apply {
            add(idUserFk, FULL_CONTROL, USER_ROLE_FULL_CONTROL, USER_ROLE_INSERT_ALL, USER_ROLE_INSERT_ID_USER_FK)
            add(idRoleFk, FULL_CONTROL, USER_ROLE_FULL_CONTROL, USER_ROLE_INSERT_ALL, USER_ROLE_INSERT_ID_ROLE_FK)
        }
    })
}

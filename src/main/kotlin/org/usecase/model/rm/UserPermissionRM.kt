package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission
import org.usecase.model.resource.UserPermission
import java.sql.ResultSet

/**
 * Relational Mapping of UserPermission from table user_permission
 * @author Simpli CLI generator
 */
class UserPermissionRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<UserPermission>() {
    override val table = "user_permission"

    val idUserFk = col("idUserFk",
            { idUserFk },
            { idUserFk = it.value() })

    val idPermissionFk = col("idPermissionFk",
            { idPermissionFk },
            { idPermissionFk = it.value() })

    fun build(rs: ResultSet) = UserPermission().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<UserPermission>>
        get() = permission.buildArray {
            Permission.apply {
                add(idUserFk, FULL_CONTROL, USER_PERMISSION_FULL_CONTROL, USER_PERMISSION_READ_ALL, USER_PERMISSION_READ_ID_USER_FK)
                add(idPermissionFk, FULL_CONTROL, USER_PERMISSION_FULL_CONTROL, USER_PERMISSION_READ_ALL, USER_PERMISSION_READ_ID_PERMISSION_FK)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<UserPermission>>
        get() = permission.buildArray {
            Permission.apply {
                add(idUserFk, FULL_CONTROL, USER_PERMISSION_FULL_CONTROL, USER_PERMISSION_READ_ALL, USER_PERMISSION_READ_ID_USER_FK)
                add(idPermissionFk, FULL_CONTROL, USER_PERMISSION_FULL_CONTROL, USER_PERMISSION_READ_ALL, USER_PERMISSION_READ_ID_PERMISSION_FK)
            }
        }

    val orderMap: Map<String, VirtualColumn<UserPermission>>
        get() = permission.buildMap {
            Permission.apply {
                add("idUserFk" to idUserFk, FULL_CONTROL, USER_PERMISSION_FULL_CONTROL, USER_PERMISSION_READ_ALL, USER_PERMISSION_READ_ID_USER_FK)
                add("idPermissionFk" to idPermissionFk, FULL_CONTROL, USER_PERMISSION_FULL_CONTROL, USER_PERMISSION_READ_ALL, USER_PERMISSION_READ_ID_PERMISSION_FK)
            }
        }

    fun insertValues(userPermission: UserPermission) = colsToMap(userPermission, *permission.buildArray {
        Permission.apply {
            add(idUserFk, FULL_CONTROL, USER_PERMISSION_FULL_CONTROL, USER_PERMISSION_INSERT_ALL, USER_PERMISSION_INSERT_ID_USER_FK)
            add(idPermissionFk, FULL_CONTROL, USER_PERMISSION_FULL_CONTROL, USER_PERMISSION_INSERT_ALL, USER_PERMISSION_INSERT_ID_PERMISSION_FK)
        }
    })
}

package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission
import org.usecase.model.resource.RolePermission
import java.sql.ResultSet

/**
 * Relational Mapping of RolePermission from table role_permission
 * @author Simpli CLI generator
 */
class RolePermissionRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<RolePermission>() {
    override val table = "role_permission"

    val idRoleFk = col("idRoleFk",
            { idRoleFk },
            { idRoleFk = it.value() })

    val idPermissionFk = col("idPermissionFk",
            { idPermissionFk },
            { idPermissionFk = it.value() })

    fun build(rs: ResultSet) = RolePermission().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<RolePermission>>
        get() = permission.buildArray {
            Permission.apply {
                add(idRoleFk, FULL_CONTROL, ROLE_PERMISSION_FULL_CONTROL, ROLE_PERMISSION_READ_ALL, ROLE_PERMISSION_READ_ID_ROLE_FK)
                add(idPermissionFk, FULL_CONTROL, ROLE_PERMISSION_FULL_CONTROL, ROLE_PERMISSION_READ_ALL, ROLE_PERMISSION_READ_ID_PERMISSION_FK)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<RolePermission>>
        get() = permission.buildArray {
            Permission.apply {
                add(idRoleFk, FULL_CONTROL, ROLE_PERMISSION_FULL_CONTROL, ROLE_PERMISSION_READ_ALL, ROLE_PERMISSION_READ_ID_ROLE_FK)
                add(idPermissionFk, FULL_CONTROL, ROLE_PERMISSION_FULL_CONTROL, ROLE_PERMISSION_READ_ALL, ROLE_PERMISSION_READ_ID_PERMISSION_FK)
            }
        }

    val orderMap: Map<String, VirtualColumn<RolePermission>>
        get() = permission.buildMap {
            Permission.apply {
                add("idRoleFk" to idRoleFk, FULL_CONTROL, ROLE_PERMISSION_FULL_CONTROL, ROLE_PERMISSION_READ_ALL, ROLE_PERMISSION_READ_ID_ROLE_FK)
                add("idPermissionFk" to idPermissionFk, FULL_CONTROL, ROLE_PERMISSION_FULL_CONTROL, ROLE_PERMISSION_READ_ALL, ROLE_PERMISSION_READ_ID_PERMISSION_FK)
            }
        }

    fun insertValues(rolePermission: RolePermission) = colsToMap(rolePermission, *permission.buildArray {
        Permission.apply {
            add(idRoleFk, FULL_CONTROL, ROLE_PERMISSION_FULL_CONTROL, ROLE_PERMISSION_INSERT_ALL, ROLE_PERMISSION_INSERT_ID_ROLE_FK)
            add(idPermissionFk, FULL_CONTROL, ROLE_PERMISSION_FULL_CONTROL, ROLE_PERMISSION_INSERT_ALL, ROLE_PERMISSION_INSERT_ID_PERMISSION_FK)
        }
    })
}

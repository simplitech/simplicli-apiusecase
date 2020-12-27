package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission
import org.usecase.model.resource.PermissionPermission
import java.sql.ResultSet

/**
 * Relational Mapping of PermissionPermission from table permission_permission
 * @author Simpli CLI generator
 */
class PermissionPermissionRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<PermissionPermission>() {
    override val table = "permission_permission"

    val idPermissionParentFk = col("idPermissionParentFk",
            { idPermissionParentFk },
            { idPermissionParentFk = it.value() })

    val idPermissionChildFk = col("idPermissionChildFk",
            { idPermissionChildFk },
            { idPermissionChildFk = it.value() })

    fun build(rs: ResultSet) = PermissionPermission().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<PermissionPermission>>
        get() = permission.buildArray {
            Permission.apply {
                add(idPermissionParentFk, FULL_CONTROL, PERMISSION_PERMISSION_FULL_CONTROL, PERMISSION_PERMISSION_READ_ALL, PERMISSION_PERMISSION_READ_ID_PERMISSION_PARENT_FK)
                add(idPermissionChildFk, FULL_CONTROL, PERMISSION_PERMISSION_FULL_CONTROL, PERMISSION_PERMISSION_READ_ALL, PERMISSION_PERMISSION_READ_ID_PERMISSION_CHILD_FK)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<PermissionPermission>>
        get() = permission.buildArray {
            Permission.apply {
                add(idPermissionParentFk, FULL_CONTROL, PERMISSION_PERMISSION_FULL_CONTROL, PERMISSION_PERMISSION_READ_ALL, PERMISSION_PERMISSION_READ_ID_PERMISSION_PARENT_FK)
                add(idPermissionChildFk, FULL_CONTROL, PERMISSION_PERMISSION_FULL_CONTROL, PERMISSION_PERMISSION_READ_ALL, PERMISSION_PERMISSION_READ_ID_PERMISSION_CHILD_FK)
            }
        }

    val orderMap: Map<String, VirtualColumn<PermissionPermission>>
        get() = permission.buildMap {
            Permission.apply {
                add("idPermissionParentFk" to idPermissionParentFk, FULL_CONTROL, PERMISSION_PERMISSION_FULL_CONTROL, PERMISSION_PERMISSION_READ_ALL, PERMISSION_PERMISSION_READ_ID_PERMISSION_PARENT_FK)
                add("idPermissionChildFk" to idPermissionChildFk, FULL_CONTROL, PERMISSION_PERMISSION_FULL_CONTROL, PERMISSION_PERMISSION_READ_ALL, PERMISSION_PERMISSION_READ_ID_PERMISSION_CHILD_FK)
            }
        }

    fun insertValues(permissionPermission: PermissionPermission) = colsToMap(permissionPermission, *permission.buildArray {
        Permission.apply {
            add(idPermissionParentFk, FULL_CONTROL, PERMISSION_PERMISSION_FULL_CONTROL, PERMISSION_PERMISSION_INSERT_ALL, PERMISSION_PERMISSION_INSERT_ID_PERMISSION_PARENT_FK)
            add(idPermissionChildFk, FULL_CONTROL, PERMISSION_PERMISSION_FULL_CONTROL, PERMISSION_PERMISSION_INSERT_ALL, PERMISSION_PERMISSION_INSERT_ID_PERMISSION_CHILD_FK)
        }
    })
}

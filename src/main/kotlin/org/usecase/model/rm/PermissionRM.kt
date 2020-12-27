package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission
import java.sql.ResultSet

/**
 * Relational Mapping of Permission from table permission
 * @author Simpli CLI generator
 */
class PermissionRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<Permission>() {
    override val table = "permission"

    val idPermissionPk = col("idPermissionPk",
            { idPermissionPk },
            { idPermissionPk = it.value() })

    val scope = col("scope",
            { scope },
            { scope = it.value() })

    val name = col("name",
            { name },
            { name = it.value() })

    val description = col("description",
            { description },
            { description = it.value() })

    val active = col("active",
            { active },
            { active = it.value() })

    fun build(rs: ResultSet) = Permission().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<Permission>>
        get() = permission.buildArray {
            Permission.apply {
                add(idPermissionPk, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_ID_PERMISSION_PK)
                add(scope, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_SCOPE)
                add(name, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_NAME)
                add(description, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_DESCRIPTION)
                add(active, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_ACTIVE)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<Permission>>
        get() = permission.buildArray {
            Permission.apply {
                add(idPermissionPk, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_ID_PERMISSION_PK)
                add(scope, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_SCOPE)
                add(name, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_NAME)
                add(description, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_DESCRIPTION)
            }
        }

    val orderMap: Map<String, VirtualColumn<Permission>>
        get() = permission.buildMap {
            Permission.apply {
                add("idPermissionPk" to idPermissionPk, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_ID_PERMISSION_PK)
                add("scope" to scope, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_SCOPE)
                add("name" to name, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_NAME)
                add("description" to description, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_DESCRIPTION)
                add("active" to active, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_READ_ALL, PERMISSION_READ_ACTIVE)
            }
        }

    fun updateSet(permission: Permission) = colsToMap(permission, *this.permission.buildArray {
        Permission.apply {
            add(scope, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_UPDATE_ALL, PERMISSION_UPDATE_SCOPE)
            add(name, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_UPDATE_ALL, PERMISSION_UPDATE_NAME)
            add(description, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_UPDATE_ALL, PERMISSION_UPDATE_DESCRIPTION)
        }
    })

    fun insertValues(permission: Permission) = colsToMap(permission, *this.permission.buildArray {
        Permission.apply {
            add(scope, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_INSERT_ALL, PERMISSION_INSERT_SCOPE)
            add(name, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_INSERT_ALL, PERMISSION_INSERT_NAME)
            add(description, FULL_CONTROL, PERMISSION_FULL_CONTROL, PERMISSION_INSERT_ALL, PERMISSION_INSERT_DESCRIPTION)
        }
    })
}

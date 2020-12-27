package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role
import java.sql.ResultSet

/**
 * Relational Mapping of Role from table role
 * @author Simpli CLI generator
 */
class RoleRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<Role>() {
    override val table = "role"

    val idRolePk = col("idRolePk",
            { idRolePk },
            { idRolePk = it.value() })

    val slug = col("slug",
            { slug },
            { slug = it.value() })

    val name = col("name",
            { name },
            { name = it.value() })

    val description = col("description",
            { description },
            { description = it.value() })

    val level = col("level",
            { level },
            { level = it.value() })

    val active = col("active",
            { active },
            { active = it.value() })

    fun build(rs: ResultSet) = Role().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<Role>>
        get() = permission.buildArray {
            Permission.apply {
                add(idRolePk, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_ID_ROLE_PK)
                add(slug, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_SLUG)
                add(name, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_NAME)
                add(description, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_DESCRIPTION)
                add(level, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_LEVEL)
                add(active, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_ACTIVE)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<Role>>
        get() = permission.buildArray {
            Permission.apply {
                add(idRolePk, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_ID_ROLE_PK)
                add(slug, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_SLUG)
                add(name, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_NAME)
                add(description, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_DESCRIPTION)
                add(level, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_LEVEL)
            }
        }

    val orderMap: Map<String, VirtualColumn<Role>>
        get() = permission.buildMap {
            Permission.apply {
                add("idRolePk" to idRolePk, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_ID_ROLE_PK)
                add("slug" to slug, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_SLUG)
                add("name" to name, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_NAME)
                add("description" to description, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_DESCRIPTION)
                add("level" to level, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_LEVEL)
                add("active" to active, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_READ_ALL, ROLE_READ_ACTIVE)
            }
        }

    fun updateSet(role: Role) = colsToMap(role, *permission.buildArray {
        Permission.apply {
            add(slug, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_UPDATE_ALL, ROLE_UPDATE_SLUG)
            add(name, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_UPDATE_ALL, ROLE_UPDATE_NAME)
            add(description, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_UPDATE_ALL, ROLE_UPDATE_DESCRIPTION)
            add(level, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_UPDATE_ALL, ROLE_UPDATE_LEVEL)
        }
    })

    fun insertValues(role: Role) = colsToMap(role, *permission.buildArray {
        Permission.apply {
            add(slug, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_INSERT_ALL, ROLE_INSERT_SLUG)
            add(name, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_INSERT_ALL, ROLE_INSERT_NAME)
            add(description, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_INSERT_ALL, ROLE_INSERT_DESCRIPTION)
            add(level, FULL_CONTROL, ROLE_FULL_CONTROL, ROLE_INSERT_ALL, ROLE_INSERT_LEVEL)
        }
    })
}

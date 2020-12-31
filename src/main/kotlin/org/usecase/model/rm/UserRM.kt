package org.usecase.model.rm

import org.usecase.model.resource.User
import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import br.com.simpli.tools.SecurityUtils
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table user
 * @author Simpli CLI generator
 */
class UserRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<User>() {
    override val table = "user"

    val idUserPk = col("idUserPk",
            { idUserPk },
            { idUserPk = it.value() })

    val email = col("email",
            { email },
            { email = it.value() })

    val senha = col("senha",
            { SecurityUtils.sha256(senha ?: "") },
            { senha = it.value() })

    fun build(rs: ResultSet) = User().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<User>>
        get() = permission.buildArray {
            Permission.apply {
                add(idUserPk, FULL_CONTROL, USER_FULL_CONTROL, USER_READ_ALL, USER_READ_ID_USER_PK)
                add(email, FULL_CONTROL, USER_FULL_CONTROL, USER_READ_ALL, USER_READ_EMAIL)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<User>>
        get() = permission.buildArray {
            Permission.apply {
                add(idUserPk, FULL_CONTROL, USER_FULL_CONTROL, USER_READ_ALL, USER_READ_ID_USER_PK)
                add(email, FULL_CONTROL, USER_FULL_CONTROL, USER_READ_ALL, USER_READ_EMAIL)
            }
        }

    val orderMap: Map<String, VirtualColumn<User>>
        get() = permission.buildMap {
            Permission.apply {
                add("idUserPk" to idUserPk, FULL_CONTROL, USER_FULL_CONTROL, USER_READ_ALL, USER_READ_ID_USER_PK)
                add("email" to email, FULL_CONTROL, USER_FULL_CONTROL, USER_READ_ALL, USER_READ_EMAIL)
            }
        }

    fun updateSet(user: User) = colsToMap(user, *permission.buildArray {
        Permission.apply {
            add(email, FULL_CONTROL, USER_FULL_CONTROL, USER_UPDATE_ALL, USER_UPDATE_EMAIL)

            if (user.senha.isNullOrBlank()) {
                add(senha, FULL_CONTROL, USER_FULL_CONTROL, USER_UPDATE_ALL, USER_UPDATE_SENHA)
            }
        }
    })

    fun insertValues(user: User) = colsToMap(user, *permission.buildArray {
        Permission.apply {
            add(email, FULL_CONTROL, USER_FULL_CONTROL, USER_INSERT_ALL, USER_INSERT_EMAIL)
            add(senha, FULL_CONTROL, USER_FULL_CONTROL, USER_INSERT_ALL, USER_INSERT_SENHA)
        }
    })
}

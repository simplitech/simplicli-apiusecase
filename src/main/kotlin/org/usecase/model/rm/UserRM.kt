package org.usecase.model.rm

import br.com.simpli.sql.Query
import org.usecase.model.resource.User
import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.model.resource.Tag
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.USER_READ_ALL
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table user
 * @author Simpli CLI generator
 */
class UserRM(val permission: Permission, override var alias: String? = null) : RelationalMapper<User>() {
    override val table = "user"

    val idUserPk = col("idUserPk",
            { idUserPk },
            { idUserPk = it.value() })

    val email = col("email",
            { email },
            { email = it.value() })

    val senha = col("senha",
            { senha },
            { senha = it.value() })

    fun build(rs: ResultSet) = User().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<User>>
        get() = permission.buildArray {
            add(USER_READ_ALL, idUserPk)
            add(USER_READ_ALL, email)
        }

    val fieldsToSearch: Array<VirtualColumn<User>>
        get() = permission.buildArray {
            add(USER_READ_ALL, idUserPk)
            add(USER_READ_ALL, email)
        }

    val orderMap: Map<String, VirtualColumn<User>>
        get() = permission.buildMap {
            add(USER_READ_ALL, "idUserPk" to idUserPk)
            add(USER_READ_ALL, "email" to email)
        }

    fun updateSet(user: User) = mapOf(
        email.column to user.email,
        senha.column to Query("IF(? IS NOT NULL, SHA2(?, 256), ${senha.column})", user.senha, user.senha)
    )

    fun insertValues(user: User) = mapOf(
        email.column to user.email,
        senha.column to Query("SHA2(?, 256)", user.senha)
    )
}

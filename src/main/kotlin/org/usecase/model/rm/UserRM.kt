package org.usecase.model.rm

import org.usecase.model.resource.User
import br.com.simpli.sql.Query
import br.com.simpli.sql.ResultBuilder
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table user
 * @author Simpli CLI generator
 */
object UserRM {
    fun build(rs: ResultSet, alias: String = "user", allowedColumns: Array<String> = selectFields(alias)) = User().apply {
        ResultBuilder(allowedColumns, rs, alias).run {
            idUserPk = getLong("idUserPk")
            email = getString("email")
            senha = getString("senha")
        }
    }

    fun selectFields(alias: String = "user") = arrayOf(
            "$alias.idUserPk",
            "$alias.email"
    )

    fun fieldsToSearch(alias: String = "user") = arrayOf(
            "$alias.idUserPk",
            "$alias.email"
    )

    fun orderMap(alias: String = "user") = mapOf(
            "idUserPk" to "$alias.idUserPk",
            "email" to "$alias.email"
    )

    fun updateSet(user: User) = mapOf(
            "email" to user.email,
            "senha" to Query("IF(? IS NOT NULL, SHA2(?, 256), senha)", user.senha, user.senha)
    )

    fun insertValues(user: User) = mapOf(
            "email" to user.email,
            "senha" to Query("SHA2(?, 256)", user.senha)
    )
}

package org.usecase.user.auth

import org.usecase.model.resource.User
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query

/**
 * Data Access Object of Auth
 * @author Simpli CLI generator
 */
class AuthDao(val con: AbstractConnector) {

    fun getIdOfUser(email: String, senha: String): Long? {
        val query = Query()
                .select("idUserPk")
                .from("user")
                .whereEq("email", email)
                .where("senha = SHA2(?, 256)", senha)

        return con.getFirstLong(query)
    }

    fun getUser(idUserPk: Long): User? {
        val query = Query()
                .selectAll()
                .from("user")
                .whereEq("idUserPk", idUserPk)

        return con.getOne(query) {
            User(it)
        }
    }

    fun getUserByEmail(email: String): User? {
        val query = Query()
                .selectAll()
                .from("user")
                .whereEq("email", email)

        return con.getOne(query) {
            User(it)
        }
    }

    fun updateUserPassword(email: String, senha: String): Int {
        val query = Query()
                .updateTable("user")
                .updateSet(
                        "senha" to Query("SHA2(?, 256)", senha)
                )
                .whereEq("email", email)

        return con.execute(query).affectedRows
    }

}

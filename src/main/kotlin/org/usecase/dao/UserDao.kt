package org.usecase.dao

import org.usecase.model.filter.UserListFilter
import org.usecase.model.resource.User
import org.usecase.model.rm.UserRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query

/**
 * Data Access Object of User from table user
 * @author Simpli CLI generator
 */
class UserDao(val con: AbstractConnector) {
    fun getOne(idUserPk: Long): User? {
        // TODO: review generated method
        val query = Query()
                .selectUser()
                .from("user")
                .whereEq("idUserPk", idUserPk)

        return con.getOne(query) {
            UserRM.build(it)
        }
    }

    fun getList(filter: UserListFilter): MutableList<User> {
        // TODO: review generated method
        val query = Query()
                .selectUser()
                .from("user")
                .whereUserFilter(filter)
                .orderAndLimitUser(filter)

        return con.getList(query) {
            UserRM.build(it)
        }
    }

    fun count(filter: UserListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idUserPk")
                .from("user")
                .whereUserFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(user: User): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("user")
                .updateUserSet(user)
                .whereEq("idUserPk", user.id)

        return con.execute(query).affectedRows
    }

    fun insert(user: User): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("user")
                .insertUserValues(user)

        return con.execute(query).key
    }

    fun exist(idUserPk: Long): Boolean {
        // TODO: review generated method
        val query = Query()
                .select("idUserPk")
                .from("user")
                .whereEq("idUserPk", idUserPk)

        return con.exist(query)
    }

    private fun Query.selectUser() = selectFields(UserRM.selectFields())

    private fun Query.updateUserSet(user: User) = updateSet(UserRM.updateSet(user))

    private fun Query.insertUserValues(user: User) = insertValues(UserRM.insertValues(user))

    private fun Query.whereUserFilter(filter: UserListFilter, alias: String = "user"): Query {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(UserRM.fieldsToSearch(alias), "%$it%")
            }
        }

        return this
    }

    private fun Query.orderAndLimitUser(filter: UserListFilter, alias: String = "user"): Query {
        UserRM.orderMap(alias)[filter.orderBy]?.also {
            orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            limit(index, it)
        }

        return this
    }
}

package org.usecase.dao

import org.usecase.model.filter.ListFilter
import org.usecase.model.resource.User
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
                .selectAll()
                .from("user")
                .whereEq("idUserPk", idUserPk)

        return con.getOne(query) {
            User(it)
        }
    }

    fun getList(filter: ListFilter): MutableList<User> {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("user")
                .applyListFilter(filter)

        User.orderMap[filter.orderBy]?.also {
            query.orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            query.limit(index, it)
        }

        return con.getList(query) {
            User(it)
        }
    }

    fun count(filter: ListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idUserPk")
                .from("user")
                .applyListFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(user: User): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("user")
                .updateSet(user.updateSet())
                .whereEq("idUserPk", user.id)

        return con.execute(query).affectedRows
    }

    fun insert(user: User): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("user")
                .insertValues(user.insertValues())

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


    private fun Query.applyListFilter(filter: ListFilter): Query {

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSome {
                    whereLike("user.idUserPk", "%$it%")
                    whereLike("user.email", "%$it%")
                }
            }
        }

        return this
    }

}

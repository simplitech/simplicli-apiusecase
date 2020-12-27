package org.usecase.dao

import org.usecase.model.filter.UserListFilter
import org.usecase.model.resource.User
import org.usecase.model.rm.UserRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission

/**
 * Data Access Object of User from table user
 * @author Simpli CLI generator
 */
class UserDao(val con: AbstractConnector, val permission: PermissionGroup) {
    fun getOne(idUserPk: Long): User? {
        val userRm = UserRM(permission)

        val vs = VirtualSelect()
                .selectFields(userRm.selectFields)
                .from(userRm)
                .whereEq(userRm.idUserPk, idUserPk)

        return con.getOne(vs.toQuery()) {
            userRm.build(it)
        }
    }

    fun getOneByLoginInfo(email: String, senha: String?, useSha2: Boolean = true): User? {
        val userRm = UserRM(permission)

        val vs = VirtualSelect()
                .selectFields(userRm.selectFields)
                .from(userRm)
                .whereEq(userRm.email, email)

        senha?.let {
            if (useSha2) {
                vs.whereRaw("%s = SHA2(?, 256)", arrayOf(userRm.senha), it)
            } else {
                vs.whereEq(userRm.senha, senha)
            }
        }

        return con.getOne(vs.toQuery()) {
            userRm.build(it)
        }
    }

    fun getLoginInfo(idUserPk: Long): Pair<String, String>? {
        val userRm = UserRM(permission)

        val vs = VirtualSelect()
                .select(userRm.idUserPk, userRm.email, userRm.senha)
                .from(userRm)
                .whereEq(userRm.idUserPk, idUserPk)

        return con.getOne(vs.toQuery()) {
            val email = it.getString(userRm.email.column)
            val senha = it.getString(userRm.senha.column)
            Pair(email, senha)
        }
    }

    fun getList(filter: UserListFilter): MutableList<User> {
        val userRm = UserRM(permission)

        val vs = VirtualSelect()
                .selectFields(userRm.selectFields)
                .from(userRm)
                .whereUserFilter(userRm, filter)
                .orderAndLimitUser(userRm, filter)

        return con.getList(vs.toQuery()) {
            userRm.build(it)
        }
    }

    fun count(filter: UserListFilter): Int {
        val userRm = UserRM(permission)

        val vs = VirtualSelect()
                .selectRaw("COUNT(DISTINCT %s)", userRm.idUserPk)
                .from(userRm)
                .whereUserFilter(userRm, filter)

        return con.getFirstInt(vs.toQuery()) ?: 0
    }

    fun update(user: User): Int {
        val userRm = UserRM(permission)
        val query = Query()
                .updateTable(userRm.table)
                .updateSet(userRm.updateSet(user))
                .whereEq(userRm.idUserPk.column, user.id)

        return con.execute(query).affectedRows
    }

    fun insert(user: User): Long {
        val userRm = UserRM(permission)
        val query = Query()
                .insertInto(userRm.table)
                .insertValues(userRm.insertValues(user))

        return con.execute(query).key
    }

    fun exist(idUserPk: Long): Boolean {
        val userRm = UserRM(permission)
        val vs = VirtualSelect()
                .select(userRm.idUserPk)
                .from(userRm)
                .whereEq(userRm.idUserPk, idUserPk)

        return con.exist(vs.toQuery())
    }

    private fun VirtualSelect.whereUserFilter(userRm: UserRM, filter: UserListFilter): VirtualSelect {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(userRm.fieldsToSearch, "%$it%")
            }
        }

        return this
    }

    private fun VirtualSelect.orderAndLimitUser(userRm: UserRM, filter: UserListFilter): VirtualSelect {
        orderBy(userRm.orderMap, filter.orderBy to filter.ascending)

        limitByPage(filter.page, filter.limit)

        return this
    }
}

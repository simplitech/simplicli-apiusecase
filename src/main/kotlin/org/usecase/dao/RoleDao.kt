package org.usecase.dao

import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.filter.RoleListFilter
import org.usecase.model.resource.Role
import org.usecase.model.rm.PermissionRM
import org.usecase.model.rm.RoleRM

/**
 * Data Access Object of Role from table role
 * @author Simpli CLI generator
 */
class RoleDao(val con: AbstractConnector, val permission: PermissionGroup) {
    fun getOne(idRolePk: Long): Role? {
        val roleRm = RoleRM(permission)

        val vs = VirtualSelect()
                .selectFields(roleRm.selectFields)
                .from(roleRm)
                .whereEq(roleRm.idRolePk, idRolePk)

        return con.getOne(vs.toQuery()) {
            roleRm.build(it)
        }
    }

    fun getList(filter: RoleListFilter): MutableList<Role> {
        val roleRm = RoleRM(permission)

        val vs = VirtualSelect()
                .selectFields(roleRm.selectFields)
                .from(roleRm)
                .whereRoleFilter(roleRm, filter)
                .orderAndLimitRole(roleRm, filter)

        return con.getList(vs.toQuery()) {
            roleRm.build(it)
        }
    }

    fun count(filter: RoleListFilter): Int {
        val roleRm = RoleRM(permission)

        val vs = VirtualSelect()
                .selectRaw("COUNT(DISTINCT %s)", roleRm.idRolePk)
                .from(roleRm)
                .whereRoleFilter(roleRm, filter)

        return con.getFirstInt(vs.toQuery()) ?: 0
    }

    fun update(role: Role): Int {
        val roleRm = RoleRM(permission)

        val query = Query()
                .updateTable(roleRm.table)
                .updateSet(roleRm.updateSet(role))
                .whereEq(roleRm.idRolePk.column, role.id)

        return con.execute(query).affectedRows
    }

    fun insert(role: Role): Long {
        val roleRm = RoleRM(permission)

        val query = Query()
                .insertInto(roleRm.table)
                .insertValues(roleRm.insertValues(role))

        return con.execute(query).key
    }

    fun exist(idRolePk: Long): Boolean {
        val roleRm = RoleRM(permission)

        val vs = VirtualSelect()
                .select(roleRm.idRolePk)
                .from(roleRm)
                .whereEq(roleRm.idRolePk, idRolePk)

        return con.exist(vs.toQuery())
    }

    fun existSlug(slug: String, idRolePk: Long): Boolean {
        val roleRm = RoleRM(permission)

        val vs = VirtualSelect()
                .select(roleRm.slug)
                .from(roleRm)
                .whereEq(roleRm.slug, slug)
                .whereNotEq(roleRm.idRolePk, idRolePk)

        return con.exist(vs.toQuery())
    }

    fun softDelete(idRolePk: Long): Int {
        val roleRm = RoleRM(permission)

        val query = Query()
                .updateTable(roleRm.table)
                .updateSet(roleRm.active.column to false)
                .whereEq(roleRm.idRolePk.column, idRolePk)

        return con.execute(query).affectedRows
    }

    private fun VirtualSelect.whereRoleFilter(roleRm: RoleRM, filter: RoleListFilter): VirtualSelect {
        whereEq(roleRm.active, true)

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(roleRm.fieldsToSearch, "%$it%")
            }
        }

        return this
    }

    private fun VirtualSelect.orderAndLimitRole(roleRm: RoleRM, filter: RoleListFilter): VirtualSelect {
        orderBy(roleRm.orderMap, filter.orderBy to filter.ascending)

        limitByPage(filter.page, filter.limit)

        return this
    }
}

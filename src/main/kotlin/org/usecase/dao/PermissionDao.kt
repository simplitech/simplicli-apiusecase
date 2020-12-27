package org.usecase.dao

import org.usecase.model.rm.PermissionRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.filter.PermissionListFilter
import org.usecase.model.resource.Permission
import org.usecase.model.rm.PrincipalRM

/**
 * Data Access Object of Permission from table permission
 * @author Simpli CLI generator
 */
class PermissionDao(val con: AbstractConnector, val permission: PermissionGroup) {
    fun getOne(idPermissionPk: Long): Permission? {
        val permissionRm = PermissionRM(permission)

        val vs = VirtualSelect()
                .selectFields(permissionRm.selectFields)
                .from(permissionRm)
                .whereEq(permissionRm.idPermissionPk, idPermissionPk)

        return con.getOne(vs.toQuery()) {
            permissionRm.build(it)
        }
    }

    fun getList(filter: PermissionListFilter): MutableList<Permission> {
        val permissionRm = PermissionRM(permission)

        val vs = VirtualSelect()
                .selectFields(permissionRm.selectFields)
                .from(permissionRm)
                .wherePermissionFilter(permissionRm, filter)
                .orderAndLimitPermission(permissionRm, filter)

        return con.getList(vs.toQuery()) {
            permissionRm.build(it)
        }
    }

    fun count(filter: PermissionListFilter): Int {
        val permissionRm = PermissionRM(permission)

        val vs = VirtualSelect()
                .selectRaw("COUNT(DISTINCT %s)", permissionRm.idPermissionPk)
                .from(permissionRm)
                .wherePermissionFilter(permissionRm, filter)

        return con.getFirstInt(vs.toQuery()) ?: 0
    }

    fun update(permission: Permission): Int {
        val permissionRm = PermissionRM(this.permission)

        val query = Query()
                .updateTable(permissionRm.table)
                .updateSet(permissionRm.updateSet(permission))
                .whereEq(permissionRm.idPermissionPk.column, permission.id)

        return con.execute(query).affectedRows
    }

    fun insert(permission: Permission): Long {
        val permissionRm = PermissionRM(this.permission)

        val query = Query()
                .insertInto(permissionRm.table)
                .insertValues(permissionRm.insertValues(permission))

        return con.execute(query).key
    }

    fun exist(idPermissionPk: Long): Boolean {
        val permissionRm = PermissionRM(permission)

        val vs = VirtualSelect()
                .select(permissionRm.idPermissionPk)
                .from(permissionRm)
                .whereEq(permissionRm.idPermissionPk, idPermissionPk)

        return con.exist(vs.toQuery())
    }

    fun existScope(scope: String, idPermissionPk: Long): Boolean {
        val permissionRm = PermissionRM(permission)

        val vs = VirtualSelect()
                .select(permissionRm.scope)
                .from(permissionRm)
                .whereEq(permissionRm.scope, scope)
                .whereNotEq(permissionRm.idPermissionPk, idPermissionPk)

        return con.exist(vs.toQuery())
    }

    fun softDelete(idPermissionPk: Long): Int {
        val permissionRm = PermissionRM(permission)

        val query = Query()
                .updateTable(permissionRm.table)
                .updateSet(permissionRm.active.column to false)
                .whereEq(permissionRm.idPermissionPk.column, idPermissionPk)

        return con.execute(query).affectedRows
    }

    private fun VirtualSelect.wherePermissionFilter(permissionRm: PermissionRM, filter: PermissionListFilter): VirtualSelect {
        whereEq(permissionRm.active, true)

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(permissionRm.fieldsToSearch, "%$it%")
            }
        }

        return this
    }

    private fun VirtualSelect.orderAndLimitPermission(permissionRm: PermissionRM, filter: PermissionListFilter): VirtualSelect {
        orderBy(permissionRm.orderMap, filter.orderBy to filter.ascending)

        limitByPage(filter.page, filter.limit)

        return this
    }
}

package org.usecase.dao

import org.usecase.model.filter.GrupoDoPrincipalListFilter
import org.usecase.model.resource.GrupoDoPrincipal
import org.usecase.model.rm.GrupoDoPrincipalRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission

/**
 * Data Access Object of GrupoDoPrincipal from table grupo_do_principal
 * @author Simpli CLI generator
 */
class GrupoDoPrincipalDao(val con: AbstractConnector, val permission: PermissionGroup) {
    fun getOne(idGrupoDoPrincipalPk: Long): GrupoDoPrincipal? {
        val grupoDoPrincipalRm = GrupoDoPrincipalRM(permission)

        val vs = VirtualSelect()
                .selectFields(grupoDoPrincipalRm.selectFields)
                .from(grupoDoPrincipalRm)
                .whereEq(grupoDoPrincipalRm.idGrupoDoPrincipalPk, idGrupoDoPrincipalPk)

        return con.getOne(vs.toQuery()) {
            grupoDoPrincipalRm.build(it)
        }
    }

    fun getList(filter: GrupoDoPrincipalListFilter): MutableList<GrupoDoPrincipal> {
        val grupoDoPrincipalRm = GrupoDoPrincipalRM(permission)

        val vs = VirtualSelect()
                .selectFields(grupoDoPrincipalRm.selectFields)
                .from(grupoDoPrincipalRm)
                .whereGrupoDoPrincipalFilter(grupoDoPrincipalRm, filter)
                .orderAndLimitGrupoDoPrincipal(grupoDoPrincipalRm, filter)

        return con.getList(vs.toQuery()) {
            grupoDoPrincipalRm.build(it)
        }
    }

    fun count(filter: GrupoDoPrincipalListFilter): Int {
        val grupoDoPrincipalRm = GrupoDoPrincipalRM(permission)

        val vs = VirtualSelect()
                .selectRaw("COUNT(DISTINCT %s)", grupoDoPrincipalRm.idGrupoDoPrincipalPk)
                .from(grupoDoPrincipalRm)
                .whereGrupoDoPrincipalFilter(grupoDoPrincipalRm, filter)

        return con.getFirstInt(vs.toQuery()) ?: 0
    }

    fun update(grupoDoPrincipal: GrupoDoPrincipal): Int {
        val grupoDoPrincipalRm = GrupoDoPrincipalRM(permission)
        val query = Query()
                .updateTable(grupoDoPrincipalRm.table)
                .updateSet(grupoDoPrincipalRm.updateSet(grupoDoPrincipal))
                .whereEq(grupoDoPrincipalRm.idGrupoDoPrincipalPk.column, grupoDoPrincipal.id)

        return con.execute(query).affectedRows
    }

    fun insert(grupoDoPrincipal: GrupoDoPrincipal): Long {
        val grupoDoPrincipalRm = GrupoDoPrincipalRM(permission)
        val query = Query()
                .insertInto(grupoDoPrincipalRm.table)
                .insertValues(grupoDoPrincipalRm.insertValues(grupoDoPrincipal))

        return con.execute(query).key
    }

    fun exist(idGrupoDoPrincipalPk: Long): Boolean {
        val grupoDoPrincipalRm = GrupoDoPrincipalRM(permission)
        val vs = VirtualSelect()
                .select(grupoDoPrincipalRm.idGrupoDoPrincipalPk)
                .from(grupoDoPrincipalRm)
                .whereEq(grupoDoPrincipalRm.idGrupoDoPrincipalPk, idGrupoDoPrincipalPk)

        return con.exist(vs.toQuery())
    }

    private fun VirtualSelect.whereGrupoDoPrincipalFilter(grupoDoPrincipalRm: GrupoDoPrincipalRM, filter: GrupoDoPrincipalListFilter): VirtualSelect {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(grupoDoPrincipalRm.fieldsToSearch, "%$it%")
            }
        }

        return this
    }

    private fun VirtualSelect.orderAndLimitGrupoDoPrincipal(grupoDoPrincipalRm: GrupoDoPrincipalRM, filter: GrupoDoPrincipalListFilter): VirtualSelect {
        orderBy(grupoDoPrincipalRm.orderMap, filter.orderBy to filter.ascending)

        limitByPage(filter.page, filter.limit)

        return this
    }
}

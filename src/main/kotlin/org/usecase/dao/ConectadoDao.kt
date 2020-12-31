package org.usecase.dao

import org.usecase.model.filter.ConectadoListFilter
import org.usecase.model.resource.Conectado
import org.usecase.model.rm.ConectadoRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission

/**
 * Data Access Object of Conectado from table conectado
 * @author Simpli CLI generator
 */
class ConectadoDao(val con: AbstractConnector, val permission: PermissionGroup) {
    fun getOne(idConectadoPk: Long): Conectado? {
        val conectadoRm = ConectadoRM(permission)

        val vs = VirtualSelect()
                .selectFields(conectadoRm.selectFields)
                .from(conectadoRm)
                .whereEq(conectadoRm.idConectadoPk, idConectadoPk)

        return con.getOne(vs.toQuery()) {
            conectadoRm.build(it)
        }
    }

    fun getList(filter: ConectadoListFilter): MutableList<Conectado> {
        val conectadoRm = ConectadoRM(permission)

        val vs = VirtualSelect()
                .selectFields(conectadoRm.selectFields)
                .from(conectadoRm)
                .whereConectadoFilter(conectadoRm, filter)
                .orderAndLimitConectado(conectadoRm, filter)

        return con.getList(vs.toQuery()) {
            conectadoRm.build(it)
        }
    }

    fun count(filter: ConectadoListFilter): Int {
        val conectadoRm = ConectadoRM(permission)

        val vs = VirtualSelect()
                .selectRaw("COUNT(DISTINCT %s)", conectadoRm.idConectadoPk)
                .from(conectadoRm)
                .whereConectadoFilter(conectadoRm, filter)

        return con.getFirstInt(vs.toQuery()) ?: 0
    }

    fun update(conectado: Conectado): Int {
        val conectadoRm = ConectadoRM(permission)
        val query = Query()
                .updateTable(conectadoRm.table)
                .updateSet(conectadoRm.updateSet(conectado))
                .whereEq(conectadoRm.idConectadoPk.column, conectado.id)

        return con.execute(query).affectedRows
    }

    fun insert(conectado: Conectado): Long {
        val conectadoRm = ConectadoRM(permission)
        val query = Query()
                .insertInto(conectadoRm.table)
                .insertValues(conectadoRm.insertValues(conectado))

        return con.execute(query).key
    }

    fun exist(idConectadoPk: Long): Boolean {
        val conectadoRm = ConectadoRM(permission)
        val vs = VirtualSelect()
                .select(conectadoRm.idConectadoPk)
                .from(conectadoRm)
                .whereEq(conectadoRm.idConectadoPk, idConectadoPk)

        return con.exist(vs.toQuery())
    }

    private fun VirtualSelect.whereConectadoFilter(conectadoRm: ConectadoRM, filter: ConectadoListFilter): VirtualSelect {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(conectadoRm.fieldsToSearch, "%$it%")
            }
        }

        return this
    }

    private fun VirtualSelect.orderAndLimitConectado(conectadoRm: ConectadoRM, filter: ConectadoListFilter): VirtualSelect {
        orderBy(conectadoRm.orderMap, filter.orderBy to filter.ascending)

        limitByPage(filter.page, filter.limit)

        return this
    }
}

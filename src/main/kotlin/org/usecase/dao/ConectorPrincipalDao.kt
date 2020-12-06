package org.usecase.dao

import org.usecase.model.filter.ConectorPrincipalListFilter
import org.usecase.model.resource.ConectorPrincipal
import org.usecase.model.rm.ConectorPrincipalRM
import org.usecase.model.rm.ConectadoRM
import org.usecase.model.rm.PrincipalRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.user.context.Permission

/**
 * Data Access Object of ConectorPrincipal from table conector_principal
 * @author Simpli CLI generator
 */
class ConectorPrincipalDao(val con: AbstractConnector) {
    fun getOne(idPrincipalFk: Long, idConectadoFk: Long, permission: Permission): ConectorPrincipal? {
        val conectorPrincipalRm = ConectorPrincipalRM(permission)
        val conectadoRm = ConectadoRM(permission)
        val principalRm = PrincipalRM(permission)

        val vs = VirtualSelect()
                .selectFields(conectorPrincipalRm.selectFields)
                .selectFields(conectadoRm.selectFields)
                .selectFields(principalRm.selectFields)
                .from(conectorPrincipalRm)
                .innerJoin(conectadoRm, conectadoRm.idConectadoPk, conectorPrincipalRm.idConectadoFk)
                .innerJoin(principalRm, principalRm.idPrincipalPk, conectorPrincipalRm.idPrincipalFk)
                .whereEq(conectorPrincipalRm.idPrincipalFk, idPrincipalFk)
                .whereEq(conectorPrincipalRm.idConectadoFk, idConectadoFk)

        return con.getOne(vs.toQuery()) {
            conectorPrincipalRm.build(it).apply {
                conectado = conectadoRm.build(it)
                principal = principalRm.build(it)
            }
        }
    }

    fun getList(filter: ConectorPrincipalListFilter, permission: Permission): MutableList<ConectorPrincipal> {
        val conectorPrincipalRm = ConectorPrincipalRM(permission)
        val conectadoRm = ConectadoRM(permission)
        val principalRm = PrincipalRM(permission)

        val vs = VirtualSelect()
                .selectFields(conectorPrincipalRm.selectFields)
                .selectFields(conectadoRm.selectFields)
                .selectFields(principalRm.selectFields)
                .from(conectorPrincipalRm)
                .innerJoin(conectadoRm, conectadoRm.idConectadoPk, conectorPrincipalRm.idConectadoFk)
                .innerJoin(principalRm, principalRm.idPrincipalPk, conectorPrincipalRm.idPrincipalFk)
                .whereConectorPrincipalFilter(conectorPrincipalRm, filter)
                .orderAndLimitConectorPrincipal(conectorPrincipalRm, filter)

        return con.getList(vs.toQuery()) {
            conectorPrincipalRm.build(it).apply {
                conectado = conectadoRm.build(it)
                principal = principalRm.build(it)
            }
        }
    }

    fun count(filter: ConectorPrincipalListFilter, permission: Permission): Int {
        val conectorPrincipalRm = ConectorPrincipalRM(permission)
        val conectadoRm = ConectadoRM(permission)
        val principalRm = PrincipalRM(permission)

        val vs = VirtualSelect()
                .selectRaw("COUNT(DISTINCT %s)", conectorPrincipalRm.idPrincipalFk)
                .from(conectorPrincipalRm)
                .innerJoin(conectadoRm, conectadoRm.idConectadoPk, conectorPrincipalRm.idConectadoFk)
                .innerJoin(principalRm, principalRm.idPrincipalPk, conectorPrincipalRm.idPrincipalFk)
                .whereConectorPrincipalFilter(conectorPrincipalRm, filter)

        return con.getFirstInt(vs.toQuery()) ?: 0
    }

    fun update(conectorPrincipal: ConectorPrincipal, permission: Permission): Int {
        val conectorPrincipalRm = ConectorPrincipalRM(permission)
        val query = Query()
                .updateTable(conectorPrincipalRm.table)
                .updateSet(conectorPrincipalRm.updateSet(conectorPrincipal))
                .whereEq(conectorPrincipalRm.idPrincipalFk.column, conectorPrincipal.idPrincipalFk)
                .whereEq(conectorPrincipalRm.idConectadoFk.column, conectorPrincipal.idConectadoFk)

        return con.execute(query).affectedRows
    }

    fun insert(conectorPrincipal: ConectorPrincipal, permission: Permission): Long {
        val conectorPrincipalRm = ConectorPrincipalRM(permission)
        val query = Query()
                .insertInto(conectorPrincipalRm.table)
                .insertValues(conectorPrincipalRm.insertValues(conectorPrincipal))

        return con.execute(query).key
    }

    fun exist(idPrincipalFk: Long, idConectadoFk: Long, permission: Permission): Boolean {
        val conectorPrincipalRm = ConectorPrincipalRM(permission)
        val vs = VirtualSelect()
                .select(conectorPrincipalRm.idPrincipalFk)
                .from(conectorPrincipalRm)
                .whereEq(conectorPrincipalRm.idPrincipalFk, idPrincipalFk)
                .whereEq(conectorPrincipalRm.idConectadoFk, idConectadoFk)

        return con.exist(vs.toQuery())
    }

    private fun VirtualSelect.whereConectorPrincipalFilter(conectorPrincipalRm: ConectorPrincipalRM, filter: ConectorPrincipalListFilter): VirtualSelect {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(conectorPrincipalRm.fieldsToSearch, "%$it%")
            }
        }

        return this
    }

    private fun VirtualSelect.orderAndLimitConectorPrincipal(conectorPrincipalRm: ConectorPrincipalRM, filter: ConectorPrincipalListFilter): VirtualSelect {
        orderBy(conectorPrincipalRm.orderMap, filter.orderBy to filter.ascending)

        limitByPage(filter.page, filter.limit)

        return this
    }
}

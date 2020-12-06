package org.usecase.dao

import org.usecase.model.filter.PrincipalListFilter
import org.usecase.model.resource.Principal
import org.usecase.model.rm.PrincipalRM
import org.usecase.model.rm.GrupoDoPrincipalRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.user.context.Permission

/**
 * Data Access Object of Principal from table principal
 * @author Simpli CLI generator
 */
class PrincipalDao(val con: AbstractConnector) {
    fun getOne(idPrincipalPk: Long, permission: Permission): Principal? {
        val principalRm = PrincipalRM(permission)
        val grupoDoPrincipalRm = GrupoDoPrincipalRM(permission)
        val grupoDoPrincipalFacultativoRm = GrupoDoPrincipalRM(permission,"grupo_do_principal_facultativo")

        val vs = VirtualSelect()
                .selectFields(principalRm.selectFields)
                .selectFields(grupoDoPrincipalRm.selectFields)
                .selectFields(grupoDoPrincipalFacultativoRm.selectFields)
                .from(principalRm)
                .innerJoin(grupoDoPrincipalRm, grupoDoPrincipalRm.idGrupoDoPrincipalPk, principalRm.idGrupoDoPrincipalFk)
                .leftJoin(grupoDoPrincipalFacultativoRm, grupoDoPrincipalFacultativoRm.idGrupoDoPrincipalPk, principalRm.idGrupoDoPrincipalFacultativoFk)
                .whereEq(principalRm.idPrincipalPk, idPrincipalPk)

        return con.getOne(vs.toQuery()) {
            principalRm.build(it).apply {
                grupoDoPrincipal = grupoDoPrincipalRm.build(it)
                grupoDoPrincipalFacultativo = grupoDoPrincipalFacultativoRm.build(it)
            }
        }
    }

    fun getList(filter: PrincipalListFilter, permission: Permission): MutableList<Principal> {
        val principalRm = PrincipalRM(permission)
        val grupoDoPrincipalRm = GrupoDoPrincipalRM(permission)
        val grupoDoPrincipalFacultativoRm = GrupoDoPrincipalRM(permission, "grupo_do_principal_facultativo")

        val vs = VirtualSelect()
                .selectFields(principalRm.selectFields)
                .selectFields(grupoDoPrincipalRm.selectFields)
                .selectFields(grupoDoPrincipalFacultativoRm.selectFields)
                .from(principalRm)
                .innerJoin(grupoDoPrincipalRm, grupoDoPrincipalRm.idGrupoDoPrincipalPk, principalRm.idGrupoDoPrincipalFk)
                .leftJoin(grupoDoPrincipalFacultativoRm, grupoDoPrincipalFacultativoRm.idGrupoDoPrincipalPk, principalRm.idGrupoDoPrincipalFacultativoFk)
                .wherePrincipalFilter(principalRm, filter)
                .orderAndLimitPrincipal(principalRm, filter)

        return con.getList(vs.toQuery()) {
            principalRm.build(it).apply {
                grupoDoPrincipal = grupoDoPrincipalRm.build(it)
                grupoDoPrincipalFacultativo = grupoDoPrincipalFacultativoRm.build(it)
            }
        }
    }

    fun count(filter: PrincipalListFilter, permission: Permission): Int {
        val principalRm = PrincipalRM(permission)
        val grupoDoPrincipalRm = GrupoDoPrincipalRM(permission)
        val grupoDoPrincipalFacultativoRm = GrupoDoPrincipalRM(permission, "grupo_do_principal_facultativo")

        val vs = VirtualSelect()
                .selectRaw("COUNT(DISTINCT %s)", principalRm.idPrincipalPk)
                .from(principalRm)
                .innerJoin(grupoDoPrincipalRm, grupoDoPrincipalRm.idGrupoDoPrincipalPk, principalRm.idGrupoDoPrincipalFk)
                .leftJoin(grupoDoPrincipalFacultativoRm, grupoDoPrincipalFacultativoRm.idGrupoDoPrincipalPk, principalRm.idGrupoDoPrincipalFacultativoFk)
                .wherePrincipalFilter(principalRm, filter)

        return con.getFirstInt(vs.toQuery()) ?: 0
    }

    fun update(principal: Principal, permission: Permission): Int {
        val principalRm = PrincipalRM(permission)
        val query = Query()
                .updateTable(principalRm.table)
                .updateSet(principalRm.updateSet(principal))
                .whereEq(principalRm.idPrincipalPk.column, principal.id)

        return con.execute(query).affectedRows
    }

    fun insert(principal: Principal, permission: Permission): Long {
        val principalRm = PrincipalRM(permission)
        val query = Query()
                .insertInto(principalRm.table)
                .insertValues(principalRm.insertValues(principal))

        return con.execute(query).key
    }

    fun exist(idPrincipalPk: Long, permission: Permission): Boolean {
        val principalRm = PrincipalRM(permission)
        val vs = VirtualSelect()
                .select(principalRm.idPrincipalPk)
                .from(principalRm)
                .whereEq(principalRm.idPrincipalPk, idPrincipalPk)

        return con.exist(vs.toQuery())
    }

    fun existUnico(unico: String, idPrincipalPk: Long, permission: Permission): Boolean {
        val principalRm = PrincipalRM(permission)
        val vs = VirtualSelect()
                .select(principalRm.unico)
                .from(principalRm)
                .whereEq(principalRm.unico, unico)
                .whereNotEq(principalRm.idPrincipalPk, idPrincipalPk)

        return con.exist(vs.toQuery())
    }

    fun softDelete(idPrincipalPk: Long, permission: Permission): Int {
        val principalRm = PrincipalRM(permission)
        val query = Query()
                .updateTable(principalRm.table)
                .updateSet(principalRm.ativo.column to false)
                .whereEq(principalRm.idPrincipalPk.column, idPrincipalPk)

        return con.execute(query).affectedRows
    }

    private fun VirtualSelect.wherePrincipalFilter(principalRm: PrincipalRM, filter: PrincipalListFilter): VirtualSelect {
        whereEq(principalRm.ativo, true)

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(principalRm.fieldsToSearch, "%$it%")
            }
        }

        filter.idGrupoDoPrincipalFk?.also {
            if (it.isNotEmpty()) {
                whereIn(principalRm.idGrupoDoPrincipalFk, *it.toTypedArray())
            }
        }

        filter.idGrupoDoPrincipalFacultativoFk?.also {
            if (it.isNotEmpty()) {
                whereIn(principalRm.idGrupoDoPrincipalFacultativoFk, *it.toTypedArray())
            }
        }

        filter.startDataObrigatoria?.also {
            whereDateGtEq(principalRm.dataObrigatoria, it)
        }
        filter.endDataObrigatoria?.also {
            whereDateLtEq(principalRm.dataObrigatoria, it)
        }

        filter.startDataFacultativa?.also {
            whereDateGtEq(principalRm.dataFacultativa, it)
        }
        filter.endDataFacultativa?.also {
            whereDateLtEq(principalRm.dataFacultativa, it)
        }

        filter.startDatahoraObrigatoria?.also {
            whereDateGtEq(principalRm.datahoraObrigatoria, it)
        }
        filter.endDatahoraObrigatoria?.also {
            whereDateLtEq(principalRm.datahoraObrigatoria, it)
        }

        filter.startDatahoraFacultativa?.also {
            whereDateGtEq(principalRm.datahoraFacultativa, it)
        }
        filter.endDatahoraFacultativa?.also {
            whereDateLtEq(principalRm.datahoraFacultativa, it)
        }

        filter.startDataCriacao?.also {
            whereDateGtEq(principalRm.dataCriacao, it)
        }
        filter.endDataCriacao?.also {
            whereDateLtEq(principalRm.dataCriacao, it)
        }

        filter.startDataAlteracao?.also {
            whereDateGtEq(principalRm.dataAlteracao, it)
        }
        filter.endDataAlteracao?.also {
            whereDateLtEq(principalRm.dataAlteracao, it)
        }

        filter.minDecimalObrigatorio?.also {
            whereGtEq(principalRm.decimalObrigatorio, it)
        }
        filter.maxDecimalObrigatorio?.also {
            whereLtEq(principalRm.decimalObrigatorio, it)
        }

        filter.minDecimalFacultativo?.also {
            whereGtEq(principalRm.decimalFacultativo, it)
        }
        filter.maxDecimalFacultativo?.also {
            whereLtEq(principalRm.decimalFacultativo, it)
        }

        filter.minInteiroObrigatorio?.also {
            whereGtEq(principalRm.inteiroObrigatorio, it)
        }
        filter.maxInteiroObrigatorio?.also {
            whereLtEq(principalRm.inteiroObrigatorio, it)
        }

        filter.minInteiroFacultativo?.also {
            whereGtEq(principalRm.inteiroFacultativo, it)
        }
        filter.maxInteiroFacultativo?.also {
            whereLtEq(principalRm.inteiroFacultativo, it)
        }

        filter.minPreco?.also {
            whereGtEq(principalRm.preco, it)
        }
        filter.maxPreco?.also {
            whereLtEq(principalRm.preco, it)
        }

        filter.booleanoObrigatorio?.also {
            whereEq(principalRm.booleanoObrigatorio, it)
        }

        filter.booleanoFacultativo?.also {
            whereEq(principalRm.booleanoFacultativo, it)
        }

        return this
    }

    private fun VirtualSelect.orderAndLimitPrincipal(principalRm: PrincipalRM, filter: PrincipalListFilter): VirtualSelect {
        orderBy(principalRm.orderMap, filter.orderBy to filter.ascending)

        limitByPage(filter.page, filter.limit)

        return this
    }
}

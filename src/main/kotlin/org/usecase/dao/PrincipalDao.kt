package org.usecase.dao

import org.usecase.model.resource.Principal
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.ResultBuilder
import org.usecase.rm.PrincipalRM
import org.usecase.model.filter.PrincipalListFilter

/**
 * Data Access Object of Principal from table principal
 * @author Simpli CLI generator
 */
class PrincipalDao(val con: AbstractConnector) {

    fun getOne(idPrincipalPk: Long): Principal? {
        // TODO: review generated method
        val query = Query()
                .selectFields(PrincipalRM.selectFields())
                .from("principal")
                .whereEq("idPrincipalPk", idPrincipalPk)

        return con.getOne(query) {
            PrincipalRM.build(ResultBuilder(PrincipalRM.selectFields(), it, "principal"))
        }
    }

    fun getList(filter: PrincipalListFilter): MutableList<Principal> {
        // TODO: review generated method
        val query = Query()
                .selectFields(PrincipalRM.selectFields())
                .from("principal")
                .applyListFilter(filter)

        PrincipalRM.orderMap()[filter.orderBy]?.also {
            query.orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            query.limit(index, it)
        }

        return con.getList(query) {
            PrincipalRM.build(ResultBuilder(PrincipalRM.selectFields(), it, "principal"))
        }
    }

    fun count(filter: PrincipalListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idPrincipalPk")
                .from("principal")
                .applyListFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(principal: Principal): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("principal")
                .updateSet(PrincipalRM.updateSet(principal))
                .whereEq("idPrincipalPk", principal.id)

        return con.execute(query).affectedRows
    }

    fun insert(principal: Principal): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("principal")
                .insertValues(PrincipalRM.insertValues(principal))

        return con.execute(query).key
    }

    fun exist(idPrincipalPk: Long): Boolean {
        // TODO: review generated method
        val query = Query()
                .select("idPrincipalPk")
                .from("principal")
                .whereEq("idPrincipalPk", idPrincipalPk)

        return con.exist(query)
    }

    fun existUnico(unico: String, idPrincipalPk: Long): Boolean {
        // TODO: review generated method
        val query = Query()
                .select("unico")
                .from("principal")
                .whereEq("unico", unico)
                .whereNotEq("idPrincipalPk", idPrincipalPk)

        return con.exist(query)
    }

    fun softDelete(idPrincipalPk: Long): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("principal")
                .updateSet("ativo" to false)
                .whereEq("idPrincipalPk", idPrincipalPk)

        return con.execute(query).affectedRows
    }

    private fun Query.applyListFilter(filter: PrincipalListFilter, alias: String = "principal"): Query {
        whereEq("$alias.ativo", true)

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(PrincipalRM.fieldsToSearch(alias), "%$it%")
            }
        }

        filter.idGrupoDoPrincipalFk?.also {
            if (it.isNotEmpty()) {
                whereIn("$alias.idGrupoDoPrincipalFk", *it.toTypedArray())
            }
        }
        filter.idGrupoDoPrincipalFacultativoFk?.also {
            if (it.isNotEmpty()) {
                whereIn("$alias.idGrupoDoPrincipalFacultativoFk", *it.toTypedArray())
            }
        }

        filter.minInteiroObrigatorio?.also {
            whereGtEq("$alias.inteiroObrigatorio", it)
        }
        filter.maxInteiroObrigatorio?.also {
            whereLtEq("$alias.inteiroObrigatorio", it)
        }

        filter.minDecimalObrigatorio?.also {
            whereGtEq("$alias.decimalObrigatorio", it)
        }
        filter.maxDecimalObrigatorio?.also {
            whereLtEq("$alias.decimalObrigatorio", it)
        }

        filter.booleanoObrigatorio?.also {
            whereEq("$alias.booleanoObrigatorio", it)
        }

        filter.minDataObrigatoria?.also {
            whereGtEq("$alias.dataObrigatoria", it)
        }
        filter.maxDataObrigatoria?.also {
            whereLtEq("$alias.dataObrigatoria", it)
        }

        filter.minDatahoraObrigatoria?.also {
            whereGtEq("$alias.datahoraObrigatoria", it)
        }
        filter.maxDatahoraObrigatoria?.also {
            whereLtEq("$alias.datahoraObrigatoria", it)
        }

        filter.minDataCriacao?.also {
            whereGtEq("$alias.dataCriacao", it)
        }
        filter.maxDataCriacao?.also {
            whereLtEq("$alias.dataCriacao", it)
        }

        filter.minInteiroFacultativo?.also {
            whereGtEq("$alias.inteiroFacultativo", it)
        }
        filter.maxInteiroFacultativo?.also {
            whereLtEq("$alias.inteiroFacultativo", it)
        }

        filter.minDecimalFacultativo?.also {
            whereGtEq("$alias.decimalFacultativo", it)
        }
        filter.maxDecimalFacultativo?.also {
            whereLtEq("$alias.decimalFacultativo", it)
        }

        filter.booleanoFacultativo?.also {
            whereEq("$alias.booleanoFacultativo", it)
        }

        filter.minDataFacultativa?.also {
            whereGtEq("$alias.dataFacultativa", it)
        }
        filter.maxDataFacultativa?.also {
            whereLtEq("$alias.dataFacultativa", it)
        }

        filter.minDatahoraFacultativa?.also {
            whereGtEq("$alias.datahoraFacultativa", it)
        }
        filter.maxDatahoraFacultativa?.also {
            whereLtEq("$alias.datahoraFacultativa", it)
        }

        filter.minDataAlteracao?.also {
            whereGtEq("$alias.dataAlteracao", it)
        }
        filter.maxDataAlteracao?.also {
            whereLtEq("$alias.dataAlteracao", it)
        }

        filter.minPreco?.also {
            whereGtEq("$alias.preco", it)
        }
        filter.maxPreco?.also {
            whereLtEq("$alias.preco", it)
        }

        return this
    }

}

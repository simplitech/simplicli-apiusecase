package org.usecase.dao

import org.usecase.model.filter.PrincipalListFilter
import org.usecase.model.resource.Principal
import org.usecase.model.rm.PrincipalRM
import org.usecase.model.rm.GrupoDoPrincipalRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query

/**
 * Data Access Object of Principal from table principal
 * @author Simpli CLI generator
 */
class PrincipalDao(val con: AbstractConnector) {
    fun getOne(idPrincipalPk: Long): Principal? {
        // TODO: review generated method
        val query = Query()
                .selectPrincipal()
                .from("principal")
                .whereEq("idPrincipalPk", idPrincipalPk)

        return con.getOne(query) {
            PrincipalRM.build(it)
        }
    }

    fun getList(filter: PrincipalListFilter): MutableList<Principal> {
        // TODO: review generated method
        val query = Query()
                .selectFields(PrincipalRM.selectFields() + GrupoDoPrincipalRM.selectFields() + GrupoDoPrincipalRM.selectFields("grupo_do_principal_facultativo"))
                .from("principal")
                .innerJoin("grupo_do_principal", "grupo_do_principal.idGrupoDoPrincipalPk", "principal.idGrupoDoPrincipalFk")
                .leftJoin("grupo_do_principal AS grupo_do_principal_facultativo", "grupo_do_principal_facultativo.idGrupoDoPrincipalPk", "principal.idGrupoDoPrincipalFacultativoFk")
                .wherePrincipalFilter(filter)
                .orderAndLimitPrincipal(filter)

        return con.getList(query) {
            PrincipalRM.build(it).apply {
                grupoDoPrincipal = GrupoDoPrincipalRM.build(it)
                grupoDoPrincipalFacultativo = GrupoDoPrincipalRM.build(it, "grupo_do_principal_facultativo")
            }
        }
    }

    fun count(filter: PrincipalListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idPrincipalPk")
                .from("principal")
                .wherePrincipalFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(principal: Principal): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("principal")
                .updatePrincipalSet(principal)
                .whereEq("idPrincipalPk", principal.id)

        return con.execute(query).affectedRows
    }

    fun insert(principal: Principal): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("principal")
                .insertPrincipalValues(principal)

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

    private fun Query.selectPrincipal() = selectFields(PrincipalRM.selectFields())

    private fun Query.updatePrincipalSet(principal: Principal) = updateSet(PrincipalRM.updateSet(principal))

    private fun Query.insertPrincipalValues(principal: Principal) = insertValues(PrincipalRM.insertValues(principal))

    private fun Query.wherePrincipalFilter(filter: PrincipalListFilter, alias: String = "principal"): Query {
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

        filter.startDataObrigatoria?.also {
            where("DATE($alias.dataObrigatoria) >= DATE(?)", it)
        }
        filter.endDataObrigatoria?.also {
            where("DATE($alias.dataObrigatoria) <= DATE(?)", it)
        }

        filter.startDataFacultativa?.also {
            where("DATE($alias.dataFacultativa) >= DATE(?)", it)
        }
        filter.endDataFacultativa?.also {
            where("DATE($alias.dataFacultativa) <= DATE(?)", it)
        }

        filter.startDatahoraObrigatoria?.also {
            where("DATE($alias.datahoraObrigatoria) >= DATE(?)", it)
        }
        filter.endDatahoraObrigatoria?.also {
            where("DATE($alias.datahoraObrigatoria) <= DATE(?)", it)
        }

        filter.startDatahoraFacultativa?.also {
            where("DATE($alias.datahoraFacultativa) >= DATE(?)", it)
        }
        filter.endDatahoraFacultativa?.also {
            where("DATE($alias.datahoraFacultativa) <= DATE(?)", it)
        }

        filter.startDataCriacao?.also {
            where("DATE($alias.dataCriacao) >= DATE(?)", it)
        }
        filter.endDataCriacao?.also {
            where("DATE($alias.dataCriacao) <= DATE(?)", it)
        }

        filter.startDataAlteracao?.also {
            where("DATE($alias.dataAlteracao) >= DATE(?)", it)
        }
        filter.endDataAlteracao?.also {
            where("DATE($alias.dataAlteracao) <= DATE(?)", it)
        }

        filter.minDecimalObrigatorio?.also {
            whereGtEq("$alias.decimalObrigatorio", it)
        }
        filter.maxDecimalObrigatorio?.also {
            whereLtEq("$alias.decimalObrigatorio", it)
        }

        filter.minDecimalFacultativo?.also {
            whereGtEq("$alias.decimalFacultativo", it)
        }
        filter.maxDecimalFacultativo?.also {
            whereLtEq("$alias.decimalFacultativo", it)
        }

        filter.minInteiroObrigatorio?.also {
            whereGtEq("$alias.inteiroObrigatorio", it)
        }
        filter.maxInteiroObrigatorio?.also {
            whereLtEq("$alias.inteiroObrigatorio", it)
        }

        filter.minInteiroFacultativo?.also {
            whereGtEq("$alias.inteiroFacultativo", it)
        }
        filter.maxInteiroFacultativo?.also {
            whereLtEq("$alias.inteiroFacultativo", it)
        }

        filter.minPreco?.also {
            whereGtEq("$alias.preco", it)
        }
        filter.maxPreco?.also {
            whereLtEq("$alias.preco", it)
        }

        filter.booleanoObrigatorio?.also {
            whereEq("$alias.booleanoObrigatorio", it)
        }

        filter.booleanoFacultativo?.also {
            whereEq("$alias.booleanoFacultativo", it)
        }

        return this
    }

    private fun Query.orderAndLimitPrincipal(filter: PrincipalListFilter, alias: String = "principal"): Query {
        PrincipalRM.orderMap(alias)[filter.orderBy]?.also {
            orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            limit(index, it)
        }

        return this
    }
}

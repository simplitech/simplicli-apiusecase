package org.usecase.dao

import org.usecase.model.collection.ListFilter
import org.usecase.model.resource.Principal
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.ResultBuilder

/**
 * Data Access Object of Principal from table principal
 * @author Simpli CLI generator
 */
class PrincipalDao(val con: AbstractConnector) {

    fun getOne(idPrincipalPk: Long): Principal? {
        // TODO: review generated method
        val query = Query()
                .selectFields(Principal.selectFields())
                .from("principal")
                .whereEq("idPrincipalPk", idPrincipalPk)

        return con.getOne(query) {
            Principal(ResultBuilder(Principal.selectFields(), it, "principal"))
        }
    }

    fun getList(filter: ListFilter): MutableList<Principal> {
        // TODO: review generated method
        val query = Query()
                .selectFields(Principal.selectFields())
                .from("principal")
                .applyListFilter(filter)

        Principal.orderMap()[filter.orderBy]?.also {
            query.orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            query.limit(index, it)
        }

        return con.getList(query) {
            Principal(ResultBuilder(Principal.selectFields(), it, "principal"))
        }
    }

    fun count(filter: ListFilter): Int {
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
                .updateSet(principal.updateSet())
                .whereEq("idPrincipalPk", principal.id)

        return con.execute(query).affectedRows
    }

    fun insert(principal: Principal): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("principal")
                .insertValues(principal.insertValues())

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

    private fun Query.applyListFilter(filter: ListFilter, alias: String = "principal"): Query {
        whereEq("$alias.ativo", true)

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSome {
                    whereLike("$alias.idPrincipalPk", "%$it%")
                    whereLike("$alias.textoObrigatorio", "%$it%")
                    whereLike("$alias.textoFacultativo", "%$it%")
                    whereLike("$alias.email", "%$it%")
                    whereLike("$alias.unico", "%$it%")
                    whereLike("$alias.nome", "%$it%")
                    whereLike("$alias.titulo", "%$it%")
                    whereLike("$alias.cpf", "%$it%")
                    whereLike("$alias.cnpj", "%$it%")
                    whereLike("$alias.rg", "%$it%")
                    whereLike("$alias.celular", "%$it%")
                    whereLike("$alias.textoGrande", "%$it%")
                    whereLike("$alias.snake_case", "%$it%")
                }
            }
        }

        return this
    }

}

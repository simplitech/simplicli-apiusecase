package org.usecase.dao

import org.usecase.model.collection.ListFilter
import org.usecase.model.resource.Principal
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
                .selectAll()
                .from("principal")
                .whereEq("idPrincipalPk", idPrincipalPk)

        return con.getOne(query) {
            Principal(it)
        }
    }

    fun getList(filter: ListFilter): MutableList<Principal> {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("principal")
                .applyListFilter(filter)

        Principal.orderMap[filter.orderBy]?.also {
            query.orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            query.limit(index, it)
        }

        return con.getList(query) {
            Principal(it)
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

    private fun Query.applyListFilter(filter: ListFilter): Query {
        whereEq("principal.ativo", true)

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSome {
                    whereLike("principal.idPrincipalPk", "%$it%")
                    whereLike("principal.textoObrigatorio", "%$it%")
                    whereLike("principal.textoFacultativo", "%$it%")
                    whereLike("principal.email", "%$it%")
                    whereLike("principal.unico", "%$it%")
                    whereLike("principal.nome", "%$it%")
                    whereLike("principal.titulo", "%$it%")
                    whereLike("principal.cpf", "%$it%")
                    whereLike("principal.cnpj", "%$it%")
                    whereLike("principal.rg", "%$it%")
                    whereLike("principal.celular", "%$it%")
                    whereLike("principal.textoGrande", "%$it%")
                    whereLike("principal.snake_case", "%$it%")
                }
            }
        }

        return this
    }

}

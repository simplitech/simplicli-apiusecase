package org.usecase.dao

import org.usecase.model.filter.ListFilter
import org.usecase.model.resource.ConectorPrincipal
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query

/**
 * Data Access Object of ConectorPrincipal from table conector_principal
 * @author Simpli CLI generator
 */
class ConectorPrincipalDao(val con: AbstractConnector) {

    fun getOne(idPrincipalFk: Long, idConectadoFk: Long): ConectorPrincipal? {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("conector_principal")
                .whereEq("idPrincipalFk", idPrincipalFk)
                .whereEq("idConectadoFk", idConectadoFk)

        return con.getOne(query) {
            ConectorPrincipal(it)
        }
    }

    fun getList(filter: ListFilter): MutableList<ConectorPrincipal> {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("conector_principal")
                .applyListFilter(filter)

        ConectorPrincipal.orderMap[filter.orderBy]?.also {
            query.orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            query.limit(index, it)
        }

        return con.getList(query) {
            ConectorPrincipal(it)
        }
    }

    fun count(filter: ListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idPrincipalFk")
                .from("conector_principal")
                .applyListFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(conectorPrincipal: ConectorPrincipal): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("conector_principal")
                .updateSet(conectorPrincipal.updateSet())
                .whereEq("idPrincipalFk", conectorPrincipal.id1)
                .whereEq("idConectadoFk", conectorPrincipal.id2)

        return con.execute(query).affectedRows
    }

    fun insert(conectorPrincipal: ConectorPrincipal): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("conector_principal")
                .insertValues(conectorPrincipal.insertValues())

        return con.execute(query).key
    }

    fun exist(idPrincipalFk: Long, idConectadoFk: Long): Boolean {
        // TODO: review generated method
        val query = Query()
                .select("idPrincipalFk")
                .from("conector_principal")
                .whereEq("idPrincipalFk", idPrincipalFk)
                .whereEq("idConectadoFk", idConectadoFk)

        return con.exist(query)
    }


    private fun Query.applyListFilter(filter: ListFilter): Query {

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSome {
                    whereLike("conector_principal.idPrincipalFk", "%$it%")
                    whereLike("conector_principal.idConectadoFk", "%$it%")
                    whereLike("conector_principal.titulo", "%$it%")
                }
            }
        }

        return this
    }

}

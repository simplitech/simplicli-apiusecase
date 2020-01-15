package org.usecase.dao

import org.usecase.model.collection.ListFilter
import org.usecase.model.resource.GrupoDoPrincipal
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query

/**
 * Data Access Object of GrupoDoPrincipal from table grupo_do_principal
 * @author Simpli CLI generator
 */
class GrupoDoPrincipalDao(val con: AbstractConnector) {

    fun getOne(idGrupoDoPrincipalPk: Long): GrupoDoPrincipal? {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("grupo_do_principal")
                .whereEq("idGrupoDoPrincipalPk", idGrupoDoPrincipalPk)

        return con.getOne(query) {
            GrupoDoPrincipal(it)
        }
    }

    fun getList(filter: ListFilter): MutableList<GrupoDoPrincipal> {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("grupo_do_principal")
                .applyListFilter(filter)

        GrupoDoPrincipal.orderMap[filter.orderBy]?.also {
            query.orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            query.limit(index, it)
        }

        return con.getList(query) {
            GrupoDoPrincipal(it)
        }
    }

    fun count(filter: ListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idGrupoDoPrincipalPk")
                .from("grupo_do_principal")
                .applyListFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(grupoDoPrincipal: GrupoDoPrincipal): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("grupo_do_principal")
                .updateSet(grupoDoPrincipal.updateSet())
                .whereEq("idGrupoDoPrincipalPk", grupoDoPrincipal.id)

        return con.execute(query).affectedRows
    }

    fun insert(grupoDoPrincipal: GrupoDoPrincipal): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("grupo_do_principal")
                .insertValues(grupoDoPrincipal.insertValues())

        return con.execute(query).key
    }

    fun exist(idGrupoDoPrincipalPk: Long): Boolean {
        // TODO: review generated method
        val query = Query()
                .select("idGrupoDoPrincipalPk")
                .from("grupo_do_principal")
                .whereEq("idGrupoDoPrincipalPk", idGrupoDoPrincipalPk)

        return con.exist(query)
    }


    private fun Query.applyListFilter(filter: ListFilter): Query {

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSome {
                    whereLike("grupo_do_principal.idGrupoDoPrincipalPk", "%$it%")
                    whereLike("grupo_do_principal.titulo", "%$it%")
                }
            }
        }

        return this
    }

}

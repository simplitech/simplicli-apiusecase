package org.usecase.dao

import org.usecase.model.collection.ListFilter
import org.usecase.model.resource.ExtensaoDoPrincipal
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query

/**
 * Data Access Object of ExtensaoDoPrincipal from table extensao_do_principal
 * @author Simpli CLI generator
 */
class ExtensaoDoPrincipalDao(val con: AbstractConnector) {

    fun getOne(idPrincipalFk: Long): ExtensaoDoPrincipal? {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("extensao_do_principal")
                .whereEq("idPrincipalFk", idPrincipalFk)

        return con.getOne(query) {
            ExtensaoDoPrincipal(it)
        }
    }

    fun getList(filter: ListFilter): MutableList<ExtensaoDoPrincipal> {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("extensao_do_principal")
                .applyListFilter(filter)

        ExtensaoDoPrincipal.orderMap[filter.orderBy]?.also {
            query.orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            query.limit(index, it)
        }

        return con.getList(query) {
            ExtensaoDoPrincipal(it)
        }
    }

    fun count(filter: ListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idPrincipalFk")
                .from("extensao_do_principal")
                .applyListFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(extensaoDoPrincipal: ExtensaoDoPrincipal): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("extensao_do_principal")
                .updateSet(extensaoDoPrincipal.updateSet())
                .whereEq("idPrincipalFk", extensaoDoPrincipal.id)

        return con.execute(query).affectedRows
    }

    fun insert(extensaoDoPrincipal: ExtensaoDoPrincipal): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("extensao_do_principal")
                .insertValues(extensaoDoPrincipal.insertValues())

        return con.execute(query).key
    }

    fun exist(idPrincipalFk: Long): Boolean {
        // TODO: review generated method
        val query = Query()
                .select("idPrincipalFk")
                .from("extensao_do_principal")
                .whereEq("idPrincipalFk", idPrincipalFk)

        return con.exist(query)
    }


    private fun Query.applyListFilter(filter: ListFilter): Query {

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSome {
                    whereLike("extensao_do_principal.idPrincipalFk", "%$it%")
                    whereLike("extensao_do_principal.titulo", "%$it%")
                }
            }
        }

        return this
    }

}

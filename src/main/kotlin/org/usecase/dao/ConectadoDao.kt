package org.usecase.dao

import org.usecase.model.filter.ListFilter
import org.usecase.model.resource.Conectado
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query

/**
 * Data Access Object of Conectado from table conectado
 * @author Simpli CLI generator
 */
class ConectadoDao(val con: AbstractConnector) {

    fun getOne(idConectadoPk: Long): Conectado? {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("conectado")
                .whereEq("idConectadoPk", idConectadoPk)

        return con.getOne(query) {
            Conectado(it)
        }
    }

    fun getList(filter: ListFilter): MutableList<Conectado> {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("conectado")
                .applyListFilter(filter)

        Conectado.orderMap[filter.orderBy]?.also {
            query.orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            query.limit(index, it)
        }

        return con.getList(query) {
            Conectado(it)
        }
    }

    fun count(filter: ListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idConectadoPk")
                .from("conectado")
                .applyListFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(conectado: Conectado): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("conectado")
                .updateSet(conectado.updateSet())
                .whereEq("idConectadoPk", conectado.id)

        return con.execute(query).affectedRows
    }

    fun insert(conectado: Conectado): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("conectado")
                .insertValues(conectado.insertValues())

        return con.execute(query).key
    }

    fun exist(idConectadoPk: Long): Boolean {
        // TODO: review generated method
        val query = Query()
                .select("idConectadoPk")
                .from("conectado")
                .whereEq("idConectadoPk", idConectadoPk)

        return con.exist(query)
    }


    private fun Query.applyListFilter(filter: ListFilter): Query {

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSome {
                    whereLike("conectado.idConectadoPk", "%$it%")
                    whereLike("conectado.titulo", "%$it%")
                }
            }
        }

        return this
    }

}

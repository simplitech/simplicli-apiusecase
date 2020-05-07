package org.usecase.dao

import org.usecase.model.filter.ConectadoListFilter
import org.usecase.model.resource.Conectado
import org.usecase.model.rm.ConectadoRM
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
                .selectConectado()
                .from("conectado")
                .whereEq("idConectadoPk", idConectadoPk)

        return con.getOne(query) {
            ConectadoRM.build(it)
        }
    }

    fun getList(filter: ConectadoListFilter): MutableList<Conectado> {
        // TODO: review generated method
        val query = Query()
                .selectConectado()
                .from("conectado")
                .whereConectadoFilter(filter)
                .orderAndLimitConectado(filter)

        return con.getList(query) {
            ConectadoRM.build(it)
        }
    }

    fun count(filter: ConectadoListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idConectadoPk")
                .from("conectado")
                .whereConectadoFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(conectado: Conectado): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("conectado")
                .updateConectadoSet(conectado)
                .whereEq("idConectadoPk", conectado.id)

        return con.execute(query).affectedRows
    }

    fun insert(conectado: Conectado): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("conectado")
                .insertConectadoValues(conectado)

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

    private fun Query.selectConectado() = selectFields(ConectadoRM.selectFields())

    private fun Query.updateConectadoSet(conectado: Conectado) = updateSet(ConectadoRM.updateSet(conectado))

    private fun Query.insertConectadoValues(conectado: Conectado) = insertValues(ConectadoRM.insertValues(conectado))

    private fun Query.whereConectadoFilter(filter: ConectadoListFilter, alias: String = "conectado"): Query {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(ConectadoRM.fieldsToSearch(alias), "%$it%")
            }
        }

        return this
    }

    private fun Query.orderAndLimitConectado(filter: ConectadoListFilter, alias: String = "conectado"): Query {
        ConectadoRM.orderMap(alias)[filter.orderBy]?.also {
            orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            limit(index, it)
        }

        return this
    }
}

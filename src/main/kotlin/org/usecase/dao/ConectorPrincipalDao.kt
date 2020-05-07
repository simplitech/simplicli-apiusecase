package org.usecase.dao

import org.usecase.model.filter.ConectorPrincipalListFilter
import org.usecase.model.resource.ConectorPrincipal
import org.usecase.model.rm.ConectorPrincipalRM
import org.usecase.model.rm.ConectadoRM
import org.usecase.model.rm.PrincipalRM
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
                .selectConectorPrincipal()
                .from("conector_principal")
                .whereEq("idPrincipalFk", idPrincipalFk)
                .whereEq("idConectadoFk", idConectadoFk)

        return con.getOne(query) {
            ConectorPrincipalRM.build(it)
        }
    }

    fun getList(filter: ConectorPrincipalListFilter): MutableList<ConectorPrincipal> {
        // TODO: review generated method
        val query = Query()
                .selectFields(ConectorPrincipalRM.selectFields() + ConectadoRM.selectFields() + PrincipalRM.selectFields())
                .from("conector_principal")
                .innerJoin("conectado", "conectado.idConectadoPk", "conector_principal.idConectadoFk")
                .innerJoin("principal", "principal.idprincipalpk", "conector_principal.idPrincipalFk")
                .whereConectorPrincipalFilter(filter)
                .orderAndLimitConectorPrincipal(filter)

        return con.getList(query) {
            ConectorPrincipalRM.build(it).apply {
                conectado = ConectadoRM.build(it)
                principal = PrincipalRM.build(it)
            }
        }
    }

    fun count(filter: ConectorPrincipalListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idPrincipalFk")
                .from("conector_principal")
                .whereConectorPrincipalFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(conectorPrincipal: ConectorPrincipal): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("conector_principal")
                .updateConectorPrincipalSet(conectorPrincipal)
                .whereEq("idPrincipalFk", conectorPrincipal.id1)
                .whereEq("idConectadoFk", conectorPrincipal.id2)

        return con.execute(query).affectedRows
    }

    fun insert(conectorPrincipal: ConectorPrincipal): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("conector_principal")
                .insertConectorPrincipalValues(conectorPrincipal)

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

    private fun Query.selectConectorPrincipal() = selectFields(ConectorPrincipalRM.selectFields())

    private fun Query.updateConectorPrincipalSet(conectorPrincipal: ConectorPrincipal) = updateSet(ConectorPrincipalRM.updateSet(conectorPrincipal))

    private fun Query.insertConectorPrincipalValues(conectorPrincipal: ConectorPrincipal) = insertValues(ConectorPrincipalRM.insertValues(conectorPrincipal))

    private fun Query.whereConectorPrincipalFilter(filter: ConectorPrincipalListFilter, alias: String = "conector_principal"): Query {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(ConectorPrincipalRM.fieldsToSearch(alias), "%$it%")
            }
        }

        return this
    }

    private fun Query.orderAndLimitConectorPrincipal(filter: ConectorPrincipalListFilter, alias: String = "conector_principal"): Query {
        ConectorPrincipalRM.orderMap(alias)[filter.orderBy]?.also {
            orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            limit(index, it)
        }

        return this
    }
}

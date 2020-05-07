package org.usecase.dao

import org.usecase.model.filter.GrupoDoPrincipalListFilter
import org.usecase.model.resource.GrupoDoPrincipal
import org.usecase.model.rm.GrupoDoPrincipalRM
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
                .selectGrupoDoPrincipal()
                .from("grupo_do_principal")
                .whereEq("idGrupoDoPrincipalPk", idGrupoDoPrincipalPk)

        return con.getOne(query) {
            GrupoDoPrincipalRM.build(it)
        }
    }

    fun getList(filter: GrupoDoPrincipalListFilter): MutableList<GrupoDoPrincipal> {
        // TODO: review generated method
        val query = Query()
                .selectGrupoDoPrincipal()
                .from("grupo_do_principal")
                .whereGrupoDoPrincipalFilter(filter)
                .orderAndLimitGrupoDoPrincipal(filter)

        return con.getList(query) {
            GrupoDoPrincipalRM.build(it)
        }
    }

    fun count(filter: GrupoDoPrincipalListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idGrupoDoPrincipalPk")
                .from("grupo_do_principal")
                .whereGrupoDoPrincipalFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(grupoDoPrincipal: GrupoDoPrincipal): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("grupo_do_principal")
                .updateGrupoDoPrincipalSet(grupoDoPrincipal)
                .whereEq("idGrupoDoPrincipalPk", grupoDoPrincipal.id)

        return con.execute(query).affectedRows
    }

    fun insert(grupoDoPrincipal: GrupoDoPrincipal): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("grupo_do_principal")
                .insertGrupoDoPrincipalValues(grupoDoPrincipal)

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

    private fun Query.selectGrupoDoPrincipal() = selectFields(GrupoDoPrincipalRM.selectFields())

    private fun Query.updateGrupoDoPrincipalSet(grupoDoPrincipal: GrupoDoPrincipal) = updateSet(GrupoDoPrincipalRM.updateSet(grupoDoPrincipal))

    private fun Query.insertGrupoDoPrincipalValues(grupoDoPrincipal: GrupoDoPrincipal) = insertValues(GrupoDoPrincipalRM.insertValues(grupoDoPrincipal))

    private fun Query.whereGrupoDoPrincipalFilter(filter: GrupoDoPrincipalListFilter, alias: String = "grupo_do_principal"): Query {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(GrupoDoPrincipalRM.fieldsToSearch(alias), "%$it%")
            }
        }

        return this
    }

    private fun Query.orderAndLimitGrupoDoPrincipal(filter: GrupoDoPrincipalListFilter, alias: String = "grupo_do_principal"): Query {
        GrupoDoPrincipalRM.orderMap(alias)[filter.orderBy]?.also {
            orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            limit(index, it)
        }

        return this
    }
}

package org.usecase.dao

import org.usecase.model.filter.ExtensaoDoPrincipalListFilter
import org.usecase.model.resource.ExtensaoDoPrincipal
import org.usecase.model.rm.ExtensaoDoPrincipalRM
import org.usecase.model.rm.PrincipalRM
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
                .selectExtensaoDoPrincipal()
                .from("extensao_do_principal")
                .whereEq("idPrincipalFk", idPrincipalFk)

        return con.getOne(query) {
            ExtensaoDoPrincipalRM.build(it)
        }
    }

    fun getList(filter: ExtensaoDoPrincipalListFilter): MutableList<ExtensaoDoPrincipal> {
        // TODO: review generated method
        val query = Query()
                .selectFields(ExtensaoDoPrincipalRM.selectFields() + PrincipalRM.selectFields())
                .from("extensao_do_principal")
                .innerJoin("principal", "principal.idprincipalpk", "extensao_do_principal.idPrincipalFk")
                .whereExtensaoDoPrincipalFilter(filter)
                .orderAndLimitExtensaoDoPrincipal(filter)

        return con.getList(query) {
            ExtensaoDoPrincipalRM.build(it).apply {
                principal = PrincipalRM.build(it)
            }
        }
    }

    fun count(filter: ExtensaoDoPrincipalListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idPrincipalFk")
                .from("extensao_do_principal")
                .whereExtensaoDoPrincipalFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(extensaoDoPrincipal: ExtensaoDoPrincipal): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("extensao_do_principal")
                .updateExtensaoDoPrincipalSet(extensaoDoPrincipal)
                .whereEq("idPrincipalFk", extensaoDoPrincipal.id)

        return con.execute(query).affectedRows
    }

    fun insert(extensaoDoPrincipal: ExtensaoDoPrincipal): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("extensao_do_principal")
                .insertExtensaoDoPrincipalValues(extensaoDoPrincipal)

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

    private fun Query.selectExtensaoDoPrincipal() = selectFields(ExtensaoDoPrincipalRM.selectFields())

    private fun Query.updateExtensaoDoPrincipalSet(extensaoDoPrincipal: ExtensaoDoPrincipal) = updateSet(ExtensaoDoPrincipalRM.updateSet(extensaoDoPrincipal))

    private fun Query.insertExtensaoDoPrincipalValues(extensaoDoPrincipal: ExtensaoDoPrincipal) = insertValues(ExtensaoDoPrincipalRM.insertValues(extensaoDoPrincipal))

    private fun Query.whereExtensaoDoPrincipalFilter(filter: ExtensaoDoPrincipalListFilter, alias: String = "extensao_do_principal"): Query {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(ExtensaoDoPrincipalRM.fieldsToSearch(alias), "%$it%")
            }
        }

        return this
    }

    private fun Query.orderAndLimitExtensaoDoPrincipal(filter: ExtensaoDoPrincipalListFilter, alias: String = "extensao_do_principal"): Query {
        ExtensaoDoPrincipalRM.orderMap(alias)[filter.orderBy]?.also {
            orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            limit(index, it)
        }

        return this
    }
}

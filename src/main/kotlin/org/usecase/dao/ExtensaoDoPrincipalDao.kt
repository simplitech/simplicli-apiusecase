package org.usecase.dao

import org.usecase.model.filter.ExtensaoDoPrincipalListFilter
import org.usecase.model.resource.ExtensaoDoPrincipal
import org.usecase.model.rm.ExtensaoDoPrincipalRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.rm.PrincipalRM
import org.usecase.model.resource.Permission

/**
 * Data Access Object of ExtensaoDoPrincipal from table extensao_do_principal
 * @author Simpli CLI generator
 */
class ExtensaoDoPrincipalDao(val con: AbstractConnector, val permission: PermissionGroup) {
    fun getOne(idPrincipalFk: Long): ExtensaoDoPrincipal? {
        val extensaoDoPrincipalRm = ExtensaoDoPrincipalRM(permission)
        val principalRm = PrincipalRM(permission)

        val vs = VirtualSelect()
                .selectFields(extensaoDoPrincipalRm.selectFields)
                .selectFields(principalRm.selectFields)
                .from(extensaoDoPrincipalRm)
                .innerJoin(principalRm, principalRm.idPrincipalPk, extensaoDoPrincipalRm.idPrincipalFk)
                .whereEq(extensaoDoPrincipalRm.idPrincipalFk, idPrincipalFk)

        return con.getOne(vs.toQuery()) {
            extensaoDoPrincipalRm.build(it).apply {
                principal = principalRm.build(it)
            }
        }
    }

    fun getList(filter: ExtensaoDoPrincipalListFilter): MutableList<ExtensaoDoPrincipal> {
        val extensaoDoPrincipalRm = ExtensaoDoPrincipalRM(permission)
        val principalRm = PrincipalRM(permission)

        val vs = VirtualSelect()
                .selectFields(extensaoDoPrincipalRm.selectFields)
                .selectFields(principalRm.selectFields)
                .from(extensaoDoPrincipalRm)
                .innerJoin(principalRm, principalRm.idPrincipalPk, extensaoDoPrincipalRm.idPrincipalFk)
                .whereExtensaoDoPrincipalFilter(extensaoDoPrincipalRm, filter)
                .orderAndLimitExtensaoDoPrincipal(extensaoDoPrincipalRm, filter)

        return con.getList(vs.toQuery()) {
            extensaoDoPrincipalRm.build(it).apply {
                principal = principalRm.build(it)
            }
        }
    }

    fun count(filter: ExtensaoDoPrincipalListFilter): Int {
        val extensaoDoPrincipalRm = ExtensaoDoPrincipalRM(permission)
        val principalRm = PrincipalRM(permission)

        val vs = VirtualSelect()
                .selectRaw("COUNT(DISTINCT %s)", extensaoDoPrincipalRm.idPrincipalFk)
                .from(extensaoDoPrincipalRm)
                .innerJoin(principalRm, principalRm.idPrincipalPk, extensaoDoPrincipalRm.idPrincipalFk)
                .whereExtensaoDoPrincipalFilter(extensaoDoPrincipalRm, filter)

        return con.getFirstInt(vs.toQuery()) ?: 0
    }

    fun update(extensaoDoPrincipal: ExtensaoDoPrincipal): Int {
        val extensaoDoPrincipalRm = ExtensaoDoPrincipalRM(permission)
        val query = Query()
                .updateTable(extensaoDoPrincipalRm.table)
                .updateSet(extensaoDoPrincipalRm.updateSet(extensaoDoPrincipal))
                .whereEq(extensaoDoPrincipalRm.idPrincipalFk.column, extensaoDoPrincipal.id)

        return con.execute(query).affectedRows
    }

    fun insert(extensaoDoPrincipal: ExtensaoDoPrincipal): Long {
        val extensaoDoPrincipalRm = ExtensaoDoPrincipalRM(permission)
        val query = Query()
                .insertInto(extensaoDoPrincipalRm.table)
                .insertValues(extensaoDoPrincipalRm.insertValues(extensaoDoPrincipal))

        return con.execute(query).key
    }

    fun exist(idPrincipalFk: Long): Boolean {
        val extensaoDoPrincipalRm = ExtensaoDoPrincipalRM(permission)
        val vs = VirtualSelect()
                .select(extensaoDoPrincipalRm.idPrincipalFk)
                .from(extensaoDoPrincipalRm)
                .whereEq(extensaoDoPrincipalRm.idPrincipalFk, idPrincipalFk)

        return con.exist(vs.toQuery())
    }

    private fun VirtualSelect.whereExtensaoDoPrincipalFilter(extensaoDoPrincipalRm: ExtensaoDoPrincipalRM, filter: ExtensaoDoPrincipalListFilter): VirtualSelect {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(extensaoDoPrincipalRm.fieldsToSearch, "%$it%")
            }
        }

        return this
    }

    private fun VirtualSelect.orderAndLimitExtensaoDoPrincipal(extensaoDoPrincipalRm: ExtensaoDoPrincipalRM, filter: ExtensaoDoPrincipalListFilter): VirtualSelect {
        orderBy(extensaoDoPrincipalRm.orderMap, filter.orderBy to filter.ascending)

        limitByPage(filter.page, filter.limit)

        return this
    }
}

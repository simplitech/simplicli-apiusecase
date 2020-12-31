package org.usecase.dao

import org.usecase.model.filter.ItemDoPrincipalListFilter
import org.usecase.model.resource.ItemDoPrincipal
import org.usecase.model.rm.ItemDoPrincipalRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.rm.PrincipalRM
import org.usecase.model.resource.Permission

/**
 * Data Access Object of ItemDoPrincipal from table item_do_principal
 * @author Simpli CLI generator
 */
class ItemDoPrincipalDao(val con: AbstractConnector, val permission: PermissionGroup) {
    fun getOne(idItemDoPrincipalPk: Long): ItemDoPrincipal? {
        val itemDoPrincipalRm = ItemDoPrincipalRM(permission)
        val principalRm = PrincipalRM(permission)

        val vs = VirtualSelect()
                .selectFields(itemDoPrincipalRm.selectFields)
                .selectFields(principalRm.selectFields)
                .from(itemDoPrincipalRm)
                .innerJoin(principalRm, principalRm.idPrincipalPk, itemDoPrincipalRm.idPrincipalFk)
                .whereEq(itemDoPrincipalRm.idItemDoPrincipalPk, idItemDoPrincipalPk)

        return con.getOne(vs.toQuery()) {
            itemDoPrincipalRm.build(it).apply {
                principal = principalRm.build(it)
            }
        }
    }

    fun getList(filter: ItemDoPrincipalListFilter): MutableList<ItemDoPrincipal> {
        val itemDoPrincipalRm = ItemDoPrincipalRM(permission)
        val principalRm = PrincipalRM(permission)

        val vs = VirtualSelect()
                .selectFields(itemDoPrincipalRm.selectFields)
                .selectFields(principalRm.selectFields)
                .from(itemDoPrincipalRm)
                .innerJoin(principalRm, principalRm.idPrincipalPk, itemDoPrincipalRm.idPrincipalFk)
                .whereItemDoPrincipalFilter(itemDoPrincipalRm, filter)
                .orderAndLimitItemDoPrincipal(itemDoPrincipalRm, filter)

        return con.getList(vs.toQuery()) {
            itemDoPrincipalRm.build(it).apply {
                principal = principalRm.build(it)
            }
        }
    }

    fun count(filter: ItemDoPrincipalListFilter): Int {
        val itemDoPrincipalRm = ItemDoPrincipalRM(permission)
        val principalRm = PrincipalRM(permission)

        val vs = VirtualSelect()
                .selectRaw("COUNT(DISTINCT %s)", itemDoPrincipalRm.idItemDoPrincipalPk)
                .from(itemDoPrincipalRm)
                .innerJoin(principalRm, principalRm.idPrincipalPk, itemDoPrincipalRm.idPrincipalFk)
                .whereItemDoPrincipalFilter(itemDoPrincipalRm, filter)

        return con.getFirstInt(vs.toQuery()) ?: 0
    }

    fun update(itemDoPrincipal: ItemDoPrincipal): Int {
        val itemDoPrincipalRm = ItemDoPrincipalRM(permission)
        val query = Query()
                .updateTable(itemDoPrincipalRm.table)
                .updateSet(itemDoPrincipalRm.updateSet(itemDoPrincipal))
                .whereEq(itemDoPrincipalRm.idItemDoPrincipalPk.column, itemDoPrincipal.id)

        return con.execute(query).affectedRows
    }

    fun insert(itemDoPrincipal: ItemDoPrincipal): Long {
        val itemDoPrincipalRm = ItemDoPrincipalRM(permission)
        val query = Query()
                .insertInto(itemDoPrincipalRm.table)
                .insertValues(itemDoPrincipalRm.insertValues(itemDoPrincipal))

        return con.execute(query).key
    }

    fun exist(idItemDoPrincipalPk: Long): Boolean {
        val itemDoPrincipalRm = ItemDoPrincipalRM(permission)
        val vs = VirtualSelect()
                .select(itemDoPrincipalRm.idItemDoPrincipalPk)
                .from(itemDoPrincipalRm)
                .whereEq(itemDoPrincipalRm.idItemDoPrincipalPk, idItemDoPrincipalPk)

        return con.exist(vs.toQuery())
    }

    private fun VirtualSelect.whereItemDoPrincipalFilter(itemDoPrincipalRm: ItemDoPrincipalRM, filter: ItemDoPrincipalListFilter): VirtualSelect {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(itemDoPrincipalRm.fieldsToSearch, "%$it%")
            }
        }

        filter.idPrincipalFk?.also {
            if (it.isNotEmpty()) {
                whereIn(itemDoPrincipalRm.idPrincipalFk, *it.toTypedArray())
            }
        }

        return this
    }

    private fun VirtualSelect.orderAndLimitItemDoPrincipal(itemDoPrincipalRm: ItemDoPrincipalRM, filter: ItemDoPrincipalListFilter): VirtualSelect {
        orderBy(itemDoPrincipalRm.orderMap, filter.orderBy to filter.ascending)

        limitByPage(filter.page, filter.limit)

        return this
    }
}

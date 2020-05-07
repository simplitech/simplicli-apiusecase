package org.usecase.dao

import org.usecase.model.filter.ItemDoPrincipalListFilter
import org.usecase.model.resource.ItemDoPrincipal
import org.usecase.model.rm.ItemDoPrincipalRM
import org.usecase.model.rm.PrincipalRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query

/**
 * Data Access Object of ItemDoPrincipal from table item_do_principal
 * @author Simpli CLI generator
 */
class ItemDoPrincipalDao(val con: AbstractConnector) {
    fun getOne(idItemDoPrincipalPk: Long): ItemDoPrincipal? {
        // TODO: review generated method
        val query = Query()
                .selectItemDoPrincipal()
                .from("item_do_principal")
                .whereEq("idItemDoPrincipalPk", idItemDoPrincipalPk)

        return con.getOne(query) {
            ItemDoPrincipalRM.build(it)
        }
    }

    fun getList(filter: ItemDoPrincipalListFilter): MutableList<ItemDoPrincipal> {
        // TODO: review generated method
        val query = Query()
                .selectFields(ItemDoPrincipalRM.selectFields() + PrincipalRM.selectFields())
                .from("item_do_principal")
                .innerJoin("principal", "principal.idprincipalpk", "item_do_principal.idPrincipalFk")
                .whereItemDoPrincipalFilter(filter)
                .orderAndLimitItemDoPrincipal(filter)

        return con.getList(query) {
            ItemDoPrincipalRM.build(it).apply {
                principal = PrincipalRM.build(it)
            }
        }
    }

    fun count(filter: ItemDoPrincipalListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idItemDoPrincipalPk")
                .from("item_do_principal")
                .whereItemDoPrincipalFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(itemDoPrincipal: ItemDoPrincipal): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("item_do_principal")
                .updateItemDoPrincipalSet(itemDoPrincipal)
                .whereEq("idItemDoPrincipalPk", itemDoPrincipal.id)

        return con.execute(query).affectedRows
    }

    fun insert(itemDoPrincipal: ItemDoPrincipal): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("item_do_principal")
                .insertItemDoPrincipalValues(itemDoPrincipal)

        return con.execute(query).key
    }

    fun exist(idItemDoPrincipalPk: Long): Boolean {
        // TODO: review generated method
        val query = Query()
                .select("idItemDoPrincipalPk")
                .from("item_do_principal")
                .whereEq("idItemDoPrincipalPk", idItemDoPrincipalPk)

        return con.exist(query)
    }

    private fun Query.selectItemDoPrincipal() = selectFields(ItemDoPrincipalRM.selectFields())

    private fun Query.updateItemDoPrincipalSet(itemDoPrincipal: ItemDoPrincipal) = updateSet(ItemDoPrincipalRM.updateSet(itemDoPrincipal))

    private fun Query.insertItemDoPrincipalValues(itemDoPrincipal: ItemDoPrincipal) = insertValues(ItemDoPrincipalRM.insertValues(itemDoPrincipal))

    private fun Query.whereItemDoPrincipalFilter(filter: ItemDoPrincipalListFilter, alias: String = "item_do_principal"): Query {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(ItemDoPrincipalRM.fieldsToSearch(alias), "%$it%")
            }
        }

        filter.idPrincipalFk?.also {
            if (it.isNotEmpty()) {
                whereIn("$alias.idPrincipalFk", *it.toTypedArray())
            }
        }

        return this
    }

    private fun Query.orderAndLimitItemDoPrincipal(filter: ItemDoPrincipalListFilter, alias: String = "item_do_principal"): Query {
        ItemDoPrincipalRM.orderMap(alias)[filter.orderBy]?.also {
            orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            limit(index, it)
        }

        return this
    }
}

package org.usecase.dao

import org.usecase.model.filter.ListFilter
import org.usecase.model.resource.ItemDoPrincipal
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
                .selectAll()
                .from("item_do_principal")
                .whereEq("idItemDoPrincipalPk", idItemDoPrincipalPk)

        return con.getOne(query) {
            ItemDoPrincipal(it)
        }
    }

    fun getList(filter: ListFilter): MutableList<ItemDoPrincipal> {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("item_do_principal")
                .applyListFilter(filter)

        ItemDoPrincipal.orderMap[filter.orderBy]?.also {
            query.orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            query.limit(index, it)
        }

        return con.getList(query) {
            ItemDoPrincipal(it)
        }
    }

    fun count(filter: ListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idItemDoPrincipalPk")
                .from("item_do_principal")
                .applyListFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(itemDoPrincipal: ItemDoPrincipal): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("item_do_principal")
                .updateSet(itemDoPrincipal.updateSet())
                .whereEq("idItemDoPrincipalPk", itemDoPrincipal.id)

        return con.execute(query).affectedRows
    }

    fun insert(itemDoPrincipal: ItemDoPrincipal): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("item_do_principal")
                .insertValues(itemDoPrincipal.insertValues())

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


    private fun Query.applyListFilter(filter: ListFilter): Query {

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSome {
                    whereLike("item_do_principal.idItemDoPrincipalPk", "%$it%")
                    whereLike("item_do_principal.titulo", "%$it%")
                }
            }
        }

        return this
    }

}

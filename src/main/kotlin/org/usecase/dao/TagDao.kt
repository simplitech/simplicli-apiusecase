package org.usecase.dao

import org.usecase.model.filter.ListFilter
import org.usecase.model.resource.Tag
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query

/**
 * Data Access Object of Tag from table tag
 * @author Simpli CLI generator
 */
class TagDao(val con: AbstractConnector) {

    fun getOne(idTagPk: Long): Tag? {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("tag")
                .whereEq("idTagPk", idTagPk)

        return con.getOne(query) {
            Tag(it)
        }
    }

    fun getList(filter: ListFilter): MutableList<Tag> {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("tag")
                .applyListFilter(filter)

        Tag.orderMap[filter.orderBy]?.also {
            query.orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            query.limit(index, it)
        }

        return con.getList(query) {
            Tag(it)
        }
    }

    fun count(filter: ListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idTagPk")
                .from("tag")
                .applyListFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(tag: Tag): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("tag")
                .updateSet(tag.updateSet())
                .whereEq("idTagPk", tag.id)

        return con.execute(query).affectedRows
    }

    fun insert(tag: Tag): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("tag")
                .insertValues(tag.insertValues())

        return con.execute(query).key
    }

    fun exist(idTagPk: Long): Boolean {
        // TODO: review generated method
        val query = Query()
                .select("idTagPk")
                .from("tag")
                .whereEq("idTagPk", idTagPk)

        return con.exist(query)
    }


    private fun Query.applyListFilter(filter: ListFilter): Query {

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSome {
                    whereLike("tag.idTagPk", "%$it%")
                    whereLike("tag.titulo", "%$it%")
                }
            }
        }

        return this
    }

}

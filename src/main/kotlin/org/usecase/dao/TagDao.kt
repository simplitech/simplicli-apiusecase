package org.usecase.dao

import org.usecase.model.filter.TagListFilter
import org.usecase.model.resource.Tag
import org.usecase.model.rm.TagRM
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
                .selectTag()
                .from("tag")
                .whereEq("idTagPk", idTagPk)

        return con.getOne(query) {
            TagRM.build(it)
        }
    }

    fun getList(filter: TagListFilter): MutableList<Tag> {
        // TODO: review generated method
        val query = Query()
                .selectTag()
                .from("tag")
                .whereTagFilter(filter)
                .orderAndLimitTag(filter)

        return con.getList(query) {
            TagRM.build(it)
        }
    }

    fun count(filter: TagListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idTagPk")
                .from("tag")
                .whereTagFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(tag: Tag): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("tag")
                .updateTagSet(tag)
                .whereEq("idTagPk", tag.id)

        return con.execute(query).affectedRows
    }

    fun insert(tag: Tag): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("tag")
                .insertTagValues(tag)

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

    private fun Query.selectTag() = selectFields(TagRM.selectFields())

    private fun Query.updateTagSet(tag: Tag) = updateSet(TagRM.updateSet(tag))

    private fun Query.insertTagValues(tag: Tag) = insertValues(TagRM.insertValues(tag))

    private fun Query.whereTagFilter(filter: TagListFilter, alias: String = "tag"): Query {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(TagRM.fieldsToSearch(alias), "%$it%")
            }
        }

        return this
    }

    private fun Query.orderAndLimitTag(filter: TagListFilter, alias: String = "tag"): Query {
        TagRM.orderMap(alias)[filter.orderBy]?.also {
            orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            limit(index, it)
        }

        return this
    }
}

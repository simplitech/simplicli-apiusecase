package org.usecase.dao

import org.usecase.model.filter.TagListFilter
import org.usecase.model.resource.Tag
import org.usecase.model.rm.TagRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission

/**
 * Data Access Object of Tag from table tag
 * @author Simpli CLI generator
 */
class TagDao(val con: AbstractConnector, val permission: PermissionGroup) {
    fun getOne(idTagPk: Long): Tag? {
        val tagRm = TagRM(permission)

        val vs = VirtualSelect()
                .selectFields(tagRm.selectFields)
                .from(tagRm)
                .whereEq(tagRm.idTagPk, idTagPk)

        return con.getOne(vs.toQuery()) {
            tagRm.build(it)
        }
    }

    fun getList(filter: TagListFilter): MutableList<Tag> {
        val tagRm = TagRM(permission)

        val vs = VirtualSelect()
                .selectFields(tagRm.selectFields)
                .from(tagRm)
                .whereTagFilter(tagRm, filter)
                .orderAndLimitTag(tagRm, filter)

        return con.getList(vs.toQuery()) {
            tagRm.build(it)
        }
    }

    fun count(filter: TagListFilter): Int {
        val tagRm = TagRM(permission)

        val vs = VirtualSelect()
                .selectRaw("COUNT(DISTINCT %s)", tagRm.idTagPk)
                .from(tagRm)
                .whereTagFilter(tagRm, filter)

        return con.getFirstInt(vs.toQuery()) ?: 0
    }

    fun update(tag: Tag): Int {
        val tagRm = TagRM(permission)
        val query = Query()
                .updateTable(tagRm.table)
                .updateSet(tagRm.updateSet(tag))
                .whereEq(tagRm.idTagPk.column, tag.id)

        return con.execute(query).affectedRows
    }

    fun insert(tag: Tag): Long {
        val tagRm = TagRM(permission)
        val query = Query()
                .insertInto(tagRm.table)
                .insertValues(tagRm.insertValues(tag))

        return con.execute(query).key
    }

    fun exist(idTagPk: Long): Boolean {
        val tagRm = TagRM(permission)
        val vs = VirtualSelect()
                .select(tagRm.idTagPk)
                .from(tagRm)
                .whereEq(tagRm.idTagPk, idTagPk)

        return con.exist(vs.toQuery())
    }

    private fun VirtualSelect.whereTagFilter(tagRm: TagRM, filter: TagListFilter): VirtualSelect {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(tagRm.fieldsToSearch, "%$it%")
            }
        }

        return this
    }

    private fun VirtualSelect.orderAndLimitTag(tagRm: TagRM, filter: TagListFilter): VirtualSelect {
        orderBy(tagRm.orderMap, filter.orderBy to filter.ascending)

        limitByPage(filter.page, filter.limit)

        return this
    }
}

package org.usecase.dao

import org.usecase.model.resource.Principal
import org.usecase.model.rm.PrincipalRM
import org.usecase.model.resource.Tag
import org.usecase.model.rm.TagRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.TagPrincipal
import org.usecase.model.rm.TagPrincipalRM
import org.usecase.model.resource.Permission

/**
 * Data Access Object of TagPrincipal from table tag_principal
 * @author Simpli CLI generator
 */
class TagPrincipalDao(val con: AbstractConnector, val permission: PermissionGroup) {

    fun insert(tagPrincipal: TagPrincipal): Long {
        val tagPrincipalRM = TagPrincipalRM(permission)
        val query = Query()
                .insertInto(tagPrincipalRM.table)
                .insertValues(tagPrincipalRM.insertValues(tagPrincipal))

        return con.execute(query).key
    }

    fun removeAllFromTag(idTagFk: Long): Int {
        val tagPrincipalRM = TagPrincipalRM(permission)
        val query = Query()
                .deleteFrom(tagPrincipalRM.table)
                .whereEq(tagPrincipalRM.idTagFk.column, idTagFk)

        return con.execute(query).affectedRows
    }

    fun listTagOfPrincipal(idPrincipalFk: Long): MutableList<Tag> {
        val tagRm = TagRM(permission)
        val tagPrincipalRM = TagPrincipalRM(permission)

        val vs = VirtualSelect()
                .selectFields(tagRm.selectFields)
                .from(tagRm)
                .innerJoin(tagPrincipalRM, tagPrincipalRM.idTagFk, tagRm.idTagPk)
                .whereEq(tagPrincipalRM.idPrincipalFk, idPrincipalFk)

        return con.getList(vs.toQuery()) {
            tagRm.build(it)
        }
    }

    fun removeAllFromPrincipal(idPrincipalFk: Long): Int {
        val tagPrincipalRM = TagPrincipalRM(permission)
        val query = Query()
                .deleteFrom(tagPrincipalRM.table)
                .whereEq(tagPrincipalRM.idPrincipalFk.column, idPrincipalFk)

        return con.execute(query).affectedRows
    }

    fun listPrincipalOfTag(idTagFk: Long): MutableList<Principal> {
        val principalRm = PrincipalRM(permission)
        val tagPrincipalRM = TagPrincipalRM(permission)
        val vs = VirtualSelect()
                .selectFields(principalRm.selectFields)
                .from(principalRm)
                .innerJoin(tagPrincipalRM, tagPrincipalRM.idPrincipalFk, principalRm.idPrincipalPk)
                .whereEq(tagPrincipalRM.idTagFk, idTagFk)

        return con.getList(vs.toQuery()) {
            principalRm.build(it)
        }
    }

}

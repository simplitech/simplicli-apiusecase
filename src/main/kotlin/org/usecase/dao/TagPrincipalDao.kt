package org.usecase.dao

import org.usecase.model.resource.Principal
import org.usecase.model.resource.Tag
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query

/**
 * Data Access Object of TagPrincipal from table tag_principal
 * @author Simpli CLI generator
 */
class TagPrincipalDao(val con: AbstractConnector) {

    fun insert(idPrincipalFk: Long, idTagFk: Long): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("tag_principal")
                .insertValues(
                        "idPrincipalFk" to idPrincipalFk,
                        "idTagFk" to idTagFk
                )

        return con.execute(query).key
    }

    fun removeAllFromTag(idTagFk: Long): Int {
        // TODO: review generated method
        val query = Query()
                .deleteFrom("tag_principal")
                .whereEq("idTagFk", idTagFk)

        return con.execute(query).affectedRows
    }

    fun listTagOfPrincipal(idPrincipalFk: Long): MutableList<Tag> {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("tag")
                .innerJoin("tag_principal", "tag.idtagpk", "tag_principal.idTagFk")
                .whereEq("tag_principal.idPrincipalFk", idPrincipalFk)

        return con.getList(query) {
            Tag(it)
        }
    }

    fun removeAllFromPrincipal(idPrincipalFk: Long): Int {
        // TODO: review generated method
        val query = Query()
                .deleteFrom("tag_principal")
                .whereEq("idPrincipalFk", idPrincipalFk)

        return con.execute(query).affectedRows
    }

    fun listPrincipalOfTag(idTagFk: Long): MutableList<Principal> {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("principal")
                .innerJoin("tag_principal", "principal.idprincipalpk", "tag_principal.idPrincipalFk")
                .whereEq("tag_principal.idTagFk", idTagFk)

        return con.getList(query) {
            Principal(it)
        }
    }

}

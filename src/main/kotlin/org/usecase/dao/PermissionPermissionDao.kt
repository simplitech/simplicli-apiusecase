package org.usecase.dao

import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.*
import org.usecase.model.rm.*

/**
 * Data Access Object of PermissionPermission from table permission_permission
 * @author Simpli CLI generator
 */
class PermissionPermissionDao(val con: AbstractConnector, val permission: PermissionGroup) {

    fun insert(permissionPermission: PermissionPermission): Long {
        val permissionPermissionRM = PermissionPermissionRM(permission)
        val query = Query()
                .insertInto(permissionPermissionRM.table)
                .insertValues(permissionPermissionRM.insertValues(permissionPermission))

        return con.execute(query).key
    }

    fun removeAllFromPermissionParent(idPermissionParentFk: Long): Int {
        val permissionPermissionRM = PermissionPermissionRM(permission)
        val query = Query()
                .deleteFrom(permissionPermissionRM.table)
                .whereEq(permissionPermissionRM.idPermissionParentFk.column, idPermissionParentFk)

        return con.execute(query).affectedRows
    }

    fun listPermissionParentOfPermissionChild(idPermissionChildFk: Long): MutableList<Permission> {
        val permissionRm = PermissionRM(permission)
        val permissionPermissionRM = PermissionPermissionRM(permission)

        val vs = VirtualSelect()
                .selectFields(permissionRm.selectFields)
                .from(permissionRm)
                .innerJoin(permissionPermissionRM, permissionPermissionRM.idPermissionParentFk, permissionRm.idPermissionPk)
                .whereEq(permissionPermissionRM.idPermissionChildFk, idPermissionChildFk)

        return con.getList(vs.toQuery()) {
            permissionRm.build(it)
        }
    }

    fun removeAllFromPermission(idPermissionChildFk: Long): Int {
        val permissionPermissionRM = PermissionPermissionRM(permission)
        val query = Query()
                .deleteFrom(permissionPermissionRM.table)
                .whereEq(permissionPermissionRM.idPermissionChildFk.column, idPermissionChildFk)

        return con.execute(query).affectedRows
    }

    fun listPermissionChildOfPermissionParent(idPermissionParentFk: Long): MutableList<Permission> {
        val permissionRm = PermissionRM(permission)
        val permissionPermissionRM = PermissionPermissionRM(permission)
        val vs = VirtualSelect()
                .selectFields(permissionRm.selectFields)
                .from(permissionRm)
                .innerJoin(permissionPermissionRM, permissionPermissionRM.idPermissionChildFk, permissionRm.idPermissionPk)
                .whereEq(permissionPermissionRM.idPermissionParentFk, idPermissionParentFk)

        return con.getList(vs.toQuery()) {
            permissionRm.build(it)
        }
    }

}

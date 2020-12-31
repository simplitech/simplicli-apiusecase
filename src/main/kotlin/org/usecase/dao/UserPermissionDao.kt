package org.usecase.dao

import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.*
import org.usecase.model.rm.*

/**
 * Data Access Object of UserPermission from table user_permission
 * @author Simpli CLI generator
 */
class UserPermissionDao(val con: AbstractConnector, val permission: PermissionGroup) {

    fun insert(userPermission: UserPermission): Long {
        val userPermissionRM = UserPermissionRM(permission)
        val query = Query()
                .insertInto(userPermissionRM.table)
                .insertValues(userPermissionRM.insertValues(userPermission))

        return con.execute(query).key
    }

    fun removeAllFromUser(idUserFk: Long): Int {
        val userPermissionRM = UserPermissionRM(permission)
        val query = Query()
                .deleteFrom(userPermissionRM.table)
                .whereEq(userPermissionRM.idUserFk.column, idUserFk)

        return con.execute(query).affectedRows
    }

    fun listUserOfPermission(idPermissionFk: Long): MutableList<User> {
        val userRm = UserRM(permission)
        val userPermissionRM = UserPermissionRM(permission)

        val vs = VirtualSelect()
                .selectFields(userRm.selectFields)
                .from(userRm)
                .innerJoin(userPermissionRM, userPermissionRM.idUserFk, userRm.idUserPk)
                .whereEq(userPermissionRM.idPermissionFk, idPermissionFk)

        return con.getList(vs.toQuery()) {
            userRm.build(it)
        }
    }

    fun removeAllFromPermission(idPermissionFk: Long): Int {
        val userPermissionRM = UserPermissionRM(permission)
        val query = Query()
                .deleteFrom(userPermissionRM.table)
                .whereEq(userPermissionRM.idPermissionFk.column, idPermissionFk)

        return con.execute(query).affectedRows
    }

    fun listPermissionOfUser(idUserFk: Long): MutableList<Permission> {
        val permissionRm = PermissionRM(permission)
        val userPermissionRM = UserPermissionRM(permission)
        val vs = VirtualSelect()
                .selectFields(permissionRm.selectFields)
                .from(permissionRm)
                .innerJoin(userPermissionRM, userPermissionRM.idPermissionFk, permissionRm.idPermissionPk)
                .whereEq(userPermissionRM.idUserFk, idUserFk)

        return con.getList(vs.toQuery()) {
            permissionRm.build(it)
        }
    }

}

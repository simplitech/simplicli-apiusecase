package org.usecase.dao

import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.*
import org.usecase.model.rm.*

/**
 * Data Access Object of RolePermission from table role_permission
 * @author Simpli CLI generator
 */
class RolePermissionDao(val con: AbstractConnector, val permission: PermissionGroup) {

    fun insert(rolePermission: RolePermission): Long {
        val rolePermissionRM = RolePermissionRM(permission)
        val query = Query()
                .insertInto(rolePermissionRM.table)
                .insertValues(rolePermissionRM.insertValues(rolePermission))

        return con.execute(query).key
    }

    fun removeAllFromRole(idRoleFk: Long): Int {
        val rolePermissionRM = RolePermissionRM(permission)
        val query = Query()
                .deleteFrom(rolePermissionRM.table)
                .whereEq(rolePermissionRM.idRoleFk.column, idRoleFk)

        return con.execute(query).affectedRows
    }

    fun listRoleOfPermission(idPermissionFk: Long): MutableList<Role> {
        val roleRm = RoleRM(permission)
        val rolePermissionRM = RolePermissionRM(permission)

        val vs = VirtualSelect()
                .selectFields(roleRm.selectFields)
                .from(roleRm)
                .innerJoin(rolePermissionRM, rolePermissionRM.idRoleFk, roleRm.idRolePk)
                .whereEq(rolePermissionRM.idPermissionFk, idPermissionFk)

        return con.getList(vs.toQuery()) {
            roleRm.build(it)
        }
    }

    fun removeAllFromPermission(idPermissionFk: Long): Int {
        val rolePermissionRM = RolePermissionRM(permission)
        val query = Query()
                .deleteFrom(rolePermissionRM.table)
                .whereEq(rolePermissionRM.idPermissionFk.column, idPermissionFk)

        return con.execute(query).affectedRows
    }

    fun listPermissionOfRole(idRoleFk: Long): MutableList<Permission> {
        val permissionRm = PermissionRM(permission)
        val rolePermissionRM = RolePermissionRM(permission)
        val vs = VirtualSelect()
                .selectFields(permissionRm.selectFields)
                .from(permissionRm)
                .innerJoin(rolePermissionRM, rolePermissionRM.idPermissionFk, permissionRm.idPermissionPk)
                .whereEq(rolePermissionRM.idRoleFk, idRoleFk)

        return con.getList(vs.toQuery()) {
            permissionRm.build(it)
        }
    }

}

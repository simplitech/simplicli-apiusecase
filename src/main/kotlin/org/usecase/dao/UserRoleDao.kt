package org.usecase.dao

import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.*
import org.usecase.model.rm.*

/**
 * Data Access Object of UserRole from table user_role
 * @author Simpli CLI generator
 */
class UserRoleDao(val con: AbstractConnector, val permission: PermissionGroup) {

    fun insert(userRole: UserRole): Long {
        val userRoleRM = UserRoleRM(permission)
        val query = Query()
                .insertInto(userRoleRM.table)
                .insertValues(userRoleRM.insertValues(userRole))

        return con.execute(query).key
    }

    fun removeAllFromUser(idUserFk: Long): Int {
        val userRoleRM = UserRoleRM(permission)
        val query = Query()
                .deleteFrom(userRoleRM.table)
                .whereEq(userRoleRM.idUserFk.column, idUserFk)

        return con.execute(query).affectedRows
    }

    fun listUserOfRole(idRoleFk: Long): MutableList<User> {
        val userRm = UserRM(permission)
        val userRoleRM = UserRoleRM(permission)

        val vs = VirtualSelect()
                .selectFields(userRm.selectFields)
                .from(userRm)
                .innerJoin(userRoleRM, userRoleRM.idUserFk, userRm.idUserPk)
                .whereEq(userRoleRM.idRoleFk, idRoleFk)

        return con.getList(vs.toQuery()) {
            userRm.build(it)
        }
    }

    fun removeAllFromRole(idRoleFk: Long): Int {
        val userRoleRM = UserRoleRM(permission)
        val query = Query()
                .deleteFrom(userRoleRM.table)
                .whereEq(userRoleRM.idRoleFk.column, idRoleFk)

        return con.execute(query).affectedRows
    }

    fun listRoleOfUser(idUserFk: Long): MutableList<Role> {
        val roleRm = RoleRM(permission)
        val userRoleRM = UserRoleRM(permission)
        val vs = VirtualSelect()
                .selectFields(roleRm.selectFields)
                .from(roleRm)
                .innerJoin(userRoleRM, userRoleRM.idRoleFk, roleRm.idRolePk)
                .whereEq(userRoleRM.idUserFk, idUserFk)

        return con.getList(vs.toQuery()) {
            roleRm.build(it)
        }
    }

}

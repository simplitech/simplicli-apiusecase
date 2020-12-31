package org.usecase.policy

import org.usecase.context.Policy
import org.usecase.context.RequestContext
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role
import org.usecase.model.resource.User

class UserPolicy(context: RequestContext) : Policy(context) {
    fun read() {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addRole(Role.VIEWER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.USER_FULL_CONTROL)
            addPermission(Permission.USER_READ_ALL)
        }
    }

    fun persist(model: User) {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addRoleIf(Role.VIEWER) { context.isLoggedUser(model.id) }
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.USER_FULL_CONTROL)
            addPermissionIf(Permission.USER_INSERT_ALL) { model.id <= 0 }
            addPermissionIf(Permission.USER_UPDATE_ALL) { model.id > 0 }
        }
    }
}
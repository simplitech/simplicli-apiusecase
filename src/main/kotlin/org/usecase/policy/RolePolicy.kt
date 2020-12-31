package org.usecase.policy

import org.usecase.context.Policy
import org.usecase.context.RequestContext
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role

class RolePolicy(context: RequestContext) : Policy(context) {
    fun read() {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addRole(Role.VIEWER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.ROLE_FULL_CONTROL)
            addPermission(Permission.ROLE_READ_ALL)
        }
    }

    fun persist(model: Role) {
        requireAll {
            requireAny {
                addRole(Role.ADMIN)
                addPermission(Permission.FULL_CONTROL)
                addPermission(Permission.ROLE_FULL_CONTROL)
                addPermissionIf(Permission.ROLE_INSERT_ALL) { model.id <= 0 }
                addPermissionIf(Permission.ROLE_UPDATE_ALL) { model.id > 0 }
            }
            refuseAny {
                addRole(Role.MANAGER)
            }
        }
    }
}
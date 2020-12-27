package org.usecase.policy

import org.usecase.context.Policy
import org.usecase.context.RequestContext
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role

class PermissionPolicy(context: RequestContext) : Policy(context) {
    fun read() {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addRole(Role.VIEWER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.PERMISSION_FULL_CONTROL)
            addPermission(Permission.PERMISSION_READ_ALL)
        }
    }

    fun persist(model: Permission) {
        requireAll {
            requireAny {
                addRole(Role.ADMIN)
                addPermission(Permission.FULL_CONTROL)
                addPermission(Permission.PERMISSION_FULL_CONTROL)
                addPermissionIf(Permission.PERMISSION_INSERT_ALL) { model.id <= 0 }
                addPermissionIf(Permission.PERMISSION_UPDATE_ALL) { model.id > 0 }
            }
            refuseAny {
                addRole(Role.MANAGER)
            }
        }
    }
}
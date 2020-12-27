package org.usecase.policy

import org.usecase.context.Policy
import org.usecase.context.RequestContext
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Principal
import org.usecase.model.resource.Role

class PrincipalPolicy(context: RequestContext) : Policy(context) {
    fun read() {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addRole(Role.VIEWER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.PRINCIPAL_FULL_CONTROL)
            addPermission(Permission.PRINCIPAL_READ_ALL)
        }
    }

    fun persist(model: Principal) {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.PRINCIPAL_FULL_CONTROL)
            addPermissionIf(Permission.PRINCIPAL_INSERT_ALL) { model.id <= 0 }
            addPermissionIf(Permission.PRINCIPAL_UPDATE_ALL) { model.id > 0 }
        }
    }

    fun delete() {
        requireAny {
            addRole(Role.ADMIN)
            addPermission(Permission.PRINCIPAL_DELETE)
        }
    }
}
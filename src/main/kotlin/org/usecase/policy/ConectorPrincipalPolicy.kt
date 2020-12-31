package org.usecase.policy

import org.usecase.context.Policy
import org.usecase.context.RequestContext
import org.usecase.model.resource.ConectorPrincipal
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role

class ConectorPrincipalPolicy(context: RequestContext) : Policy(context) {
    fun read() {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addRole(Role.VIEWER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.CONECTOR_PRINCIPAL_FULL_CONTROL)
            addPermission(Permission.CONECTOR_PRINCIPAL_READ_ALL)
        }
    }

    fun persist(model: ConectorPrincipal) {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.CONECTOR_PRINCIPAL_FULL_CONTROL)
            addPermission(Permission.CONECTOR_PRINCIPAL_INSERT_ALL)
        }
    }
}
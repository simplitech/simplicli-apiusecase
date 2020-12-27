package org.usecase.policy

import org.usecase.context.Policy
import org.usecase.context.RequestContext
import org.usecase.model.resource.ExtensaoDoPrincipal
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role

class ExtensaoDoPrincipalPolicy(context: RequestContext) : Policy(context) {
    fun read() {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addRole(Role.VIEWER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.EXTENSAO_DO_PRINCIPAL_FULL_CONTROL)
            addPermission(Permission.EXTENSAO_DO_PRINCIPAL_READ_ALL)
        }
    }

    fun persist(model: ExtensaoDoPrincipal) {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.EXTENSAO_DO_PRINCIPAL_FULL_CONTROL)
            addPermissionIf(Permission.EXTENSAO_DO_PRINCIPAL_INSERT_ALL) { model.id <= 0 }
            addPermissionIf(Permission.EXTENSAO_DO_PRINCIPAL_UPDATE_ALL) { model.id > 0 }
        }
    }
}
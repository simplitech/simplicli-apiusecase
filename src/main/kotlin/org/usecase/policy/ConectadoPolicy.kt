package org.usecase.policy

import org.usecase.context.Policy
import org.usecase.context.RequestContext
import org.usecase.model.resource.Conectado
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role

class ConectadoPolicy(context: RequestContext) : Policy(context) {
    fun read() {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addRole(Role.VIEWER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.CONECTADO_FULL_CONTROL)
            addPermission(Permission.CONECTADO_READ_ALL)
        }
    }

    fun persist(model: Conectado) {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.CONECTADO_FULL_CONTROL)
            addPermissionIf(Permission.CONECTADO_INSERT_ALL) { model.id <= 0 }
            addPermissionIf(Permission.CONECTADO_UPDATE_ALL) { model.id > 0 }
        }
    }
}
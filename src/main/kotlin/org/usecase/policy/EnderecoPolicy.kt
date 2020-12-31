package org.usecase.policy

import org.usecase.context.Policy
import org.usecase.context.RequestContext
import org.usecase.model.resource.Endereco
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role

class EnderecoPolicy(context: RequestContext) : Policy(context) {
    fun read() {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addRole(Role.VIEWER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.ENDERECO_FULL_CONTROL)
            addPermission(Permission.ENDERECO_READ_ALL)
        }
    }

    fun persist(model: Endereco) {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.ENDERECO_FULL_CONTROL)
            addPermissionIf(Permission.ENDERECO_INSERT_ALL) { model.id <= 0 }
            addPermissionIf(Permission.ENDERECO_UPDATE_ALL) { model.id > 0 }
        }
    }
}
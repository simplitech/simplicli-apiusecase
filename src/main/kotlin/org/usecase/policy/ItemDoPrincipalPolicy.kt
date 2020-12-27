package org.usecase.policy

import org.usecase.context.Policy
import org.usecase.context.RequestContext
import org.usecase.model.resource.ItemDoPrincipal
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role

class ItemDoPrincipalPolicy(context: RequestContext) : Policy(context) {
    fun read() {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addRole(Role.VIEWER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.ITEM_DO_PRINCIPAL_FULL_CONTROL)
            addPermission(Permission.ITEM_DO_PRINCIPAL_READ_ALL)
        }
    }

    fun persist(model: ItemDoPrincipal) {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.ITEM_DO_PRINCIPAL_FULL_CONTROL)
            addPermissionIf(Permission.ITEM_DO_PRINCIPAL_INSERT_ALL) { model.id <= 0 }
            addPermissionIf(Permission.ITEM_DO_PRINCIPAL_UPDATE_ALL) { model.id > 0 }
        }
    }
}
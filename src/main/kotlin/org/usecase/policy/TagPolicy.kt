package org.usecase.policy

import org.usecase.context.Policy
import org.usecase.context.RequestContext
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role
import org.usecase.model.resource.Tag

class TagPolicy(context: RequestContext) : Policy(context) {
    fun read() {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addRole(Role.VIEWER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.TAG_FULL_CONTROL)
            addPermission(Permission.TAG_READ_ALL)
        }
    }

    fun persist(model: Tag) {
        requireAny {
            addRole(Role.ADMIN)
            addRole(Role.MANAGER)
            addPermission(Permission.FULL_CONTROL)
            addPermission(Permission.TAG_FULL_CONTROL)
            addPermissionIf(Permission.TAG_INSERT_ALL) { model.id <= 0 }
            addPermissionIf(Permission.TAG_UPDATE_ALL) { model.id > 0 }
        }
    }
}
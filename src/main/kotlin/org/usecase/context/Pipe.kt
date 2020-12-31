package org.usecase.context

import org.usecase.auth.AuthProcess
import org.usecase.model.param.DefaultParam
import br.com.simpli.sql.AbstractConPipe
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Permission.Companion.PERMISSION_READ_ALL
import org.usecase.model.resource.Permission.Companion.ROLE_READ_ALL
import org.usecase.model.resource.Permission.Companion.USER_READ_ALL

/**
 * Authentication Gateway
 * Responsible to control the authentication data and validates it
 * @author Simpli CLI generator
 */
open class Pipe(
        private val routePermission: PermissionGroup = PermissionGroup(),
        private val public: Boolean = false
) {
    fun <T> handle(
            conPipe: AbstractConPipe,
            param: DefaultParam,
            callback: (context: RequestContext) -> T
    ): T {
        return conPipe.handle {
            val context = RequestContext(it, param)

            if (!public || param.authorization != null) {
                context.routePermission = Permission.groupOf(USER_READ_ALL, ROLE_READ_ALL, PERMISSION_READ_ALL)
                context.auth = AuthProcess(context).authenticate(param)
            }

            context.routePermission = routePermission

            callback(context)
        }
    }

    class Public(routePermission: PermissionGroup = PermissionGroup()) : Pipe(routePermission, true)
}

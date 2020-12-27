package org.usecase

import org.usecase.model.param.DefaultParam
import br.com.simpli.sql.DaoTest
import br.com.simpli.sql.TransacConnector
import org.usecase.auth.AuthProcess
import org.usecase.context.PermissionGroup
import org.usecase.context.RequestContext
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Permission.Companion.groupOf

/**
 * Extended class of database connector tests
 * @author Simpli CLI generator
 */
open class AppTest : DaoTest() {
    protected object ID {
        val admin = 1L
        val manager = 2L
        val viewer = 3L
        val guest = 4L
    }

    protected val pipe = TransacConnector(getConnection())
    protected val param = DefaultParam()
    protected val context = RequestContext(pipe, param)

    protected val token get() = context.token ?: ""

    protected fun switchToAnonymous(tokenPermission: PermissionGroup = PermissionGroup()) {
        val token = AuthProcess.createAnonymousToken(1, tokenPermission)
        param.authorization = "Bearer $token"
        context.auth = AuthProcess(context).authenticate(param)
        context.routePermission = PermissionGroup()
    }

    protected fun switchUser(idUser: Long, routePermission: PermissionGroup = PermissionGroup()) {
        Permission.apply {
            context.routePermission = groupOf(USER_READ_ALL, ROLE_READ_ALL, PERMISSION_READ_ALL)
        }

        AuthProcess(context).apply {
            val token = createDirectAuthToken(idUser, 1)
            param.authorization = "Bearer $token"
            context.auth = authenticate(param)
            context.routePermission = routePermission
        }
    }

    init {
        switchToAnonymous(groupOf(Permission.FULL_CONTROL))
    }
}

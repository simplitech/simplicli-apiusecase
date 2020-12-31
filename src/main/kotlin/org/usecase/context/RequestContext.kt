package org.usecase.context

import org.usecase.app.Facade.Env
import org.usecase.model.enum.Lang
import org.usecase.model.filter.ListFilter
import org.usecase.model.param.DefaultParam
import org.usecase.locale.LangDefinition
import br.com.simpli.sql.AbstractConnector
import org.usecase.model.resource.Role
import org.usecase.model.response.AuthResponse
import org.usecase.socket.NotificationSocket
import java.util.*

/**
 * Request Context
 * Responsible to validate the request
 * @author Simpli CLI generator
 */
open class RequestContext(val con: AbstractConnector, param: DefaultParam) {
    val lang: LangDefinition = Env.AVAILABLE_LANGUAGES[Lang.from(param.lang)] ?: LangDefinition("en_US")
    val clientVersion: String = param.clientVersion
    var auth: AuthResponse? = null
    var routePermission = PermissionGroup()

    val tokenPermission get() = PermissionGroup(options?.permission?.scopes ?: mutableListOf())
    val userPermission get() = PermissionGroup(user?.scopes ?: mutableListOf())

    val permission get() = routePermission
            .union(tokenPermission)
            .union(userPermission)

    val token get() = auth?.token
    val user get() = auth?.user
    val roles get() = auth?.user?.roles?.mapNotNull { it.slug } ?: emptyList()
    val level get() = auth?.user?.roles?.mapNotNull { it.level }?.max() ?: 0

    val options get() = auth?.requestOptions

    val isAdmin get() = roles.contains(Role.ADMIN)
    val isManager get() = roles.contains(Role.MANAGER)
    val isViewer get() = roles.contains(Role.VIEWER)
    val isGuest get() = roles.contains(Role.GUEST)

    fun isLoggedUser(idUser: Long): Boolean = auth?.user?.id == idUser

    fun sendNotification(content: String) {
        NotificationSocket.socket.send(content, user?.id)
    }

    init {
        when (param) {
            is ListFilter -> {
                param.query = param.query?.replace("[.,:\\-/]".toRegex(), "")
            }
        }
    }

    companion object {
        val langBundle: ResourceBundle = ResourceBundle.getBundle("pt_BR")
    }
}

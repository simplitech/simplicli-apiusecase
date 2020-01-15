package org.usecase.user.context

import org.usecase.user.response.AuthResponse
import org.usecase.user.auth.AuthProcess
import org.usecase.param.DefaultParam
import br.com.simpli.sql.AbstractConPipe

/**
 * Authentication Gateway
 * Responsible to control the authentication data and validates it
 * @author Simpli CLI generator
 */
object AuthPipe {
    fun <T> handle(
            conPipe: AbstractConPipe,
            param: DefaultParam.Auth,
            callback: (context: RequestContext, auth: AuthResponse) -> T
    ): T {
        return conPipe.handle {
            val context = RequestContext(it, param)
            val auth = AuthProcess(context).authenticate(param)
            callback(context, auth)
        }
    }
}

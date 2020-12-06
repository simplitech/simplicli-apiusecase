package org.usecase.user.context

import org.usecase.user.auth.AuthProcess
import org.usecase.model.param.DefaultParam
import br.com.simpli.sql.AbstractConPipe

/**
 * Authentication Gateway
 * Responsible to control the authentication data and validates it
 * @author Simpli CLI generator
 */
open class AuthPipe(private val public: Boolean = false) {
    fun <T> handle(
            conPipe: AbstractConPipe,
            param: DefaultParam,
            callback: (context: RequestContext) -> T
    ): T {
        return conPipe.handle {
            val context = RequestContext(it, param)
            if (!public || param.authorization != null) {
                context.auth = AuthProcess(context).authenticate(param)
            }
            callback(context)
        }
    }
}

class PublicPipe : AuthPipe(true)

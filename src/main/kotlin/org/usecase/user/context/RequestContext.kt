package org.usecase.user.context

import org.usecase.app.Facade.Env
import org.usecase.model.enum.Lang
import org.usecase.model.filter.ListFilter
import org.usecase.model.param.DefaultParam
import org.usecase.locale.LangDefinition
import br.com.simpli.sql.AbstractConnector
import org.usecase.user.response.AuthResponse
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

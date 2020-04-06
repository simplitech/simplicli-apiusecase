package org.usecase.user.context

import org.usecase.app.Facade.Env
import org.usecase.enums.Lang
import org.usecase.locale.EnUs
import org.usecase.model.filter.ListFilter
import org.usecase.param.DefaultParam
import br.com.simpli.model.LanguageHolder
import br.com.simpli.sql.AbstractConnector

/**
 * Request Context
 * Responsible to validate the request
 * @author Simpli CLI generator
 */
 open class RequestContext(val con: AbstractConnector, param: DefaultParam) {
     val lang: LanguageHolder = Env.AVAILABLE_LANGUAGES[Lang.from(param.lang)] ?: EnUs()
     val clientVersion: String = param.clientVersion

     init {
         when (param) {
             is ListFilter -> {
                 param.query = param.query?.replace("[.,:\\-/]".toRegex(), "")
             }
         }
     }
 }

package org.usecase.model.param

import org.usecase.model.filter.ExtensaoDoPrincipalListFilter
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.QueryParam

/**
 * Authenticated ExtensaoDoPrincipal Param
 * @author Simpli CLI generator
 */
open class AuthExtensaoDoPrincipalListParam: DefaultParam.AuthPaged(), ExtensaoDoPrincipalListFilter {
}

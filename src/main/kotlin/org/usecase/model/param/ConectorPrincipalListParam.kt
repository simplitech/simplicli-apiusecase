package org.usecase.model.param

import org.usecase.model.filter.ConectorPrincipalListFilter
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.QueryParam

/**
 * Authenticated ConectorPrincipal Param
 * @author Simpli CLI generator
 */
open class ConectorPrincipalListParam: DefaultParam.Paged(), ConectorPrincipalListFilter {
}

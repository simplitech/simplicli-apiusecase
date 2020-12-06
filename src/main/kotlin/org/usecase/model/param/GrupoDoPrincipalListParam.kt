package org.usecase.model.param

import org.usecase.model.filter.GrupoDoPrincipalListFilter
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.QueryParam

/**
 * Authenticated GrupoDoPrincipal Param
 * @author Simpli CLI generator
 */
open class GrupoDoPrincipalListParam: DefaultParam.Paged(), GrupoDoPrincipalListFilter {
}

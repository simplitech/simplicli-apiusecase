package org.usecase.model.param

import org.usecase.model.filter.ConectadoListFilter
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.QueryParam

/**
 * Authenticated Conectado Param
 * @author Simpli CLI generator
 */
open class ConectadoListParam: DefaultParam.Paged(), ConectadoListFilter {
}

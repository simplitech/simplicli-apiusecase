package org.usecase.model.param

import org.usecase.model.filter.ItemDoPrincipalListFilter
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.QueryParam

/**
 * Authenticated ItemDoPrincipal Param
 * @author Simpli CLI generator
 */
open class ItemDoPrincipalListParam: DefaultParam.Paged(), ItemDoPrincipalListFilter {
    @QueryParam("idPrincipalFk")
    @Schema
    override var idPrincipalFk: List<Long>? = null
}

package org.usecase.model.param

import io.swagger.v3.oas.annotations.media.Schema
import org.usecase.model.filter.RoleListFilter
import java.util.Date
import javax.ws.rs.QueryParam

/**
 * Authenticated Role Param
 * @author Simpli CLI generator
 */
open class RoleListParam: DefaultParam.Paged(), RoleListFilter {
    @QueryParam("minLevel")
    @Schema
    override var minLevel: Int? = null

    @QueryParam("maxLevel")
    @Schema
    override var maxLevel: Int? = null
}

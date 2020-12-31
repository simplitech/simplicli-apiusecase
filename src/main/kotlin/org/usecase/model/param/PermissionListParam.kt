package org.usecase.model.param

import io.swagger.v3.oas.annotations.media.Schema
import org.usecase.model.filter.PermissionListFilter
import java.util.Date
import javax.ws.rs.QueryParam

/**
 * Authenticated Permission Param
 * @author Simpli CLI generator
 */
open class PermissionListParam: DefaultParam.Paged(), PermissionListFilter {
}

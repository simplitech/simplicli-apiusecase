package org.usecase.model.param

import org.usecase.model.filter.TagListFilter
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.QueryParam

/**
 * Authenticated Tag Param
 * @author Simpli CLI generator
 */
open class AuthTagListParam: DefaultParam.AuthPaged(), TagListFilter {
}

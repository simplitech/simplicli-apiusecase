package org.usecase.model.param

import org.usecase.enums.Lang
import io.swagger.v3.oas.annotations.media.Schema
import javax.ws.rs.HeaderParam
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam

/**
 * DefaultParam
 * Used as Bean parameters in the routers
 * @author Simpli CLI generator
 */
open class DefaultParam {
    @HeaderParam("Accept-Language")
    @Schema(allowableValues = ["en-US", "pt-BR"], defaultValue = "en-US")
    var lang: String = Lang.EN_US.toString()

    @HeaderParam("X-Client-Version")
    @Schema(defaultValue = "w1.0.0")
    var clientVersion: String = "w1.0.0"

    @HeaderParam("Authorization")
    @Schema(required = true, example = "Bearer my-token-here")
    var authorization: String? = null

    open class RequiredPathId : DefaultParam() {
        @PathParam("id")
        @Schema(required = true)
        var id: Long? = null
    }

    open class Paged : DefaultParam() {
        @QueryParam("query")
        @Schema(name = "Query of search")
        var query: String? = null

        @QueryParam("page")
        @Schema(name = "Page index, null to not paginate")
        var page: Int? = null

        @QueryParam("limit")
        @Schema(name = "Page size, null to not paginate")
        var limit: Int? = null

        @QueryParam("orderBy")
        @Schema(name = "Identifier for sorting, usually a property name", example = "id")
        var orderBy: String? = null

        @QueryParam("ascending")
        @Schema(name = "True for ascending order")
        var ascending: Boolean? = null
    }
}

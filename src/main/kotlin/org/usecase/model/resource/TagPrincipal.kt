package org.usecase.model.resource

import org.usecase.model.param.DefaultParam
import io.swagger.v3.oas.annotations.media.Schema
import javax.ws.rs.PathParam

/**
 * Reference model of table conector_principal
 * @author Simpli CLI generator
 */
class TagPrincipal {
    @Schema(required = true) var idTagFk: Long = 0
    @Schema(required = true) var idPrincipalFk: Long = 0

    var tag: Tag? = null
    var principal: Principal? = null

    open class RequiredPathId : DefaultParam() {
        @PathParam("idPrincipalFk")
        @Schema(required = true)
        var idPrincipalFk: Long? = null

        @PathParam("idTagFk")
        @Schema(required = true)
        var idTagFk: Long? = null
    }
}

package org.usecase.model.resource

import org.usecase.model.param.DefaultParam
import org.usecase.locale.LangDefinition
import io.swagger.v3.oas.annotations.media.Schema
import javax.ws.rs.PathParam
import org.valiktor.functions.*
import org.valiktor.validate

/**
 * Reference model of table conector_principal
 * @author Simpli CLI generator
 */
class ConectorPrincipal {
    @Schema(required = true) var idPrincipalFk: Long = 0
    @Schema(required = true) var idConectadoFk: Long = 0

    var conectado: Conectado? = null
    var principal: Principal? = null

    @Schema(required = true, maxLength = 45)
    var titulo: String? = null

    open class RequiredPathId : DefaultParam() {
        @PathParam("idPrincipalFk")
        @Schema(required = true)
        var idPrincipalFk: Long? = null

        @PathParam("idConectadoFk")
        @Schema(required = true)
        var idConectadoFk: Long? = null
    }
}

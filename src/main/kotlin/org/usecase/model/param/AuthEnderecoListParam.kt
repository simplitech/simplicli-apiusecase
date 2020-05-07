package org.usecase.model.param

import org.usecase.model.filter.EnderecoListFilter
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.QueryParam

/**
 * Authenticated Endereco Param
 * @author Simpli CLI generator
 */
open class AuthEnderecoListParam: DefaultParam.AuthPaged(), EnderecoListFilter {
    @QueryParam("minNro")
    @Schema
    override var minNro: Long? = null

    @QueryParam("maxNro")
    @Schema
    override var maxNro: Long? = null

    @QueryParam("minLatitude")
    @Schema
    override var minLatitude: Double? = null

    @QueryParam("maxLatitude")
    @Schema
    override var maxLatitude: Double? = null

    @QueryParam("minLongitude")
    @Schema
    override var minLongitude: Double? = null

    @QueryParam("maxLongitude")
    @Schema
    override var maxLongitude: Double? = null
}

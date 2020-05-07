package org.usecase.model.param

import org.usecase.model.filter.PrincipalListFilter
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.QueryParam

/**
 * Authenticated Principal Param
 * @author Simpli CLI generator
 */
open class AuthPrincipalListParam: DefaultParam.AuthPaged(), PrincipalListFilter {
    @QueryParam("idGrupoDoPrincipalFk")
    @Schema
    override var idGrupoDoPrincipalFk: List<Long>? = null

    @QueryParam("idGrupoDoPrincipalFacultativoFk")
    @Schema
    override var idGrupoDoPrincipalFacultativoFk: List<Long>? = null

    @QueryParam("startDataObrigatoria")
    @Schema
    override var startDataObrigatoria: Date? = null

    @QueryParam("endDataObrigatoria")
    @Schema
    override var endDataObrigatoria: Date? = null

    @QueryParam("startDataFacultativa")
    @Schema
    override var startDataFacultativa: Date? = null

    @QueryParam("endDataFacultativa")
    @Schema
    override var endDataFacultativa: Date? = null

    @QueryParam("startDatahoraObrigatoria")
    @Schema
    override var startDatahoraObrigatoria: Date? = null

    @QueryParam("endDatahoraObrigatoria")
    @Schema
    override var endDatahoraObrigatoria: Date? = null

    @QueryParam("startDatahoraFacultativa")
    @Schema
    override var startDatahoraFacultativa: Date? = null

    @QueryParam("endDatahoraFacultativa")
    @Schema
    override var endDatahoraFacultativa: Date? = null

    @QueryParam("startDataCriacao")
    @Schema
    override var startDataCriacao: Date? = null

    @QueryParam("endDataCriacao")
    @Schema
    override var endDataCriacao: Date? = null

    @QueryParam("startDataAlteracao")
    @Schema
    override var startDataAlteracao: Date? = null

    @QueryParam("endDataAlteracao")
    @Schema
    override var endDataAlteracao: Date? = null

    @QueryParam("minDecimalObrigatorio")
    @Schema
    override var minDecimalObrigatorio: Double? = null

    @QueryParam("maxDecimalObrigatorio")
    @Schema
    override var maxDecimalObrigatorio: Double? = null

    @QueryParam("minDecimalFacultativo")
    @Schema
    override var minDecimalFacultativo: Double? = null

    @QueryParam("maxDecimalFacultativo")
    @Schema
    override var maxDecimalFacultativo: Double? = null

    @QueryParam("minInteiroObrigatorio")
    @Schema
    override var minInteiroObrigatorio: Long? = null

    @QueryParam("maxInteiroObrigatorio")
    @Schema
    override var maxInteiroObrigatorio: Long? = null

    @QueryParam("minInteiroFacultativo")
    @Schema
    override var minInteiroFacultativo: Long? = null

    @QueryParam("maxInteiroFacultativo")
    @Schema
    override var maxInteiroFacultativo: Long? = null

    @QueryParam("minPreco")
    @Schema
    override var minPreco: Double? = null

    @QueryParam("maxPreco")
    @Schema
    override var maxPreco: Double? = null

    @QueryParam("booleanoObrigatorio")
    @Schema
    override var booleanoObrigatorio: Boolean? = null

    @QueryParam("booleanoFacultativo")
    @Schema
    override var booleanoFacultativo: Boolean? = null
}

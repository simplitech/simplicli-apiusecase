package org.usecase.param

import io.swagger.v3.oas.annotations.media.Schema
import org.usecase.model.filter.PrincipalListFilter
import java.util.Date
import javax.ws.rs.QueryParam

open class AuthPrincipalListFilter: DefaultParam.AuthPaged(), PrincipalListFilter {

    @QueryParam("idGrupoDoPrincipalFk")
    @Schema
    override var idGrupoDoPrincipalFk: List<Long>? = null

    @QueryParam("idGrupoDoPrincipalFacultativoFk")
    @Schema
    override var idGrupoDoPrincipalFacultativoFk: List<Long>? = null


    @QueryParam("minInteiroObrigatorio")
    @Schema
    override var minInteiroObrigatorio: Long? = null

    @QueryParam("maxInteiroObrigatorio")
    @Schema
    override var maxInteiroObrigatorio: Long? = null


    @QueryParam("minDecimalObrigatorio")
    @Schema
    override var minDecimalObrigatorio: Double? = null

    @QueryParam("maxDecimalObrigatorio")
    @Schema
    override var maxDecimalObrigatorio: Double? = null


    @QueryParam("booleanoObrigatorio")
    @Schema
    override var booleanoObrigatorio: Boolean? = null


    @QueryParam("minDataObrigatoria")
    @Schema
    override var minDataObrigatoria: Date? = null

    @QueryParam("maxDataObrigatoria")
    @Schema
    override var maxDataObrigatoria: Date? = null


    @QueryParam("minDatahoraObrigatoria")
    @Schema
    override var minDatahoraObrigatoria: Date? = null

    @QueryParam("maxDatahoraObrigatoria")
    @Schema
    override var maxDatahoraObrigatoria: Date? = null


    @QueryParam("minDataCriacao")
    @Schema
    override var minDataCriacao: Date? = null

    @QueryParam("maxDataCriacao")
    @Schema
    override var maxDataCriacao: Date? = null


    @QueryParam("minInteiroFacultativo")
    @Schema
    override var minInteiroFacultativo: Long? = null

    @QueryParam("maxInteiroFacultativo")
    @Schema
    override var maxInteiroFacultativo: Long? = null


    @QueryParam("minDecimalFacultativo")
    @Schema
    override var minDecimalFacultativo: Double? = null

    @QueryParam("maxDecimalFacultativo")
    @Schema
    override var maxDecimalFacultativo: Double? = null


    @QueryParam("booleanoFacultativo")
    @Schema
    override var booleanoFacultativo: Boolean? = null


    @QueryParam("minDataFacultativa")
    @Schema
    override var minDataFacultativa: Date? = null

    @QueryParam("maxDataFacultativa")
    @Schema
    override var maxDataFacultativa: Date? = null


    @QueryParam("minDatahoraFacultativa")
    @Schema
    override var minDatahoraFacultativa: Date? = null

    @QueryParam("maxDatahoraFacultativa")
    @Schema
    override var maxDatahoraFacultativa: Date? = null


    @QueryParam("minDataAlteracao")
    @Schema
    override var minDataAlteracao: Date? = null

    @QueryParam("maxDataAlteracao")
    @Schema
    override var maxDataAlteracao: Date? = null


    @QueryParam("minPreco")
    @Schema
    override var minPreco: Double? = null

    @QueryParam("maxPreco")
    @Schema
    override var maxPreco: Double? = null
}

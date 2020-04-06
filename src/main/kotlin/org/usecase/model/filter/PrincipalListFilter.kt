package org.usecase.model.filter

import java.util.Date

interface PrincipalListFilter : ListFilter {
    var idGrupoDoPrincipalFk: List<Long>?
    var idGrupoDoPrincipalFacultativoFk: List<Long>?

    var minInteiroObrigatorio: Long?
    var maxInteiroObrigatorio: Long?

    var minDecimalObrigatorio: Double?
    var maxDecimalObrigatorio: Double?

    var booleanoObrigatorio: Boolean?

    var minDataObrigatoria: Date?
    var maxDataObrigatoria: Date?

    var minDatahoraObrigatoria: Date?
    var maxDatahoraObrigatoria: Date?

    var minDataCriacao: Date?
    var maxDataCriacao: Date?

    var minInteiroFacultativo: Long?
    var maxInteiroFacultativo: Long?

    var minDecimalFacultativo: Double?
    var maxDecimalFacultativo: Double?

    var booleanoFacultativo: Boolean?

    var minDataFacultativa: Date?
    var maxDataFacultativa: Date?

    var minDatahoraFacultativa: Date?
    var maxDatahoraFacultativa: Date?

    var minDataAlteracao: Date?
    var maxDataAlteracao: Date?

    var minPreco: Double?
    var maxPreco: Double?
}

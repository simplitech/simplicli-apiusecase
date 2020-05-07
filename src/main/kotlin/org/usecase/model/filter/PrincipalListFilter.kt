package org.usecase.model.filter

import java.util.Date

/**
 * Principal List Filter
 * @author Simpli CLI generator
 */
interface PrincipalListFilter : ListFilter {
    var idGrupoDoPrincipalFk: List<Long>?

    var idGrupoDoPrincipalFacultativoFk: List<Long>?

    var startDataObrigatoria: Date?
    var endDataObrigatoria: Date?

    var startDataFacultativa: Date?
    var endDataFacultativa: Date?

    var startDatahoraObrigatoria: Date?
    var endDatahoraObrigatoria: Date?

    var startDatahoraFacultativa: Date?
    var endDatahoraFacultativa: Date?

    var startDataCriacao: Date?
    var endDataCriacao: Date?

    var startDataAlteracao: Date?
    var endDataAlteracao: Date?

    var minDecimalObrigatorio: Double?
    var maxDecimalObrigatorio: Double?

    var minDecimalFacultativo: Double?
    var maxDecimalFacultativo: Double?

    var minInteiroObrigatorio: Long?
    var maxInteiroObrigatorio: Long?

    var minInteiroFacultativo: Long?
    var maxInteiroFacultativo: Long?

    var minPreco: Double?
    var maxPreco: Double?

    var booleanoObrigatorio: Boolean?

    var booleanoFacultativo: Boolean?
}

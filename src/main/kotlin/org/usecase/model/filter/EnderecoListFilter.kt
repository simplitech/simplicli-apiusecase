package org.usecase.model.filter

import java.util.Date

/**
 * Endereco List Filter
 * @author Simpli CLI generator
 */
interface EnderecoListFilter : ListFilter {
    var minNro: Long?
    var maxNro: Long?

    var minLatitude: Double?
    var maxLatitude: Double?

    var minLongitude: Double?
    var maxLongitude: Double?
}

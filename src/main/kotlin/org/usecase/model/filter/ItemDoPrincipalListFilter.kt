package org.usecase.model.filter

import java.util.Date

/**
 * ItemDoPrincipal List Filter
 * @author Simpli CLI generator
 */
interface ItemDoPrincipalListFilter : ListFilter {
    var idPrincipalFk: List<Long>?
}

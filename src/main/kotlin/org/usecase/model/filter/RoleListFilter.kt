package org.usecase.model.filter

import java.util.Date

/**
 * Role List Filter
 * @author Simpli CLI generator
 */
interface RoleListFilter : ListFilter {
    var minLevel: Int?
    var maxLevel: Int?
}

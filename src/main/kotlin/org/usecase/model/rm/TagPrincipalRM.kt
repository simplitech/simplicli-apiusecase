package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.model.resource.Principal
import org.usecase.model.resource.TagPrincipal
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.TAG_PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.TAG_PRINCIPAL_READ_ALL
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table conector_principal
 * @author Simpli CLI generator
 */
class TagPrincipalRM(val permission: Permission, override var alias: String? = null) : RelationalMapper<TagPrincipal>() {
    override val table = "tag_principal"

    val idTagFk = col("idTagFk",
            { idTagFk },
            { idTagFk = it.value() })

    val idPrincipalFk = col("idPrincipalFk",
            { idPrincipalFk },
            { idPrincipalFk = it.value() })

    fun build(rs: ResultSet) = TagPrincipal().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<TagPrincipal>>
        get() = permission.buildArray {
            add(TAG_PRINCIPAL_READ_ALL, idTagFk)
            add(TAG_PRINCIPAL_READ_ALL, idPrincipalFk)
        }

    val fieldsToSearch: Array<VirtualColumn<TagPrincipal>>
        get() = permission.buildArray {
            add(TAG_PRINCIPAL_READ_ALL, idTagFk)
            add(TAG_PRINCIPAL_READ_ALL, idPrincipalFk)
        }

    val orderMap: Map<String, VirtualColumn<TagPrincipal>>
        get() = permission.buildMap {
            add(TAG_PRINCIPAL_READ_ALL, "idTagFk" to idTagFk)
            add(TAG_PRINCIPAL_READ_ALL, "idPrincipalFk" to idPrincipalFk)
        }

    fun insertValues(tagPrincipal: TagPrincipal) = colsToMap(tagPrincipal, *permission.buildArray {
        add(TAG_PRINCIPAL_INSERT_ALL, idTagFk)
        add(TAG_PRINCIPAL_INSERT_ALL, idPrincipalFk)
    })
}

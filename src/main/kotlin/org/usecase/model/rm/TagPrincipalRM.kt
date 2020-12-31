package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.TagPrincipal
import org.usecase.model.resource.Permission
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table conector_principal
 * @author Simpli CLI generator
 */
class TagPrincipalRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<TagPrincipal>() {
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
            Permission.apply {
                add(idTagFk, FULL_CONTROL, TAG_PRINCIPAL_FULL_CONTROL, TAG_PRINCIPAL_READ_ALL, TAG_PRINCIPAL_READ_ID_TAG_FK)
                add(idPrincipalFk, FULL_CONTROL, TAG_PRINCIPAL_FULL_CONTROL, TAG_PRINCIPAL_READ_ALL, TAG_PRINCIPAL_READ_ID_PRINCIPAL_FK)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<TagPrincipal>>
        get() = permission.buildArray {
            Permission.apply {
                add(idTagFk, FULL_CONTROL, TAG_PRINCIPAL_FULL_CONTROL, TAG_PRINCIPAL_READ_ALL, TAG_PRINCIPAL_READ_ID_TAG_FK)
                add(idPrincipalFk, FULL_CONTROL, TAG_PRINCIPAL_FULL_CONTROL, TAG_PRINCIPAL_READ_ALL, TAG_PRINCIPAL_READ_ID_PRINCIPAL_FK)
            }
        }

    val orderMap: Map<String, VirtualColumn<TagPrincipal>>
        get() = permission.buildMap {
            Permission.apply {
                add("idTagFk" to idTagFk, FULL_CONTROL, TAG_PRINCIPAL_FULL_CONTROL, TAG_PRINCIPAL_READ_ALL, TAG_PRINCIPAL_READ_ID_TAG_FK)
                add("idPrincipalFk" to idPrincipalFk, FULL_CONTROL, TAG_PRINCIPAL_FULL_CONTROL, TAG_PRINCIPAL_READ_ALL, TAG_PRINCIPAL_READ_ID_PRINCIPAL_FK)
            }
        }

    fun insertValues(tagPrincipal: TagPrincipal) = colsToMap(tagPrincipal, *permission.buildArray {
        Permission.apply {
            add(idTagFk, FULL_CONTROL, TAG_PRINCIPAL_FULL_CONTROL, TAG_PRINCIPAL_INSERT_ALL, TAG_PRINCIPAL_INSERT_ID_TAG_FK)
            add(idPrincipalFk, FULL_CONTROL, TAG_PRINCIPAL_FULL_CONTROL, TAG_PRINCIPAL_INSERT_ALL, TAG_PRINCIPAL_INSERT_ID_PRINCIPAL_FK)
        }
    })
}

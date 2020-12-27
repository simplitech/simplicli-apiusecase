package org.usecase.model.rm

import org.usecase.model.resource.Tag
import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table tag
 * @author Simpli CLI generator
 */
class TagRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<Tag>() {
    override val table = "tag"

    val idTagPk = col("idTagPk",
            { idTagPk },
            { idTagPk = it.value() })

    val titulo = col("titulo",
            { titulo },
            { titulo = it.value() })

    fun build(rs: ResultSet) = Tag().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<Tag>>
        get() = permission.buildArray {
            Permission.apply {
                add(idTagPk, FULL_CONTROL, TAG_FULL_CONTROL, TAG_READ_ALL, TAG_READ_ID_TAG_PK)
                add(titulo, FULL_CONTROL, TAG_FULL_CONTROL, TAG_READ_ALL, TAG_READ_TITULO)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<Tag>>
        get() = permission.buildArray {
            Permission.apply {
                add(idTagPk, FULL_CONTROL, TAG_FULL_CONTROL, TAG_READ_ALL, TAG_READ_ID_TAG_PK)
                add(titulo, FULL_CONTROL, TAG_FULL_CONTROL, TAG_READ_ALL, TAG_READ_TITULO)
            }
        }

    val orderMap: Map<String, VirtualColumn<Tag>>
        get() = permission.buildMap {
            Permission.apply {
                add("idTagPk" to idTagPk, FULL_CONTROL, TAG_FULL_CONTROL, TAG_READ_ALL, TAG_READ_ID_TAG_PK)
                add("titulo" to titulo, FULL_CONTROL, TAG_FULL_CONTROL, TAG_READ_ALL, TAG_READ_TITULO)
            }
        }

    fun updateSet(tag: Tag) = colsToMap(tag, *permission.buildArray {
        Permission.apply {
            add(titulo, FULL_CONTROL, TAG_FULL_CONTROL, TAG_UPDATE_ALL, TAG_UPDATE_TITULO)
        }
    })

    fun insertValues(tag: Tag) = colsToMap(tag, *permission.buildArray {
        Permission.apply {
            add(titulo, FULL_CONTROL, TAG_FULL_CONTROL, TAG_INSERT_ALL, TAG_INSERT_TITULO)
        }
    })
}

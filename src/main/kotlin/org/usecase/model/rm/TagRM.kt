package org.usecase.model.rm

import org.usecase.model.resource.Tag
import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.model.resource.TagPrincipal
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.TAG_INSERT_ALL
import org.usecase.user.context.Permission.Companion.TAG_READ_ALL
import org.usecase.user.context.Permission.Companion.TAG_UPDATE_ALL
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table tag
 * @author Simpli CLI generator
 */
class TagRM(val permission: Permission, override var alias: String? = null) : RelationalMapper<Tag>() {
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
            add(TAG_READ_ALL, idTagPk)
            add(TAG_READ_ALL, titulo)
        }

    val fieldsToSearch: Array<VirtualColumn<Tag>>
        get() = permission.buildArray {
            add(TAG_READ_ALL, idTagPk)
            add(TAG_READ_ALL, titulo)
        }

    val orderMap: Map<String, VirtualColumn<Tag>>
        get() = permission.buildMap {
            add(TAG_READ_ALL, "idTagPk" to idTagPk)
            add(TAG_READ_ALL, "titulo" to titulo)
        }

    fun updateSet(tag: Tag) = colsToMap(tag, *permission.buildArray {
        add(TAG_UPDATE_ALL, titulo)
    })

    fun insertValues(tag: Tag) = colsToMap(tag, *permission.buildArray {
        add(TAG_INSERT_ALL, titulo)
    })
}

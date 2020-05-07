package org.usecase.model.rm

import org.usecase.model.resource.Tag
import br.com.simpli.sql.Query
import br.com.simpli.sql.ResultBuilder
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table tag
 * @author Simpli CLI generator
 */
object TagRM {
    fun build(rs: ResultSet, alias: String = "tag", allowedColumns: Array<String> = selectFields(alias)) = Tag().apply {
        ResultBuilder(allowedColumns, rs, alias).run {
            idTagPk = getLong("idTagPk")
            titulo = getString("titulo")
        }
    }

    fun selectFields(alias: String = "tag") = arrayOf(
            "$alias.idTagPk",
            "$alias.titulo"
    )

    fun fieldsToSearch(alias: String = "tag") = arrayOf(
            "$alias.idTagPk",
            "$alias.titulo"
    )

    fun orderMap(alias: String = "tag") = mapOf(
            "idTagPk" to "$alias.idTagPk",
            "titulo" to "$alias.titulo"
    )

    fun updateSet(tag: Tag) = mapOf(
            "titulo" to tag.titulo
    )

    fun insertValues(tag: Tag) = mapOf(
            "titulo" to tag.titulo
    )
}

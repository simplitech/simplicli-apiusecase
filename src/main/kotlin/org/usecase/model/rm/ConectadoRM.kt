package org.usecase.model.rm

import org.usecase.model.resource.Conectado
import br.com.simpli.sql.Query
import br.com.simpli.sql.ResultBuilder
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table conectado
 * @author Simpli CLI generator
 */
object ConectadoRM {
    fun build(rs: ResultSet, alias: String = "conectado", allowedColumns: Array<String> = selectFields(alias)) = Conectado().apply {
        ResultBuilder(allowedColumns, rs, alias).run {
            idConectadoPk = getLong("idConectadoPk")
            titulo = getString("titulo")
        }
    }

    fun selectFields(alias: String = "conectado") = arrayOf(
            "$alias.idConectadoPk",
            "$alias.titulo"
    )

    fun fieldsToSearch(alias: String = "conectado") = arrayOf(
            "$alias.idConectadoPk",
            "$alias.titulo"
    )

    fun orderMap(alias: String = "conectado") = mapOf(
            "idConectadoPk" to "$alias.idConectadoPk",
            "titulo" to "$alias.titulo"
    )

    fun updateSet(conectado: Conectado) = mapOf(
            "titulo" to conectado.titulo
    )

    fun insertValues(conectado: Conectado) = mapOf(
            "titulo" to conectado.titulo
    )
}

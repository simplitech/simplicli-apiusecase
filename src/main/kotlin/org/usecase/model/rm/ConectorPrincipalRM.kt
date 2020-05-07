package org.usecase.model.rm

import org.usecase.model.resource.ConectorPrincipal
import br.com.simpli.sql.Query
import br.com.simpli.sql.ResultBuilder
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table conector_principal
 * @author Simpli CLI generator
 */
object ConectorPrincipalRM {
    fun build(rs: ResultSet, alias: String = "conector_principal", allowedColumns: Array<String> = selectFields(alias)) = ConectorPrincipal().apply {
        ResultBuilder(allowedColumns, rs, alias).run {
            idPrincipalFk = getLong("idPrincipalFk")
            idConectadoFk = getLong("idConectadoFk")
            titulo = getString("titulo")
        }
    }

    fun selectFields(alias: String = "conector_principal") = arrayOf(
            "$alias.idPrincipalFk",
            "$alias.idConectadoFk",
            "$alias.titulo"
    )

    fun fieldsToSearch(alias: String = "conector_principal") = arrayOf(
            "$alias.idPrincipalFk",
            "$alias.idConectadoFk",
            "$alias.titulo"
    )

    fun orderMap(alias: String = "conector_principal") = mapOf(
            "conectado" to "$alias.idConectadoFk",
            "principal" to "$alias.idPrincipalFk",
            "titulo" to "$alias.titulo"
    )

    fun updateSet(conectorPrincipal: ConectorPrincipal) = mapOf(
            "titulo" to conectorPrincipal.titulo
    )

    fun insertValues(conectorPrincipal: ConectorPrincipal) = mapOf(
            "idPrincipalFk" to conectorPrincipal.idPrincipalFk,
            "idConectadoFk" to conectorPrincipal.idConectadoFk,
            "titulo" to conectorPrincipal.titulo
    )
}

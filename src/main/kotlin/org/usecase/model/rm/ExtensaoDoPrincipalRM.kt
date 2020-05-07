package org.usecase.model.rm

import org.usecase.model.resource.ExtensaoDoPrincipal
import br.com.simpli.sql.Query
import br.com.simpli.sql.ResultBuilder
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table extensao_do_principal
 * @author Simpli CLI generator
 */
object ExtensaoDoPrincipalRM {
    fun build(rs: ResultSet, alias: String = "extensao_do_principal", allowedColumns: Array<String> = selectFields(alias)) = ExtensaoDoPrincipal().apply {
        ResultBuilder(allowedColumns, rs, alias).run {
            idPrincipalFk = getLong("idPrincipalFk")
            titulo = getString("titulo")
        }
    }

    fun selectFields(alias: String = "extensao_do_principal") = arrayOf(
            "$alias.idPrincipalFk",
            "$alias.titulo"
    )

    fun fieldsToSearch(alias: String = "extensao_do_principal") = arrayOf(
            "$alias.idPrincipalFk",
            "$alias.titulo"
    )

    fun orderMap(alias: String = "extensao_do_principal") = mapOf(
            "principal" to "$alias.idPrincipalFk",
            "titulo" to "$alias.titulo"
    )

    fun updateSet(extensaoDoPrincipal: ExtensaoDoPrincipal) = mapOf(
            "titulo" to extensaoDoPrincipal.titulo
    )

    fun insertValues(extensaoDoPrincipal: ExtensaoDoPrincipal) = mapOf(
            "idPrincipalFk" to extensaoDoPrincipal.idPrincipalFk,
            "titulo" to extensaoDoPrincipal.titulo
    )
}

package org.usecase.model.rm

import org.usecase.model.resource.GrupoDoPrincipal
import br.com.simpli.sql.Query
import br.com.simpli.sql.ResultBuilder
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table grupo_do_principal
 * @author Simpli CLI generator
 */
object GrupoDoPrincipalRM {
    fun build(rs: ResultSet, alias: String = "grupo_do_principal", allowedColumns: Array<String> = selectFields(alias)) = GrupoDoPrincipal().apply {
        ResultBuilder(allowedColumns, rs, alias).run {
            idGrupoDoPrincipalPk = getLong("idGrupoDoPrincipalPk")
            titulo = getString("titulo")
        }
    }

    fun selectFields(alias: String = "grupo_do_principal") = arrayOf(
            "$alias.idGrupoDoPrincipalPk",
            "$alias.titulo"
    )

    fun fieldsToSearch(alias: String = "grupo_do_principal") = arrayOf(
            "$alias.idGrupoDoPrincipalPk",
            "$alias.titulo"
    )

    fun orderMap(alias: String = "grupo_do_principal") = mapOf(
            "idGrupoDoPrincipalPk" to "$alias.idGrupoDoPrincipalPk",
            "titulo" to "$alias.titulo"
    )

    fun updateSet(grupoDoPrincipal: GrupoDoPrincipal) = mapOf(
            "titulo" to grupoDoPrincipal.titulo
    )

    fun insertValues(grupoDoPrincipal: GrupoDoPrincipal) = mapOf(
            "titulo" to grupoDoPrincipal.titulo
    )
}

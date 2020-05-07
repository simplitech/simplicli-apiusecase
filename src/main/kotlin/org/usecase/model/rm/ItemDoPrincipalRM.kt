package org.usecase.model.rm

import org.usecase.model.resource.ItemDoPrincipal
import br.com.simpli.sql.Query
import br.com.simpli.sql.ResultBuilder
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table item_do_principal
 * @author Simpli CLI generator
 */
object ItemDoPrincipalRM {
    fun build(rs: ResultSet, alias: String = "item_do_principal", allowedColumns: Array<String> = selectFields(alias)) = ItemDoPrincipal().apply {
        ResultBuilder(allowedColumns, rs, alias).run {
            idItemDoPrincipalPk = getLong("idItemDoPrincipalPk")
            titulo = getString("titulo")
            idPrincipalFk = getLong("idPrincipalFk")
        }
    }

    fun selectFields(alias: String = "item_do_principal") = arrayOf(
            "$alias.idItemDoPrincipalPk",
            "$alias.titulo",
            "$alias.idPrincipalFk"
    )

    fun fieldsToSearch(alias: String = "item_do_principal") = arrayOf(
            "$alias.idItemDoPrincipalPk",
            "$alias.titulo"
    )

    fun orderMap(alias: String = "item_do_principal") = mapOf(
            "principal" to "$alias.idPrincipalFk",
            "idItemDoPrincipalPk" to "$alias.idItemDoPrincipalPk",
            "titulo" to "$alias.titulo"
    )

    fun updateSet(itemDoPrincipal: ItemDoPrincipal) = mapOf(
            "titulo" to itemDoPrincipal.titulo,
            "idPrincipalFk" to itemDoPrincipal.idPrincipalFk
    )

    fun insertValues(itemDoPrincipal: ItemDoPrincipal) = mapOf(
            "titulo" to itemDoPrincipal.titulo,
            "idPrincipalFk" to itemDoPrincipal.idPrincipalFk
    )
}

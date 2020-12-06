package org.usecase.model.rm

import org.usecase.model.resource.ItemDoPrincipal
import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.model.resource.GrupoDoPrincipal
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.ITEM_DO_PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.ITEM_DO_PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.ITEM_DO_PRINCIPAL_UPDATE_ALL
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table item_do_principal
 * @author Simpli CLI generator
 */
class ItemDoPrincipalRM(val permission: Permission, override var alias: String? = null) : RelationalMapper<ItemDoPrincipal>() {
    override val table = "item_do_principal"

    val idItemDoPrincipalPk = col("idItemDoPrincipalPk",
            { idItemDoPrincipalPk },
            { idItemDoPrincipalPk = it.value() })

    val titulo = col("titulo",
            { titulo },
            { titulo = it.value() })

    val idPrincipalFk = col("idPrincipalFk",
            { idPrincipalFk },
            { idPrincipalFk = it.value() })

    fun build(rs: ResultSet) = ItemDoPrincipal().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<ItemDoPrincipal>>
        get() = permission.buildArray {
            add(ITEM_DO_PRINCIPAL_READ_ALL, idItemDoPrincipalPk)
            add(ITEM_DO_PRINCIPAL_READ_ALL, titulo)
            add(ITEM_DO_PRINCIPAL_READ_ALL, idPrincipalFk)
        }

    val fieldsToSearch: Array<VirtualColumn<ItemDoPrincipal>>
        get() = permission.buildArray {
            add(ITEM_DO_PRINCIPAL_READ_ALL, idItemDoPrincipalPk)
            add(ITEM_DO_PRINCIPAL_READ_ALL, titulo)
        }

    val orderMap: Map<String, VirtualColumn<ItemDoPrincipal>>
        get() = permission.buildMap {
            add(ITEM_DO_PRINCIPAL_READ_ALL, "idPrincipalFk" to idPrincipalFk)
            add(ITEM_DO_PRINCIPAL_READ_ALL, "idItemDoPrincipalPk" to idItemDoPrincipalPk)
            add(ITEM_DO_PRINCIPAL_READ_ALL, "titulo" to titulo)
        }

    fun updateSet(itemDoPrincipal: ItemDoPrincipal) = colsToMap(itemDoPrincipal, *permission.buildArray {
        add(ITEM_DO_PRINCIPAL_UPDATE_ALL, titulo)
        add(ITEM_DO_PRINCIPAL_UPDATE_ALL, idPrincipalFk)
    })

    fun insertValues(itemDoPrincipal: ItemDoPrincipal) = colsToMap(itemDoPrincipal, *permission.buildArray {
        add(ITEM_DO_PRINCIPAL_INSERT_ALL, titulo)
        add(ITEM_DO_PRINCIPAL_INSERT_ALL, idPrincipalFk)
    })
}

package org.usecase.model.rm

import org.usecase.model.resource.ItemDoPrincipal
import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table item_do_principal
 * @author Simpli CLI generator
 */
class ItemDoPrincipalRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<ItemDoPrincipal>() {
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
            Permission.apply {
                add(idItemDoPrincipalPk, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_READ_ALL, ITEM_DO_PRINCIPAL_READ_ID_ITEM_DO_PRINCIPAL_PK)
                add(idPrincipalFk, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_READ_ALL, ITEM_DO_PRINCIPAL_READ_ID_PRINCIPAL_FK)
                add(titulo, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_READ_ALL, ITEM_DO_PRINCIPAL_READ_TITULO)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<ItemDoPrincipal>>
        get() = permission.buildArray {
            Permission.apply {
                add(idItemDoPrincipalPk, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_READ_ALL, ITEM_DO_PRINCIPAL_READ_ID_ITEM_DO_PRINCIPAL_PK)
                add(titulo, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_READ_ALL, ITEM_DO_PRINCIPAL_READ_TITULO)
            }
        }

    val orderMap: Map<String, VirtualColumn<ItemDoPrincipal>>
        get() = permission.buildMap {
            Permission.apply {
                add("idItemDoPrincipalPk" to idItemDoPrincipalPk, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_READ_ALL, ITEM_DO_PRINCIPAL_READ_ID_ITEM_DO_PRINCIPAL_PK)
                add("idPrincipalFk" to idPrincipalFk, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_READ_ALL, ITEM_DO_PRINCIPAL_READ_ID_PRINCIPAL_FK)
                add("titulo" to titulo, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_READ_ALL, ITEM_DO_PRINCIPAL_READ_TITULO)
            }
        }

    fun updateSet(itemDoPrincipal: ItemDoPrincipal) = colsToMap(itemDoPrincipal, *permission.buildArray {
        Permission.apply {
            add(idPrincipalFk, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_UPDATE_ALL, ITEM_DO_PRINCIPAL_UPDATE_ID_PRINCIPAL_FK)
            add(titulo, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_UPDATE_ALL, ITEM_DO_PRINCIPAL_UPDATE_TITULO)
        }
    })

    fun insertValues(itemDoPrincipal: ItemDoPrincipal) = colsToMap(itemDoPrincipal, *permission.buildArray {
        Permission.apply {
            add(idPrincipalFk, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_INSERT_ALL, ITEM_DO_PRINCIPAL_INSERT_ID_PRINCIPAL_FK)
            add(titulo, FULL_CONTROL, ITEM_DO_PRINCIPAL_FULL_CONTROL, ITEM_DO_PRINCIPAL_INSERT_ALL, ITEM_DO_PRINCIPAL_INSERT_TITULO)
        }
    })
}

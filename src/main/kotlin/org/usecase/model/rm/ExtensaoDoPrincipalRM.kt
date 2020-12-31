package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.ExtensaoDoPrincipal
import org.usecase.model.resource.Permission
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table extensao_do_principal
 * @author Simpli CLI generator
 */
class ExtensaoDoPrincipalRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<ExtensaoDoPrincipal>() {
    override val table = "extensao_do_principal"

    val idPrincipalFk = col("idPrincipalFk",
            { idPrincipalFk },
            { idPrincipalFk = it.value() })
    val titulo = col("titulo",
            { titulo },
            { titulo = it.value() })

    fun build(rs: ResultSet) = ExtensaoDoPrincipal().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<ExtensaoDoPrincipal>>
        get() = permission.buildArray {
            Permission.apply {
                add(idPrincipalFk, FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_READ_ALL, EXTENSAO_DO_PRINCIPAL_READ_ID_PRINCIPAL_FK)
                add(titulo, FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_READ_ALL, EXTENSAO_DO_PRINCIPAL_READ_TITULO)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<ExtensaoDoPrincipal>>
        get() = permission.buildArray {
            Permission.apply {
                add(idPrincipalFk, FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_READ_ALL, EXTENSAO_DO_PRINCIPAL_READ_ID_PRINCIPAL_FK)
                add(titulo, FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_READ_ALL, EXTENSAO_DO_PRINCIPAL_READ_TITULO)
            }
        }

    val orderMap: Map<String, VirtualColumn<ExtensaoDoPrincipal>>
        get() = permission.buildMap {
            Permission.apply {
                add("idPrincipalFk" to idPrincipalFk, FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_READ_ALL, EXTENSAO_DO_PRINCIPAL_READ_ID_PRINCIPAL_FK)
                add("titulo" to titulo, FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_READ_ALL, EXTENSAO_DO_PRINCIPAL_READ_TITULO)
            }
        }

    fun updateSet(extensaoDoPrincipal: ExtensaoDoPrincipal) = colsToMap(extensaoDoPrincipal, *permission.buildArray {
        Permission.apply {
            add(titulo, FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_UPDATE_ALL, EXTENSAO_DO_PRINCIPAL_UPDATE_TITULO)
        }
    })

    fun insertValues(extensaoDoPrincipal: ExtensaoDoPrincipal) = colsToMap(extensaoDoPrincipal, *permission.buildArray {
        Permission.apply {
            add(idPrincipalFk, FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_INSERT_ALL, EXTENSAO_DO_PRINCIPAL_INSERT_ID_PRINCIPAL_FK)
            add(titulo, FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_FULL_CONTROL, EXTENSAO_DO_PRINCIPAL_INSERT_ALL, EXTENSAO_DO_PRINCIPAL_INSERT_TITULO)
        }
    })
}

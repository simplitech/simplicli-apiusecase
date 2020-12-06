package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.model.resource.Endereco
import org.usecase.model.resource.ExtensaoDoPrincipal
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.EXTENSAO_DO_PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.EXTENSAO_DO_PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.EXTENSAO_DO_PRINCIPAL_UPDATE_ALL
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table extensao_do_principal
 * @author Simpli CLI generator
 */
class ExtensaoDoPrincipalRM(val permission: Permission, override var alias: String? = null) : RelationalMapper<ExtensaoDoPrincipal>() {
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
            add(EXTENSAO_DO_PRINCIPAL_READ_ALL, idPrincipalFk)
            add(EXTENSAO_DO_PRINCIPAL_READ_ALL, titulo)
        }

    val fieldsToSearch: Array<VirtualColumn<ExtensaoDoPrincipal>>
        get() = permission.buildArray {
            add(EXTENSAO_DO_PRINCIPAL_READ_ALL, idPrincipalFk)
            add(EXTENSAO_DO_PRINCIPAL_READ_ALL, titulo)
        }

    val orderMap: Map<String, VirtualColumn<ExtensaoDoPrincipal>>
        get() = permission.buildMap {
            add(EXTENSAO_DO_PRINCIPAL_READ_ALL, "idPrincipalFk" to idPrincipalFk)
            add(EXTENSAO_DO_PRINCIPAL_READ_ALL, "titulo" to titulo)
        }

    fun updateSet(extensaoDoPrincipal: ExtensaoDoPrincipal) = colsToMap(extensaoDoPrincipal, *permission.buildArray {
        add(EXTENSAO_DO_PRINCIPAL_UPDATE_ALL, titulo)
    })

    fun insertValues(extensaoDoPrincipal: ExtensaoDoPrincipal) = colsToMap(extensaoDoPrincipal, *permission.buildArray {
        add(EXTENSAO_DO_PRINCIPAL_INSERT_ALL, idPrincipalFk)
        add(EXTENSAO_DO_PRINCIPAL_INSERT_ALL, titulo)
    })
}

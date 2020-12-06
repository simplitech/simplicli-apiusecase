package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.model.resource.ExtensaoDoPrincipal
import org.usecase.model.resource.GrupoDoPrincipal
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.GRUPO_DO_PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.GRUPO_DO_PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.GRUPO_DO_PRINCIPAL_UPDATE_ALL
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table grupo_do_principal
 * @author Simpli CLI generator
 */
class GrupoDoPrincipalRM(val permission: Permission, override var alias: String? = null) : RelationalMapper<GrupoDoPrincipal>() {
    override val table = "grupo_do_principal"

    val idGrupoDoPrincipalPk = col("idGrupoDoPrincipalPk",
            { idGrupoDoPrincipalPk },
            { idGrupoDoPrincipalPk = it.long })

    val titulo = col("titulo",
            { titulo },
            { titulo = it.string })

    fun build(rs: ResultSet) = GrupoDoPrincipal().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<GrupoDoPrincipal>>
        get() = permission.buildArray {
            add(GRUPO_DO_PRINCIPAL_READ_ALL, idGrupoDoPrincipalPk)
            add(GRUPO_DO_PRINCIPAL_READ_ALL, titulo)
        }

    val fieldsToSearch: Array<VirtualColumn<GrupoDoPrincipal>>
        get() = permission.buildArray {
            add(GRUPO_DO_PRINCIPAL_READ_ALL, idGrupoDoPrincipalPk)
            add(GRUPO_DO_PRINCIPAL_READ_ALL, titulo)
        }

    val orderMap: Map<String, VirtualColumn<GrupoDoPrincipal>>
        get() = permission.buildMap {
            add(GRUPO_DO_PRINCIPAL_READ_ALL, "idGrupoDoPrincipalPk" to idGrupoDoPrincipalPk)
            add(GRUPO_DO_PRINCIPAL_READ_ALL, "titulo" to titulo)
        }

    fun updateSet(grupoDoPrincipal: GrupoDoPrincipal) = colsToMap(grupoDoPrincipal, *permission.buildArray {
        add(GRUPO_DO_PRINCIPAL_UPDATE_ALL, titulo)
    })

    fun insertValues(grupoDoPrincipal: GrupoDoPrincipal) = colsToMap(grupoDoPrincipal, *permission.buildArray {
        add(GRUPO_DO_PRINCIPAL_INSERT_ALL, titulo)
    })
}

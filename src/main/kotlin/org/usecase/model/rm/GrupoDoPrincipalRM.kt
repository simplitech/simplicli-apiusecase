package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.GrupoDoPrincipal
import org.usecase.model.resource.Permission
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table grupo_do_principal
 * @author Simpli CLI generator
 */
class GrupoDoPrincipalRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<GrupoDoPrincipal>() {
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
            Permission.apply {
                add(idGrupoDoPrincipalPk, FULL_CONTROL, GRUPO_DO_PRINCIPAL_FULL_CONTROL, GRUPO_DO_PRINCIPAL_READ_ALL, GRUPO_DO_PRINCIPAL_READ_ID_GRUPO_DO_PRINCIPAL_PK)
                add(titulo, FULL_CONTROL, GRUPO_DO_PRINCIPAL_FULL_CONTROL, GRUPO_DO_PRINCIPAL_READ_ALL, GRUPO_DO_PRINCIPAL_READ_TITULO)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<GrupoDoPrincipal>>
        get() = permission.buildArray {
            Permission.apply {
                add(idGrupoDoPrincipalPk, FULL_CONTROL, GRUPO_DO_PRINCIPAL_FULL_CONTROL, GRUPO_DO_PRINCIPAL_READ_ALL, GRUPO_DO_PRINCIPAL_READ_ID_GRUPO_DO_PRINCIPAL_PK)
                add(titulo, FULL_CONTROL, GRUPO_DO_PRINCIPAL_FULL_CONTROL, GRUPO_DO_PRINCIPAL_READ_ALL, GRUPO_DO_PRINCIPAL_READ_TITULO)
            }
        }

    val orderMap: Map<String, VirtualColumn<GrupoDoPrincipal>>
        get() = permission.buildMap {
            Permission.apply {
                add("idGrupoDoPrincipalPk" to idGrupoDoPrincipalPk, FULL_CONTROL, GRUPO_DO_PRINCIPAL_FULL_CONTROL, GRUPO_DO_PRINCIPAL_READ_ALL, GRUPO_DO_PRINCIPAL_READ_ID_GRUPO_DO_PRINCIPAL_PK)
                add("titulo" to titulo, FULL_CONTROL, GRUPO_DO_PRINCIPAL_FULL_CONTROL, GRUPO_DO_PRINCIPAL_READ_ALL, GRUPO_DO_PRINCIPAL_READ_TITULO)
            }
        }

    fun updateSet(grupoDoPrincipal: GrupoDoPrincipal) = colsToMap(grupoDoPrincipal, *permission.buildArray {
        Permission.apply {
            add(titulo, FULL_CONTROL, GRUPO_DO_PRINCIPAL_FULL_CONTROL, GRUPO_DO_PRINCIPAL_UPDATE_ALL, GRUPO_DO_PRINCIPAL_UPDATE_TITULO)
        }
    })

    fun insertValues(grupoDoPrincipal: GrupoDoPrincipal) = colsToMap(grupoDoPrincipal, *permission.buildArray {
        Permission.apply {
            add(titulo, FULL_CONTROL, GRUPO_DO_PRINCIPAL_FULL_CONTROL, GRUPO_DO_PRINCIPAL_INSERT_ALL, GRUPO_DO_PRINCIPAL_INSERT_TITULO)
        }
    })
}

package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.ConectorPrincipal
import org.usecase.model.resource.Permission
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table conector_principal
 * @author Simpli CLI generator
 */
class ConectorPrincipalRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<ConectorPrincipal>() {
    override val table = "conector_principal"

    val idPrincipalFk = col("idPrincipalFk",
            { idPrincipalFk },
            { idPrincipalFk = it.value() })

    val idConectadoFk = col("idConectadoFk",
            { idConectadoFk },
            { idConectadoFk = it.value() })

    val titulo = col("titulo",
            { titulo },
            { titulo = it.value() })

    fun build(rs: ResultSet) = ConectorPrincipal().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<ConectorPrincipal>>
        get() = permission.buildArray {
            Permission.apply {
                add(idPrincipalFk, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_READ_ALL, CONECTOR_PRINCIPAL_READ_ID_PRINCIPAL_FK)
                add(idConectadoFk, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_READ_ALL, CONECTOR_PRINCIPAL_READ_ID_CONECTADO_FK)
                add(titulo, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_READ_ALL, CONECTOR_PRINCIPAL_READ_TITULO)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<ConectorPrincipal>>
        get() = permission.buildArray {
            Permission.apply {
                add(idPrincipalFk, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_READ_ALL, CONECTOR_PRINCIPAL_READ_ID_PRINCIPAL_FK)
                add(idConectadoFk, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_READ_ALL, CONECTOR_PRINCIPAL_READ_ID_CONECTADO_FK)
                add(titulo, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_READ_ALL, CONECTOR_PRINCIPAL_READ_TITULO)
            }
        }

    val orderMap: Map<String, VirtualColumn<ConectorPrincipal>>
        get() = permission.buildMap {
            Permission.apply {
                add("idPrincipalFk" to idPrincipalFk, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_READ_ALL, CONECTOR_PRINCIPAL_READ_ID_PRINCIPAL_FK)
                add("idConectadoFk" to idConectadoFk, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_READ_ALL, CONECTOR_PRINCIPAL_READ_ID_CONECTADO_FK)
                add("titulo" to titulo, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_READ_ALL, CONECTOR_PRINCIPAL_READ_TITULO)
            }
        }

    fun updateSet(conectorPrincipal: ConectorPrincipal) = colsToMap(conectorPrincipal, *permission.buildArray {
        Permission.apply {
            add(titulo, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_UPDATE_ALL, CONECTOR_PRINCIPAL_UPDATE_TITULO)
        }
    })

    fun insertValues(conectorPrincipal: ConectorPrincipal) = colsToMap(conectorPrincipal, *permission.buildArray {
        Permission.apply {
            add(idPrincipalFk, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_INSERT_ALL, CONECTOR_PRINCIPAL_INSERT_ID_PRINCIPAL_FK)
            add(idConectadoFk, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_INSERT_ALL, CONECTOR_PRINCIPAL_INSERT_ID_CONECTADO_FK)
            add(titulo, FULL_CONTROL, CONECTOR_PRINCIPAL_FULL_CONTROL, CONECTOR_PRINCIPAL_INSERT_ALL, CONECTOR_PRINCIPAL_INSERT_TITULO)
        }
    })
}

package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.model.resource.Conectado
import org.usecase.model.resource.ConectorPrincipal
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.CONECTOR_PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.CONECTOR_PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.CONECTOR_PRINCIPAL_UPDATE_ALL
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table conector_principal
 * @author Simpli CLI generator
 */
class ConectorPrincipalRM(val permission: Permission, override var alias: String? = null) : RelationalMapper<ConectorPrincipal>() {
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
            add(CONECTOR_PRINCIPAL_READ_ALL, idPrincipalFk)
            add(CONECTOR_PRINCIPAL_READ_ALL, idConectadoFk)
            add(CONECTOR_PRINCIPAL_READ_ALL, titulo)
        }

    val fieldsToSearch: Array<VirtualColumn<ConectorPrincipal>>
        get() = permission.buildArray {
            add(CONECTOR_PRINCIPAL_READ_ALL, idPrincipalFk)
            add(CONECTOR_PRINCIPAL_READ_ALL, idConectadoFk)
            add(CONECTOR_PRINCIPAL_READ_ALL, titulo)
        }

    val orderMap: Map<String, VirtualColumn<ConectorPrincipal>>
        get() = permission.buildMap {
            add(CONECTOR_PRINCIPAL_READ_ALL, "idPrincipalFk" to idPrincipalFk)
            add(CONECTOR_PRINCIPAL_READ_ALL, "idConectadoFk" to idConectadoFk)
            add(CONECTOR_PRINCIPAL_READ_ALL, "titulo" to titulo)
        }

    fun updateSet(conectorPrincipal: ConectorPrincipal) = colsToMap(conectorPrincipal, *permission.buildArray {
        add(CONECTOR_PRINCIPAL_UPDATE_ALL, titulo)
    })

    fun insertValues(conectorPrincipal: ConectorPrincipal) = colsToMap(conectorPrincipal, *permission.buildArray {
        add(CONECTOR_PRINCIPAL_INSERT_ALL, idPrincipalFk)
        add(CONECTOR_PRINCIPAL_INSERT_ALL, idConectadoFk)
        add(CONECTOR_PRINCIPAL_INSERT_ALL, titulo)
    })
}

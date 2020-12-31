package org.usecase.model.rm

import org.usecase.model.resource.Conectado
import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table conectado
 * @author Simpli CLI generator
 */
class ConectadoRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<Conectado>() {
    override val table = "conectado"

    val idConectadoPk = col("idConectadoPk",
            { idConectadoPk },
            { idConectadoPk = it.value() })

    val titulo = col("titulo",
            { titulo },
            { titulo = it.value() })

    fun build(rs: ResultSet) = Conectado().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<Conectado>>
        get() = permission.buildArray {
            Permission.apply {
                add(idConectadoPk, FULL_CONTROL, CONECTADO_FULL_CONTROL, CONECTADO_READ_ALL, CONECTADO_READ_ID_CONECTADO_PK)
                add(titulo, FULL_CONTROL, CONECTADO_FULL_CONTROL, CONECTADO_READ_ALL, CONECTADO_READ_TITULO)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<Conectado>>
        get() = permission.buildArray {
            Permission.apply {
                add(idConectadoPk, FULL_CONTROL, CONECTADO_FULL_CONTROL, CONECTADO_READ_ALL, CONECTADO_READ_ID_CONECTADO_PK)
                add(titulo, FULL_CONTROL, CONECTADO_FULL_CONTROL, CONECTADO_READ_ALL, CONECTADO_READ_TITULO)
            }
        }

    val orderMap: Map<String, VirtualColumn<Conectado>>
        get() = permission.buildMap {
            Permission.apply {
                add("idConectadoPk" to idConectadoPk, FULL_CONTROL, CONECTADO_FULL_CONTROL, CONECTADO_READ_ALL, CONECTADO_READ_ID_CONECTADO_PK)
                add("titulo" to titulo, FULL_CONTROL, CONECTADO_FULL_CONTROL, CONECTADO_READ_ALL, CONECTADO_READ_TITULO)
            }
        }

    fun updateSet(conectado: Conectado) = colsToMap(conectado, *permission.buildArray {
        Permission.apply {
            add(titulo, FULL_CONTROL, CONECTADO_FULL_CONTROL, CONECTADO_UPDATE_ALL, CONECTADO_UPDATE_TITULO)
        }
    })

    fun insertValues(conectado: Conectado) = colsToMap(conectado, *permission.buildArray {
        Permission.apply {
            add(titulo, FULL_CONTROL, CONECTADO_FULL_CONTROL, CONECTADO_INSERT_ALL, CONECTADO_INSERT_TITULO)
        }
    })
}

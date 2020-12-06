package org.usecase.model.rm

import org.usecase.model.resource.Conectado
import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.CONECTADO_INSERT_ALL
import org.usecase.user.context.Permission.Companion.CONECTADO_READ_ALL
import org.usecase.user.context.Permission.Companion.CONECTADO_UPDATE_ALL
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table conectado
 * @author Simpli CLI generator
 */
class ConectadoRM(val permission: Permission, override var alias: String? = null) : RelationalMapper<Conectado>() {
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
            add(CONECTADO_READ_ALL, idConectadoPk)
            add(CONECTADO_READ_ALL, titulo)
        }

    val fieldsToSearch: Array<VirtualColumn<Conectado>>
        get() = permission.buildArray {
            add(CONECTADO_READ_ALL, idConectadoPk)
            add(CONECTADO_READ_ALL, titulo)
        }

    val orderMap: Map<String, VirtualColumn<Conectado>>
        get() = permission.buildMap {
            add(CONECTADO_READ_ALL, "idConectadoPk" to idConectadoPk)
            add(CONECTADO_READ_ALL, "titulo" to titulo)
        }

    fun updateSet(conectado: Conectado) = colsToMap(conectado, *permission.buildArray {
        add(CONECTADO_UPDATE_ALL, titulo)
    })

    fun insertValues(conectado: Conectado) = colsToMap(conectado, *permission.buildArray {
        add(CONECTADO_INSERT_ALL, titulo)
    })
}

package org.usecase.model.rm

import org.usecase.model.resource.Endereco
import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.model.resource.Conectado
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.ENDERECO_INSERT_ALL
import org.usecase.user.context.Permission.Companion.ENDERECO_READ_ALL
import org.usecase.user.context.Permission.Companion.ENDERECO_UPDATE_ALL
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table endereco
 * @author Simpli CLI generator
 */
class EnderecoRM(val permission: Permission, override var alias: String? = null) : RelationalMapper<Endereco>() {
    override val table = "endereco"

    val idEnderecoPk = col("idEnderecoPk",
            { idEnderecoPk },
            { idEnderecoPk = it.value() })

    val cep = col("cep",
            { cep },
            { cep = it.value() })

    val zipcode = col("zipcode",
            { zipcode },
            { zipcode = it.value() })

    val rua = col("rua",
            { rua },
            { rua = it.value() })

    val nro = col("nro",
            { nro },
            { nro = it.value() })

    val cidade = col("cidade",
            { cidade },
            { cidade = it.value() })

    val uf = col("uf",
            { uf },
            { uf = it.value() })

    val latitude = col("latitude",
            { latitude },
            { latitude = it.value() })

    val longitude = col("longitude",
            { longitude },
            { longitude = it.value() })

    fun build(rs: ResultSet) = Endereco().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<Endereco>>
        get() = permission.buildArray {
            add(ENDERECO_READ_ALL, idEnderecoPk)
            add(ENDERECO_READ_ALL, cep)
            add(ENDERECO_READ_ALL, zipcode)
            add(ENDERECO_READ_ALL, rua)
            add(ENDERECO_READ_ALL, nro)
            add(ENDERECO_READ_ALL, cidade)
            add(ENDERECO_READ_ALL, uf)
            add(ENDERECO_READ_ALL, latitude)
            add(ENDERECO_READ_ALL, longitude)
        }

    val fieldsToSearch: Array<VirtualColumn<Endereco>>
        get() = permission.buildArray {
            add(ENDERECO_READ_ALL, idEnderecoPk)
            add(ENDERECO_READ_ALL, cep)
            add(ENDERECO_READ_ALL, zipcode)
            add(ENDERECO_READ_ALL, rua)
            add(ENDERECO_READ_ALL, cidade)
            add(ENDERECO_READ_ALL, uf)
        }

    val orderMap: Map<String, VirtualColumn<Endereco>>
        get() = permission.buildMap {
            add(ENDERECO_READ_ALL, "idEnderecoPk" to idEnderecoPk)
            add(ENDERECO_READ_ALL, "cep" to cep)
            add(ENDERECO_READ_ALL, "zipcode" to zipcode)
            add(ENDERECO_READ_ALL, "rua" to rua)
            add(ENDERECO_READ_ALL, "nro" to nro)
            add(ENDERECO_READ_ALL, "cidade" to cidade)
            add(ENDERECO_READ_ALL, "uf" to uf)
            add(ENDERECO_READ_ALL, "latitude" to latitude)
            add(ENDERECO_READ_ALL, "longitude" to longitude)
        }

    fun updateSet(endereco: Endereco) = colsToMap(endereco, *permission.buildArray {
        add(ENDERECO_UPDATE_ALL, cep)
        add(ENDERECO_UPDATE_ALL, zipcode)
        add(ENDERECO_UPDATE_ALL, rua)
        add(ENDERECO_UPDATE_ALL, nro)
        add(ENDERECO_UPDATE_ALL, cidade)
        add(ENDERECO_UPDATE_ALL, uf)
        add(ENDERECO_UPDATE_ALL, latitude)
        add(ENDERECO_UPDATE_ALL, longitude)
    })

    fun insertValues(endereco: Endereco) = colsToMap(endereco, *permission.buildArray {
        add(ENDERECO_INSERT_ALL, cep)
        add(ENDERECO_INSERT_ALL, zipcode)
        add(ENDERECO_INSERT_ALL, rua)
        add(ENDERECO_INSERT_ALL, nro)
        add(ENDERECO_INSERT_ALL, cidade)
        add(ENDERECO_INSERT_ALL, uf)
        add(ENDERECO_INSERT_ALL, latitude)
        add(ENDERECO_INSERT_ALL, longitude)
    })
}

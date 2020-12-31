package org.usecase.model.rm

import org.usecase.model.resource.Endereco
import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Permission
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table endereco
 * @author Simpli CLI generator
 */
class EnderecoRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<Endereco>() {
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
            Permission.apply {
                add(idEnderecoPk, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_ID_ENDERECO_PK)
                add(cep, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_CEP)
                add(zipcode, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_ZIPCODE)
                add(rua, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_RUA)
                add(nro, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_NRO)
                add(cidade, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_CIDADE)
                add(uf, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_UF)
                add(latitude, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_LATITUDE)
                add(longitude, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_LONGITUDE)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<Endereco>>
        get() = permission.buildArray {
            Permission.apply {
                add(idEnderecoPk, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_ID_ENDERECO_PK)
                add(cep, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_CEP)
                add(zipcode, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_ZIPCODE)
                add(rua, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_RUA)
                add(cidade, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_CIDADE)
                add(uf, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_UF)
            }
        }

    val orderMap: Map<String, VirtualColumn<Endereco>>
        get() = permission.buildMap {
            Permission.apply {
                add("idEnderecoPk" to idEnderecoPk, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_ID_ENDERECO_PK)
                add("cep" to cep, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_CEP)
                add("zipcode" to zipcode, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_ZIPCODE)
                add("rua" to rua, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_RUA)
                add("nro" to nro, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_NRO)
                add("cidade" to cidade, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_CIDADE)
                add("uf" to uf, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_UF)
                add("latitude" to latitude, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_LATITUDE)
                add("longitude" to longitude, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_READ_ALL, ENDERECO_READ_LONGITUDE)
            }
        }

    fun updateSet(endereco: Endereco) = colsToMap(endereco, *permission.buildArray {
        Permission.apply {
            add(cep, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_UPDATE_ALL, ENDERECO_UPDATE_CEP)
            add(zipcode, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_UPDATE_ALL, ENDERECO_UPDATE_ZIPCODE)
            add(rua, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_UPDATE_ALL, ENDERECO_UPDATE_RUA)
            add(nro, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_UPDATE_ALL, ENDERECO_UPDATE_NRO)
            add(cidade, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_UPDATE_ALL, ENDERECO_UPDATE_CIDADE)
            add(uf, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_UPDATE_ALL, ENDERECO_UPDATE_UF)
            add(latitude, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_UPDATE_ALL, ENDERECO_UPDATE_LATITUDE)
            add(longitude, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_UPDATE_ALL, ENDERECO_UPDATE_LONGITUDE)
        }
    })

    fun insertValues(endereco: Endereco) = colsToMap(endereco, *permission.buildArray {
        Permission.apply {
            add(cep, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_INSERT_ALL, ENDERECO_INSERT_CEP)
            add(zipcode, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_INSERT_ALL, ENDERECO_INSERT_ZIPCODE)
            add(rua, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_INSERT_ALL, ENDERECO_INSERT_RUA)
            add(nro, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_INSERT_ALL, ENDERECO_INSERT_NRO)
            add(cidade, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_INSERT_ALL, ENDERECO_INSERT_CIDADE)
            add(uf, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_INSERT_ALL, ENDERECO_INSERT_UF)
            add(latitude, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_INSERT_ALL, ENDERECO_INSERT_LATITUDE)
            add(longitude, FULL_CONTROL, ENDERECO_FULL_CONTROL, ENDERECO_INSERT_ALL, ENDERECO_INSERT_LONGITUDE)
        }
    })
}

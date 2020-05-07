package org.usecase.model.rm

import org.usecase.model.resource.Endereco
import br.com.simpli.sql.Query
import br.com.simpli.sql.ResultBuilder
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table endereco
 * @author Simpli CLI generator
 */
object EnderecoRM {
    fun build(rs: ResultSet, alias: String = "endereco", allowedColumns: Array<String> = selectFields(alias)) = Endereco().apply {
        ResultBuilder(allowedColumns, rs, alias).run {
            idEnderecoPk = getLong("idEnderecoPk")
            cep = getString("cep")
            zipcode = getString("zipcode")
            rua = getString("rua")
            nro = getLongOrNull("nro")
            cidade = getString("cidade")
            uf = getString("uf")
            latitude = getDoubleOrNull("latitude")
            longitude = getDoubleOrNull("longitude")
        }
    }

    fun selectFields(alias: String = "endereco") = arrayOf(
            "$alias.idEnderecoPk",
            "$alias.cep",
            "$alias.zipcode",
            "$alias.rua",
            "$alias.nro",
            "$alias.cidade",
            "$alias.uf",
            "$alias.latitude",
            "$alias.longitude"
    )

    fun fieldsToSearch(alias: String = "endereco") = arrayOf(
            "$alias.idEnderecoPk",
            "$alias.cep",
            "$alias.zipcode",
            "$alias.rua",
            "$alias.cidade",
            "$alias.uf"
    )

    fun orderMap(alias: String = "endereco") = mapOf(
            "idEnderecoPk" to "$alias.idEnderecoPk",
            "cep" to "$alias.cep",
            "zipcode" to "$alias.zipcode",
            "rua" to "$alias.rua",
            "nro" to "$alias.nro",
            "cidade" to "$alias.cidade",
            "uf" to "$alias.uf",
            "latitude" to "$alias.latitude",
            "longitude" to "$alias.longitude"
    )

    fun updateSet(endereco: Endereco) = mapOf(
            "cep" to endereco.cep,
            "zipcode" to endereco.zipcode,
            "rua" to endereco.rua,
            "nro" to endereco.nro,
            "cidade" to endereco.cidade,
            "uf" to endereco.uf,
            "latitude" to endereco.latitude,
            "longitude" to endereco.longitude
    )

    fun insertValues(endereco: Endereco) = mapOf(
            "cep" to endereco.cep,
            "zipcode" to endereco.zipcode,
            "rua" to endereco.rua,
            "nro" to endereco.nro,
            "cidade" to endereco.cidade,
            "uf" to endereco.uf,
            "latitude" to endereco.latitude,
            "longitude" to endereco.longitude
    )
}

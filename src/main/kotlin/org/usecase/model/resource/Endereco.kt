package org.usecase.model.resource

import org.usecase.exception.response.BadRequestException
import org.usecase.param.DefaultParam
import br.com.simpli.model.LanguageHolder
import br.com.simpli.sql.getDouble
import br.com.simpli.sql.getDoubleOrNull
import br.com.simpli.sql.getLong
import br.com.simpli.sql.getString
import br.com.simpli.sql.getLongOrNull
import br.com.simpli.sql.getBoolean
import br.com.simpli.sql.getBooleanOrNull
import br.com.simpli.sql.getTimestamp
import br.com.simpli.sql.Query
import br.com.simpli.tools.Validator
import io.swagger.v3.oas.annotations.media.Schema
import java.sql.ResultSet
import java.util.Date
import javax.ws.rs.PathParam
import javax.xml.bind.annotation.XmlRootElement

/**
 * Reference model of table endereco
 * @author Simpli CLI generator
 */
class Endereco() {
    @Schema(required = true, maxLength = 11)
    var idEnderecoPk: Long = 0

    @Schema(maxLength = 45) var cep: String? = null
    @Schema(maxLength = 45) var zipcode: String? = null
    @Schema(maxLength = 45) var rua: String? = null
    @Schema(maxLength = 11) var nro: Long? = null
    @Schema(maxLength = 45) var cidade: String? = null
    @Schema(maxLength = 45) var uf: String? = null

    var latitude: Double? = null
    var longitude: Double? = null

    var id
        @Schema(hidden = true)
        get() = idEnderecoPk
        set(value) {
            idEnderecoPk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (cep?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Endereco.cep"], 45))
        }
        if (zipcode?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Endereco.zipcode"], 45))
        }
        if (rua?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Endereco.rua"], 45))
        }
        if (cidade?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Endereco.cidade"], 45))
        }
        if (uf?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Endereco.uf"], 45))
        }
    }

    constructor(rs: ResultSet, alias: String = "endereco") : this() {
        idEnderecoPk = rs.getLong(alias, "idEnderecoPk")
        cep = rs.getString(alias, "cep")
        zipcode = rs.getString(alias, "zipcode")
        rua = rs.getString(alias, "rua")
        nro = rs.getLongOrNull(alias, "nro")
        cidade = rs.getString(alias, "cidade")
        uf = rs.getString(alias, "uf")
        latitude = rs.getDoubleOrNull(alias, "latitude")
        longitude = rs.getDoubleOrNull(alias, "longitude")
    }

    fun updateSet() = mapOf(
            "cep" to cep,
            "zipcode" to zipcode,
            "rua" to rua,
            "nro" to nro,
            "cidade" to cidade,
            "uf" to uf,
            "latitude" to latitude,
            "longitude" to longitude
    )

    fun insertValues() = mapOf(
            "cep" to cep,
            "zipcode" to zipcode,
            "rua" to rua,
            "nro" to nro,
            "cidade" to cidade,
            "uf" to uf,
            "latitude" to latitude,
            "longitude" to longitude
    )

    companion object {
        val orderMap = mapOf(
                "idEnderecoPk" to "endereco.idEnderecoPk",
                "cep" to "endereco.cep",
                "zipcode" to "endereco.zipcode",
                "rua" to "endereco.rua",
                "nro" to "endereco.nro",
                "cidade" to "endereco.cidade",
                "uf" to "endereco.uf",
                "latitude" to "endereco.latitude",
                "longitude" to "endereco.longitude"
        )
    }
}

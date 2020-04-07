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
 * Reference model of table conector_principal
 * @author Simpli CLI generator
 */
class ConectorPrincipal() {
    @Schema(required = true, maxLength = 11)
    var idPrincipalFk: Long = 0

    @Schema(required = true, maxLength = 11)
    var idConectadoFk: Long = 0

    var conectado: Conectado? = null
    var principal: Principal? = null

    @Schema(required = true, maxLength = 45)
    var titulo: String? = null

    var id1
        @Schema(hidden = true)
        get() = idPrincipalFk
        set(value) {
            idPrincipalFk = value
        }

    var id2
        @Schema(hidden = true)
        get() = idConectadoFk
        set(value) {
            idConectadoFk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (titulo.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["ConectorPrincipal.titulo"]))
        }
        if (titulo?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["ConectorPrincipal.titulo"], 45))
        }
    }

    open class RequiredPathId : DefaultParam.Auth() {
        @PathParam("id1")
        @Schema(required = true)
        var id1: Long? = null

        @PathParam("id2")
        @Schema(required = true)
        var id2: Long? = null
    }

    constructor(rs: ResultSet, alias: String = "conector_principal") : this() {
        idPrincipalFk = rs.getLong(alias, "idPrincipalFk")
        idConectadoFk = rs.getLong(alias, "idConectadoFk")
        titulo = rs.getString(alias, "titulo")
    }

    fun updateSet() = mapOf(
            "titulo" to titulo
    )

    fun insertValues() = mapOf(
            "idPrincipalFk" to idPrincipalFk,
            "idConectadoFk" to idConectadoFk,
            "titulo" to titulo
    )

    companion object {
        val orderMap = mapOf(
                "idPrincipalFk" to "conector_principal.idPrincipalFk",
                "idConectadoFk" to "conector_principal.idConectadoFk",
                "titulo" to "conector_principal.titulo"
        )
    }
}

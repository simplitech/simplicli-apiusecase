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
 * Reference model of table extensao_do_principal
 * @author Simpli CLI generator
 */
@XmlRootElement
class ExtensaoDoPrincipal() {
    @Schema(required = true, maxLength = 11)
    var idPrincipalFk: Long = 0

    var principal: Principal? = null

    @Schema(required = true, maxLength = 45)
    var titulo: String? = null

    var id
        @Schema(hidden = true)
        get() = idPrincipalFk
        set(value) {
            idPrincipalFk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (titulo.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["ExtensaoDoPrincipal.titulo"]))
        }
        if (titulo?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["ExtensaoDoPrincipal.titulo"], 45))
        }
    }

    constructor(rs: ResultSet, alias: String = "extensao_do_principal") : this() {
        idPrincipalFk = rs.getLong(alias, "idPrincipalFk")
        titulo = rs.getString(alias, "titulo")
    }

    fun updateSet() = mapOf(
            "titulo" to titulo
    )

    fun insertValues() = mapOf(
            "idPrincipalFk" to idPrincipalFk,
            "titulo" to titulo
    )

    companion object {
        val orderMap = mapOf(
                "idPrincipalFk" to "extensao_do_principal.idPrincipalFk",
                "titulo" to "extensao_do_principal.titulo"
        )
    }
}

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
 * Reference model of table grupo_do_principal
 * @author Simpli CLI generator
 */
class GrupoDoPrincipal() {
    @Schema(required = true, maxLength = 11)
    var idGrupoDoPrincipalPk: Long = 0

    @Schema(required = true, maxLength = 45)
    var titulo: String? = null

    var id
        @Schema(hidden = true)
        get() = idGrupoDoPrincipalPk
        set(value) {
            idGrupoDoPrincipalPk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (titulo.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["GrupoDoPrincipal.titulo"]))
        }
        if (titulo?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["GrupoDoPrincipal.titulo"], 45))
        }
    }

    constructor(rs: ResultSet, alias: String = "grupo_do_principal") : this() {
        idGrupoDoPrincipalPk = rs.getLong(alias, "idGrupoDoPrincipalPk")
        titulo = rs.getString(alias, "titulo")
    }

    fun updateSet() = mapOf(
            "titulo" to titulo
    )

    fun insertValues() = mapOf(
            "titulo" to titulo
    )

    companion object {
        val orderMap = mapOf(
                "idGrupoDoPrincipalPk" to "grupo_do_principal.idGrupoDoPrincipalPk",
                "titulo" to "grupo_do_principal.titulo"
        )
    }
}

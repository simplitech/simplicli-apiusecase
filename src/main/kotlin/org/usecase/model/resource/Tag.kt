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
 * Reference model of table tag
 * @author Simpli CLI generator
 */
class Tag() {
    @Schema(required = true, maxLength = 11)
    var idTagPk: Long = 0

    var tagPrincipal: MutableList<Principal>? = null

    @Schema(required = true, maxLength = 45)
    var titulo: String? = null

    var id
        @Schema(hidden = true)
        get() = idTagPk
        set(value) {
            idTagPk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (titulo.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["Tag.titulo"]))
        }
        if (titulo?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Tag.titulo"], 45))
        }
    }

    constructor(rs: ResultSet, alias: String = "tag") : this() {
        idTagPk = rs.getLong(alias, "idTagPk")
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
                "idTagPk" to "tag.idTagPk",
                "titulo" to "tag.titulo"
        )
    }
}

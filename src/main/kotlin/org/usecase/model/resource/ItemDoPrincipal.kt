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
 * Reference model of table item_do_principal
 * @author Simpli CLI generator
 */
@XmlRootElement
class ItemDoPrincipal() {
    @Schema(required = true, maxLength = 11)
    var idItemDoPrincipalPk: Long = 0

    var principal: Principal? = null

    @Schema(required = true, maxLength = 45)
    var titulo: String? = null

    var id
        @Schema(hidden = true)
        get() = idItemDoPrincipalPk
        set(value) {
            idItemDoPrincipalPk = value
        }

    var idPrincipalFk: Long
        @Schema(required = true, maxLength = 11)
        get() = principal?.idPrincipalPk ?: 0
        set(value) {
            if (principal == null) {
                principal = Principal()
            }
            principal?.idPrincipalPk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (titulo.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["ItemDoPrincipal.titulo"]))
        }
        if (titulo?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["ItemDoPrincipal.titulo"], 45))
        }
        if (idPrincipalFk == 0L) {
            throw BadRequestException(lang.cannotBeNull(lang["ItemDoPrincipal.idPrincipalFk"]))
        }
    }

    constructor(rs: ResultSet, alias: String = "item_do_principal") : this() {
        idItemDoPrincipalPk = rs.getLong(alias, "idItemDoPrincipalPk")
        titulo = rs.getString(alias, "titulo")
        idPrincipalFk = rs.getLong(alias, "idPrincipalFk")
    }

    fun updateSet() = mapOf(
            "titulo" to titulo,
            "idPrincipalFk" to idPrincipalFk
    )

    fun insertValues() = mapOf(
            "titulo" to titulo,
            "idPrincipalFk" to idPrincipalFk
    )

    companion object {
        val orderMap = mapOf(
                "idItemDoPrincipalPk" to "item_do_principal.idItemDoPrincipalPk",
                "titulo" to "item_do_principal.titulo",
                "idPrincipalFk" to "item_do_principal.idPrincipalFk"
        )
    }
}

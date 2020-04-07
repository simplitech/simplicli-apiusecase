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
 * Reference model of table user
 * @author Simpli CLI generator
 */
class User() {
    @Schema(required = true, maxLength = 11)
    var idUserPk: Long = 0

    @Schema(required = true, maxLength = 45)
    var email: String? = null

    @Schema(required = true, maxLength = 200)
    var senha: String? = null

    var id
        @Schema(hidden = true)
        get() = idUserPk
        set(value) {
            idUserPk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (email.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["User.email"]))
        }
        if (email?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["User.email"], 45))
        }
        if (email != null && !Validator.isEmail(email)) {
            throw BadRequestException(lang.isNotAValidEmail(lang["User.email"]))
        }
        if (senha.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["User.senha"]))
        }
        if (senha?.length ?: 0 > 200) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["User.senha"], 200))
        }
    }

    constructor(rs: ResultSet, alias: String = "user") : this() {
        idUserPk = rs.getLong(alias, "idUserPk")
        email = rs.getString(alias, "email")
        senha = rs.getString(alias, "senha")
    }

    fun updateSet() = mapOf(
            "email" to email,
            "senha" to Query("IF(? IS NOT NULL, SHA2(?, 256), senha)", senha, senha)
    )

    fun insertValues() = mapOf(
            "email" to email,
            "senha" to Query("SHA2(?, 256)", senha)
    )

    companion object {
        val orderMap = mapOf(
                "idUserPk" to "user.idUserPk",
                "email" to "user.email"
        )
    }
}

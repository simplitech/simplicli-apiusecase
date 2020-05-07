package org.usecase.model.resource

import org.usecase.exception.response.BadRequestException
import org.usecase.model.param.DefaultParam
import br.com.simpli.model.LanguageHolder
import br.com.simpli.tools.Validator
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.ws.rs.PathParam

/**
 * Reference model of table endereco
 * @author Simpli CLI generator
 */
class Endereco {
    @Schema(required = true) var idEnderecoPk: Long = 0

    @Schema(maxLength = 45) var cep: String? = null
    @Schema(maxLength = 45) var zipcode: String? = null
    @Schema(maxLength = 45) var rua: String? = null
    @Schema(maxLength = 45) var cidade: String? = null
    @Schema(maxLength = 45) var uf: String? = null

    var nro: Long? = null
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
}

package org.usecase.model.resource

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

/**
 * Reference model of table principal
 * Note: the main model of generator usecase
 *
 * @author Simpli CLI generator
 */
@Schema(description = "the main model of generator usecase")
class Principal {
    @Schema(required = true) var idPrincipalPk: Long = 0

    var grupoDoPrincipal: GrupoDoPrincipal? = null
    var grupoDoPrincipalFacultativo: GrupoDoPrincipal? = null
    var tagPrincipal: MutableList<Tag>? = null

    @Schema(required = true, maxLength = 160, description = "principal’s mandatory text")
    var textoObrigatorio: String? = null

    @Schema(required = true, maxLength = 40)
    var unico: String? = null

    @Schema(required = true) var decimalObrigatorio: Double? = null
    @Schema(required = true) var inteiroObrigatorio: Long? = null
    @Schema(required = true) var booleanoObrigatorio: Boolean? = null
    @Schema(required = true) var dataObrigatoria: Date? = null
    @Schema(required = true) var datahoraObrigatoria: Date? = null
    @Schema(required = true) var ativo: Boolean? = null
    @Schema(required = true) var dataCriacao: Date? = null

    @Schema(maxLength = 45) var textoFacultativo: String? = null
    @Schema(maxLength = 200) var email: String? = null
    @Schema(maxLength = 200) var urlImagem: String? = null
    @Schema(maxLength = 200) var url: String? = null
    @Schema(maxLength = 45) var nome: String? = null
    @Schema(maxLength = 45) var titulo: String? = null
    @Schema(maxLength = 45) var cpf: String? = null
    @Schema(maxLength = 45) var cnpj: String? = null
    @Schema(maxLength = 45) var rg: String? = null
    @Schema(maxLength = 45) var celular: String? = null
    @Schema(maxLength = 300) var textoGrande: String? = null
    @Schema(maxLength = 200) var snakeCase: String? = null

    var decimalFacultativo: Double? = null
    var inteiroFacultativo: Long? = null
    var booleanoFacultativo: Boolean? = null
    var dataFacultativa: Date? = null
    var datahoraFacultativa: Date? = null
    var dataAlteracao: Date? = null
    var preco: Double? = null

    @Schema(maxLength = 200) var senha: String? = null

    var id
        @Schema(hidden = true)
        get() = idPrincipalPk
        set(value) {
            idPrincipalPk = value
        }

    var idGrupoDoPrincipalFk: Long
        @Schema(required = true)
        get() = grupoDoPrincipal?.idGrupoDoPrincipalPk ?: 0
        set(value) {
            grupoDoPrincipal = if (value == 0L) { null } else {
                (grupoDoPrincipal ?: GrupoDoPrincipal()).apply {
                    idGrupoDoPrincipalPk = value
                }
            }
        }

    var idGrupoDoPrincipalFacultativoFk: Long?
        get() = grupoDoPrincipalFacultativo?.idGrupoDoPrincipalPk
        set(value) {
            grupoDoPrincipalFacultativo = if (value == null || value == 0L) { null } else {
                (grupoDoPrincipalFacultativo ?: GrupoDoPrincipal()).apply {
                    idGrupoDoPrincipalPk = value
                }
            }
        }
}

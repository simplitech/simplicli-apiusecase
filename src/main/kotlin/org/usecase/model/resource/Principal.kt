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
 * Reference model of table principal
 * Note: the main model of generator usecase
 *
 * @author Simpli CLI generator
 */
@XmlRootElement
@Schema(description = "the main model of generator usecase")
class Principal() {
    @Schema(required = true, maxLength = 11)
    var idPrincipalPk: Long = 0

    var grupoDoPrincipal1: GrupoDoPrincipal? = null
    var grupoDoPrincipal2: GrupoDoPrincipal? = null
    var tagPrincipal: MutableList<Tag>? = null

    @Schema(required = true, maxLength = 160, description = "principal’s mandatory text")
    var textoObrigatorio: String? = null

    @Schema(required = true, maxLength = 11)
    var inteiroObrigatorio: Long? = null

    @Schema(required = true, maxLength = 40)
    var unico: String? = null

    @Schema(required = true) var decimalObrigatorio: Double? = null
    @Schema(required = true) var booleanoObrigatorio: Boolean? = null
    @Schema(required = true) var dataObrigatoria: Date? = null
    @Schema(required = true) var datahoraObrigatoria: Date? = null
    @Schema(required = true) var ativo: Boolean? = null
    @Schema(required = true) var dataCriacao: Date? = null

    @Schema(maxLength = 45) var textoFacultativo: String? = null
    @Schema(maxLength = 11) var inteiroFacultativo: Long? = null
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
        @Schema(required = true, maxLength = 11)
        get() = grupoDoPrincipal1?.idGrupoDoPrincipalPk ?: 0
        set(value) {
            if (grupoDoPrincipal1 == null) {
                grupoDoPrincipal1 = GrupoDoPrincipal()
            }
            grupoDoPrincipal1?.idGrupoDoPrincipalPk = value
        }

    var idGrupoDoPrincipalFacultativoFk: Long?
        @Schema(maxLength = 11)
        get() = grupoDoPrincipal2?.idGrupoDoPrincipalPk
        set(value) {
            if (value == null) {
                grupoDoPrincipal2 = null
                return
            }
            if (grupoDoPrincipal2 == null) {
                grupoDoPrincipal2 = GrupoDoPrincipal()
            }
            grupoDoPrincipal2?.idGrupoDoPrincipalPk = value
        }

    fun validate(lang: LanguageHolder) {
        // TODO: review generated method
        if (textoObrigatorio.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["Principal.textoObrigatorio"]))
        }
        if (textoObrigatorio?.length ?: 0 > 160) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.textoObrigatorio"], 160))
        }
        if (textoFacultativo?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.textoFacultativo"], 45))
        }
        if (inteiroObrigatorio == 0L) {
            throw BadRequestException(lang.cannotBeNull(lang["Principal.inteiroObrigatorio"]))
        }
        if (dataObrigatoria == null) {
            throw BadRequestException(lang.cannotBeNull(lang["Principal.dataObrigatoria"]))
        }
        if (datahoraObrigatoria == null) {
            throw BadRequestException(lang.cannotBeNull(lang["Principal.datahoraObrigatoria"]))
        }
        if (email?.length ?: 0 > 200) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.email"], 200))
        }
        if (email != null && !Validator.isEmail(email)) {
            throw BadRequestException(lang.isNotAValidEmail(lang["Principal.email"]))
        }
        if (senha?.length ?: 0 > 200) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.senha"], 200))
        }
        if (urlImagem?.length ?: 0 > 200) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.urlImagem"], 200))
        }
        if (url?.length ?: 0 > 200) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.url"], 200))
        }
        if (idGrupoDoPrincipalFk == 0L) {
            throw BadRequestException(lang.cannotBeNull(lang["Principal.idGrupoDoPrincipalFk"]))
        }
        if (unico.isNullOrBlank()) {
            throw BadRequestException(lang.cannotBeNull(lang["Principal.unico"]))
        }
        if (unico?.length ?: 0 > 40) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.unico"], 40))
        }
        if (dataCriacao == null) {
            throw BadRequestException(lang.cannotBeNull(lang["Principal.dataCriacao"]))
        }
        if (nome?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.nome"], 45))
        }
        if (titulo?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.titulo"], 45))
        }
        if (cpf?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.cpf"], 45))
        }
        if (cpf != null && !Validator.isCPF(cpf)) {
            throw BadRequestException(lang.isNotAValidCPF(lang["Principal.cpf"]))
        }
        if (cnpj?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.cnpj"], 45))
        }
        if (cnpj != null && !Validator.isCNPJ(cnpj ?: "")) {
            throw BadRequestException(lang.isNotAValidCNPJ(lang["Principal.cnpj"]))
        }
        if (rg?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.rg"], 45))
        }
        if (celular?.length ?: 0 > 45) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.celular"], 45))
        }
        if (textoGrande?.length ?: 0 > 300) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.textoGrande"], 300))
        }
        if (snakeCase?.length ?: 0 > 200) {
            throw BadRequestException(lang.lengthCannotBeMoreThan(lang["Principal.snakeCase"], 200))
        }
    }

    constructor(rs: ResultSet, alias: String = "principal") : this() {
        idPrincipalPk = rs.getLong(alias, "idPrincipalPk")
        textoObrigatorio = rs.getString(alias, "textoObrigatorio")
        textoFacultativo = rs.getString(alias, "textoFacultativo")
        decimalObrigatorio = rs.getDouble(alias, "decimalObrigatorio")
        decimalFacultativo = rs.getDoubleOrNull(alias, "decimalFacultativo")
        inteiroObrigatorio = rs.getLong(alias, "inteiroObrigatorio")
        inteiroFacultativo = rs.getLongOrNull(alias, "inteiroFacultativo")
        booleanoObrigatorio = rs.getBoolean(alias, "booleanoObrigatorio")
        booleanoFacultativo = rs.getBooleanOrNull(alias, "booleanoFacultativo")
        dataObrigatoria = rs.getTimestamp(alias, "dataObrigatoria")
        dataFacultativa = rs.getTimestamp(alias, "dataFacultativa")
        datahoraObrigatoria = rs.getTimestamp(alias, "datahoraObrigatoria")
        datahoraFacultativa = rs.getTimestamp(alias, "datahoraFacultativa")
        ativo = rs.getBoolean(alias, "ativo")
        email = rs.getString(alias, "email")
        senha = rs.getString(alias, "senha")
        urlImagem = rs.getString(alias, "urlImagem")
        url = rs.getString(alias, "url")
        idGrupoDoPrincipalFk = rs.getLong(alias, "idGrupoDoPrincipalFk")
        idGrupoDoPrincipalFacultativoFk = rs.getLongOrNull(alias, "idGrupoDoPrincipalFacultativoFk")
        unico = rs.getString(alias, "unico")
        dataCriacao = rs.getTimestamp(alias, "dataCriacao")
        dataAlteracao = rs.getTimestamp(alias, "dataAlteracao")
        nome = rs.getString(alias, "nome")
        titulo = rs.getString(alias, "titulo")
        cpf = rs.getString(alias, "cpf")
        cnpj = rs.getString(alias, "cnpj")
        rg = rs.getString(alias, "rg")
        celular = rs.getString(alias, "celular")
        textoGrande = rs.getString(alias, "textoGrande")
        snakeCase = rs.getString(alias, "snake_case")
        preco = rs.getDoubleOrNull(alias, "preco")
    }

    fun updateSet() = mapOf(
            "textoObrigatorio" to textoObrigatorio,
            "textoFacultativo" to textoFacultativo,
            "decimalObrigatorio" to decimalObrigatorio,
            "decimalFacultativo" to decimalFacultativo,
            "inteiroObrigatorio" to inteiroObrigatorio,
            "inteiroFacultativo" to inteiroFacultativo,
            "booleanoObrigatorio" to booleanoObrigatorio,
            "booleanoFacultativo" to booleanoFacultativo,
            "dataObrigatoria" to dataObrigatoria,
            "dataFacultativa" to dataFacultativa,
            "datahoraObrigatoria" to datahoraObrigatoria,
            "datahoraFacultativa" to datahoraFacultativa,
            "ativo" to ativo,
            "email" to email,
            "senha" to Query("IF(? IS NOT NULL, SHA2(?, 256), senha)", senha, senha),
            "urlImagem" to urlImagem,
            "url" to url,
            "idGrupoDoPrincipalFk" to idGrupoDoPrincipalFk,
            "idGrupoDoPrincipalFacultativoFk" to idGrupoDoPrincipalFacultativoFk,
            "unico" to unico,
            "dataAlteracao" to dataAlteracao,
            "nome" to nome,
            "titulo" to titulo,
            "cpf" to cpf,
            "cnpj" to cnpj,
            "rg" to rg,
            "celular" to celular,
            "textoGrande" to textoGrande,
            "snake_case" to snakeCase,
            "preco" to preco
    )

    fun insertValues() = mapOf(
            "textoObrigatorio" to textoObrigatorio,
            "textoFacultativo" to textoFacultativo,
            "decimalObrigatorio" to decimalObrigatorio,
            "decimalFacultativo" to decimalFacultativo,
            "inteiroObrigatorio" to inteiroObrigatorio,
            "inteiroFacultativo" to inteiroFacultativo,
            "booleanoObrigatorio" to booleanoObrigatorio,
            "booleanoFacultativo" to booleanoFacultativo,
            "dataObrigatoria" to dataObrigatoria,
            "dataFacultativa" to dataFacultativa,
            "datahoraObrigatoria" to datahoraObrigatoria,
            "datahoraFacultativa" to datahoraFacultativa,
            "ativo" to ativo,
            "email" to email,
            "senha" to Query("SHA2(?, 256)", senha),
            "urlImagem" to urlImagem,
            "url" to url,
            "idGrupoDoPrincipalFk" to idGrupoDoPrincipalFk,
            "idGrupoDoPrincipalFacultativoFk" to idGrupoDoPrincipalFacultativoFk,
            "unico" to unico,
            "dataCriacao" to dataCriacao,
            "nome" to nome,
            "titulo" to titulo,
            "cpf" to cpf,
            "cnpj" to cnpj,
            "rg" to rg,
            "celular" to celular,
            "textoGrande" to textoGrande,
            "snake_case" to snakeCase,
            "preco" to preco
    )

    companion object {
        val orderMap = mapOf(
                "idPrincipalPk" to "principal.idPrincipalPk",
                "textoObrigatorio" to "principal.textoObrigatorio",
                "textoFacultativo" to "principal.textoFacultativo",
                "decimalObrigatorio" to "principal.decimalObrigatorio",
                "decimalFacultativo" to "principal.decimalFacultativo",
                "inteiroObrigatorio" to "principal.inteiroObrigatorio",
                "inteiroFacultativo" to "principal.inteiroFacultativo",
                "booleanoObrigatorio" to "principal.booleanoObrigatorio",
                "booleanoFacultativo" to "principal.booleanoFacultativo",
                "dataObrigatoria" to "principal.dataObrigatoria",
                "dataFacultativa" to "principal.dataFacultativa",
                "datahoraObrigatoria" to "principal.datahoraObrigatoria",
                "datahoraFacultativa" to "principal.datahoraFacultativa",
                "ativo" to "principal.ativo",
                "email" to "principal.email",
                "urlImagem" to "principal.urlImagem",
                "url" to "principal.url",
                "idGrupoDoPrincipalFk" to "principal.idGrupoDoPrincipalFk",
                "idGrupoDoPrincipalFacultativoFk" to "principal.idGrupoDoPrincipalFacultativoFk",
                "unico" to "principal.unico",
                "dataCriacao" to "principal.dataCriacao",
                "dataAlteracao" to "principal.dataAlteracao",
                "nome" to "principal.nome",
                "titulo" to "principal.titulo",
                "cpf" to "principal.cpf",
                "cnpj" to "principal.cnpj",
                "rg" to "principal.rg",
                "celular" to "principal.celular",
                "textoGrande" to "principal.textoGrande",
                "snakeCase" to "principal.snake_case",
                "preco" to "principal.preco"
        )
    }
}

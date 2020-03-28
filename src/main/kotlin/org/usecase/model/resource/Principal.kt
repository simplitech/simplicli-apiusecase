package org.usecase.model.resource

import org.usecase.exception.response.BadRequestException
import org.usecase.param.DefaultParam
import br.com.simpli.model.LanguageHolder
import br.com.simpli.sql.ResultBuilder
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

    constructor(rs: ResultBuilder) : this() {
        idPrincipalPk = rs.getLong("idPrincipalPk")
        textoObrigatorio = rs.getString("textoObrigatorio")
        textoFacultativo = rs.getString("textoFacultativo")
        decimalObrigatorio = rs.getDouble("decimalObrigatorio")
        decimalFacultativo = rs.getDoubleOrNull("decimalFacultativo")
        inteiroObrigatorio = rs.getLong("inteiroObrigatorio")
        inteiroFacultativo = rs.getLongOrNull("inteiroFacultativo")
        booleanoObrigatorio = rs.getBoolean("booleanoObrigatorio")
        booleanoFacultativo = rs.getBooleanOrNull("booleanoFacultativo")
        dataObrigatoria = rs.getTimestamp("dataObrigatoria")
        dataFacultativa = rs.getTimestamp("dataFacultativa")
        datahoraObrigatoria = rs.getTimestamp("datahoraObrigatoria")
        datahoraFacultativa = rs.getTimestamp("datahoraFacultativa")
        ativo = rs.getBoolean("ativo")
        email = rs.getString("email")
        senha = rs.getString("senha")
        urlImagem = rs.getString("urlImagem")
        url = rs.getString("url")
        idGrupoDoPrincipalFk = rs.getLong("idGrupoDoPrincipalFk")
        idGrupoDoPrincipalFacultativoFk = rs.getLongOrNull("idGrupoDoPrincipalFacultativoFk")
        unico = rs.getString("unico")
        dataCriacao = rs.getTimestamp("dataCriacao")
        dataAlteracao = rs.getTimestamp("dataAlteracao")
        nome = rs.getString("nome")
        titulo = rs.getString("titulo")
        cpf = rs.getString("cpf")
        cnpj = rs.getString("cnpj")
        rg = rs.getString("rg")
        celular = rs.getString("celular")
        textoGrande = rs.getString("textoGrande")
        snakeCase = rs.getString("snake_case")
        preco = rs.getDoubleOrNull("preco")
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
        fun selectFields(alias: String = "principal") = arrayOf(
                "$alias.idPrincipalPk",
                "$alias.textoObrigatorio",
                "$alias.textoFacultativo",
                "$alias.decimalObrigatorio",
                "$alias.decimalFacultativo",
                "$alias.inteiroObrigatorio",
                "$alias.inteiroFacultativo",
                "$alias.booleanoObrigatorio",
                "$alias.booleanoFacultativo",
                "$alias.dataObrigatoria",
                "$alias.dataFacultativa",
                "$alias.datahoraObrigatoria",
                "$alias.datahoraFacultativa",
                "$alias.ativo",
                "$alias.email",
                "$alias.urlImagem",
                "$alias.url",
                "$alias.idGrupoDoPrincipalFk",
                "$alias.idGrupoDoPrincipalFacultativoFk",
                "$alias.unico",
                "$alias.dataCriacao",
                "$alias.dataAlteracao",
                "$alias.nome",
                "$alias.titulo",
                "$alias.cpf",
                "$alias.cnpj",
                "$alias.rg",
                "$alias.celular",
                "$alias.textoGrande",
                "$alias.snake_case",
                "$alias.preco"
        )

        fun orderMap(alias: String = "principal") = mapOf(
                "idPrincipalPk" to "$alias.idPrincipalPk",
                "textoObrigatorio" to "$alias.textoObrigatorio",
                "textoFacultativo" to "$alias.textoFacultativo",
                "decimalObrigatorio" to "$alias.decimalObrigatorio",
                "decimalFacultativo" to "$alias.decimalFacultativo",
                "inteiroObrigatorio" to "$alias.inteiroObrigatorio",
                "inteiroFacultativo" to "$alias.inteiroFacultativo",
                "booleanoObrigatorio" to "$alias.booleanoObrigatorio",
                "booleanoFacultativo" to "$alias.booleanoFacultativo",
                "dataObrigatoria" to "$alias.dataObrigatoria",
                "dataFacultativa" to "$alias.dataFacultativa",
                "datahoraObrigatoria" to "$alias.datahoraObrigatoria",
                "datahoraFacultativa" to "$alias.datahoraFacultativa",
                "ativo" to "$alias.ativo",
                "email" to "$alias.email",
                "urlImagem" to "$alias.urlImagem",
                "url" to "$alias.url",
                "idGrupoDoPrincipalFk" to "$alias.idGrupoDoPrincipalFk",
                "idGrupoDoPrincipalFacultativoFk" to "$alias.idGrupoDoPrincipalFacultativoFk",
                "unico" to "$alias.unico",
                "dataCriacao" to "$alias.dataCriacao",
                "dataAlteracao" to "$alias.dataAlteracao",
                "nome" to "$alias.nome",
                "titulo" to "$alias.titulo",
                "cpf" to "$alias.cpf",
                "cnpj" to "$alias.cnpj",
                "rg" to "$alias.rg",
                "celular" to "$alias.celular",
                "textoGrande" to "$alias.textoGrande",
                "snakeCase" to "$alias.snake_case",
                "preco" to "$alias.preco"
        )
    }
}

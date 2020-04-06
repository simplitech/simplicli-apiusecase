package org.usecase.rm

import br.com.simpli.sql.Query
import br.com.simpli.sql.ResultBuilder
import org.usecase.model.resource.Principal

/**
 * Relational Mapping of Principal from table principal
 * @author Simpli CLI generator
 */
object PrincipalRM {
    fun build(rb: ResultBuilder) = Principal().apply {
        idPrincipalPk = rb.getLong("idPrincipalPk")
        textoObrigatorio = rb.getString("textoObrigatorio")
        textoFacultativo = rb.getString("textoFacultativo")
        decimalObrigatorio = rb.getDouble("decimalObrigatorio")
        decimalFacultativo = rb.getDoubleOrNull("decimalFacultativo")
        inteiroObrigatorio = rb.getLong("inteiroObrigatorio")
        inteiroFacultativo = rb.getLongOrNull("inteiroFacultativo")
        booleanoObrigatorio = rb.getBoolean("booleanoObrigatorio")
        booleanoFacultativo = rb.getBooleanOrNull("booleanoFacultativo")
        dataObrigatoria = rb.getTimestamp("dataObrigatoria")
        dataFacultativa = rb.getTimestamp("dataFacultativa")
        datahoraObrigatoria = rb.getTimestamp("datahoraObrigatoria")
        datahoraFacultativa = rb.getTimestamp("datahoraFacultativa")
        ativo = rb.getBoolean("ativo")
        email = rb.getString("email")
        senha = rb.getString("senha")
        urlImagem = rb.getString("urlImagem")
        url = rb.getString("url")
        idGrupoDoPrincipalFk = rb.getLong("idGrupoDoPrincipalFk")
        idGrupoDoPrincipalFacultativoFk = rb.getLongOrNull("idGrupoDoPrincipalFacultativoFk")
        unico = rb.getString("unico")
        dataCriacao = rb.getTimestamp("dataCriacao")
        dataAlteracao = rb.getTimestamp("dataAlteracao")
        nome = rb.getString("nome")
        titulo = rb.getString("titulo")
        cpf = rb.getString("cpf")
        cnpj = rb.getString("cnpj")
        rg = rb.getString("rg")
        celular = rb.getString("celular")
        textoGrande = rb.getString("textoGrande")
        snakeCase = rb.getString("snake_case")
        preco = rb.getDoubleOrNull("preco")
    }

    fun updateSet(principal: Principal) = mapOf(
            "textoObrigatorio" to principal.textoObrigatorio,
            "textoFacultativo" to principal.textoFacultativo,
            "decimalObrigatorio" to principal.decimalObrigatorio,
            "decimalFacultativo" to principal.decimalFacultativo,
            "inteiroObrigatorio" to principal.inteiroObrigatorio,
            "inteiroFacultativo" to principal.inteiroFacultativo,
            "booleanoObrigatorio" to principal.booleanoObrigatorio,
            "booleanoFacultativo" to principal.booleanoFacultativo,
            "dataObrigatoria" to principal.dataObrigatoria,
            "dataFacultativa" to principal.dataFacultativa,
            "datahoraObrigatoria" to principal.datahoraObrigatoria,
            "datahoraFacultativa" to principal.datahoraFacultativa,
            "ativo" to principal.ativo,
            "email" to principal.email,
            "senha" to Query("IF(? IS NOT NULL, SHA2(?, 256), senha)", principal.senha, principal.senha),
            "urlImagem" to principal.urlImagem,
            "url" to principal.url,
            "idGrupoDoPrincipalFk" to principal.idGrupoDoPrincipalFk,
            "idGrupoDoPrincipalFacultativoFk" to principal.idGrupoDoPrincipalFacultativoFk,
            "unico" to principal.unico,
            "dataAlteracao" to principal.dataAlteracao,
            "nome" to principal.nome,
            "titulo" to principal.titulo,
            "cpf" to principal.cpf,
            "cnpj" to principal.cnpj,
            "rg" to principal.rg,
            "celular" to principal.celular,
            "textoGrande" to principal.textoGrande,
            "snake_case" to principal.snakeCase,
            "preco" to principal.preco
    )

    fun insertValues(principal: Principal) = mapOf(
            "textoObrigatorio" to principal.textoObrigatorio,
            "textoFacultativo" to principal.textoFacultativo,
            "decimalObrigatorio" to principal.decimalObrigatorio,
            "decimalFacultativo" to principal.decimalFacultativo,
            "inteiroObrigatorio" to principal.inteiroObrigatorio,
            "inteiroFacultativo" to principal.inteiroFacultativo,
            "booleanoObrigatorio" to principal.booleanoObrigatorio,
            "booleanoFacultativo" to principal.booleanoFacultativo,
            "dataObrigatoria" to principal.dataObrigatoria,
            "dataFacultativa" to principal.dataFacultativa,
            "datahoraObrigatoria" to principal.datahoraObrigatoria,
            "datahoraFacultativa" to principal.datahoraFacultativa,
            "ativo" to principal.ativo,
            "email" to principal.email,
            "senha" to Query("SHA2(?, 256)", principal.senha),
            "urlImagem" to principal.urlImagem,
            "url" to principal.url,
            "idGrupoDoPrincipalFk" to principal.idGrupoDoPrincipalFk,
            "idGrupoDoPrincipalFacultativoFk" to principal.idGrupoDoPrincipalFacultativoFk,
            "unico" to principal.unico,
            "dataCriacao" to principal.dataCriacao,
            "nome" to principal.nome,
            "titulo" to principal.titulo,
            "cpf" to principal.cpf,
            "cnpj" to principal.cnpj,
            "rg" to principal.rg,
            "celular" to principal.celular,
            "textoGrande" to principal.textoGrande,
            "snake_case" to principal.snakeCase,
            "preco" to principal.preco
    )

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

    fun fieldsToSearch(alias: String = "principal") = arrayOf(
            "$alias.textoObrigatorio",
            "$alias.textoFacultativo",
            "$alias.email",
            "$alias.urlImagem",
            "$alias.url",
            "$alias.nome",
            "$alias.titulo",
            "$alias.cpf",
            "$alias.cnpj",
            "$alias.rg",
            "$alias.celular",
            "$alias.textoGrande",
            "$alias.snake_case"
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

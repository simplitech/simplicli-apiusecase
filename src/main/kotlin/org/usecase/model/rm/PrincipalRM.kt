package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.context.PermissionGroup
import org.usecase.model.resource.Principal
import org.usecase.model.resource.Permission
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table principal
 * @author Simpli CLI generator
 */
class PrincipalRM(val permission: PermissionGroup, override var alias: String? = null) : RelationalMapper<Principal>() {
    override val table = "principal"

    val idPrincipalPk = col("idPrincipalPk",
            { idPrincipalPk },
            { idPrincipalPk = it.value() })

    val textoObrigatorio = col("textoObrigatorio",
            { textoObrigatorio },
            { textoObrigatorio = it.value() })

    val textoFacultativo = col("textoFacultativo",
            { textoFacultativo },
            { textoFacultativo = it.value() })

    val decimalObrigatorio = col("decimalObrigatorio",
            { decimalObrigatorio },
            { decimalObrigatorio = it.value() })

    val decimalFacultativo = col("decimalFacultativo",
            { decimalFacultativo },
            { decimalFacultativo = it.value() })

    val inteiroObrigatorio = col("inteiroObrigatorio",
            { inteiroObrigatorio },
            { inteiroObrigatorio = it.value() })

    val inteiroFacultativo = col("inteiroFacultativo",
            { inteiroFacultativo },
            { inteiroFacultativo = it.value() })

    val booleanoObrigatorio = col("booleanoObrigatorio",
            { booleanoObrigatorio },
            { booleanoObrigatorio = it.value() })

    val booleanoFacultativo = col("booleanoFacultativo",
            { booleanoFacultativo },
            { booleanoFacultativo = it.value() })

    val dataObrigatoria = col("dataObrigatoria",
            { dataObrigatoria },
            { dataObrigatoria = it.value() })

    val dataFacultativa = col("dataFacultativa",
            { dataFacultativa },
            { dataFacultativa = it.value() })

    val datahoraObrigatoria = col("datahoraObrigatoria",
            { datahoraObrigatoria },
            { datahoraObrigatoria = it.value() })

    val datahoraFacultativa = col("datahoraFacultativa",
            { datahoraFacultativa },
            { datahoraFacultativa = it.value() })

    val ativo = col("ativo",
            { ativo },
            { ativo = it.value() })

    val email = col("email",
            { email },
            { email = it.value() })

    val senha = col("senha",
            { senha },
            { senha = it.value() })

    val urlImagem = col("urlImagem",
            { urlImagem },
            { urlImagem = it.value() })

    val url = col("url",
            { url },
            { url = it.value() })

    val idGrupoDoPrincipalFk = col("idGrupoDoPrincipalFk",
            { idGrupoDoPrincipalFk },
            { idGrupoDoPrincipalFk = it.value() })

    val idGrupoDoPrincipalFacultativoFk = col("idGrupoDoPrincipalFacultativoFk",
            { idGrupoDoPrincipalFacultativoFk },
            { idGrupoDoPrincipalFacultativoFk = it.value() })

    val unico = col("unico",
            { unico },
            { unico = it.value() })

    val dataCriacao = col("dataCriacao",
            { dataCriacao },
            { dataCriacao = it.value() })

    val dataAlteracao = col("dataAlteracao",
            { dataAlteracao },
            { dataAlteracao = it.value() })

    val nome = col("nome",
            { nome },
            { nome = it.value() })

    val titulo = col("titulo",
            { titulo },
            { titulo = it.value() })

    val cpf = col("cpf",
            { cpf },
            { cpf = it.value() })

    val cnpj = col("cnpj",
            { cnpj },
            { cnpj = it.value() })

    val rg = col("rg",
            { rg },
            { rg = it.value() })

    val celular = col("celular",
            { celular },
            { celular = it.value() })

    val textoGrande = col("textoGrande",
            { textoGrande },
            { textoGrande = it.value() })

    val snakeCase = col("snake_case",
            { snakeCase },
            { snakeCase = it.value() })

    val preco = col("preco",
            { preco },
            { preco = it.value() })


    fun build(rs: ResultSet) = Principal().apply {
        selectFields.forEach { col ->
            col.build(this, rs)
        }
    }

    val selectFields: Array<VirtualColumn<Principal>>
        get() = permission.buildArray {
            Permission.apply {
                add(idPrincipalPk, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_ID_PRINCIPAL_PK)
                add(textoObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TEXTO_OBRIGATORIO)
                add(textoFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TEXTO_FACULTATIVO)
                add(decimalObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DECIMAL_OBRIGATORIO)
                add(decimalFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DECIMAL_FACULTATIVO)
                add(inteiroObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_INTEIRO_OBRIGATORIO)
                add(inteiroFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_INTEIRO_FACULTATIVO)
                add(booleanoObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_BOOLEANO_OBRIGATORIO)
                add(booleanoFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_BOOLEANO_FACULTATIVO)
                add(dataObrigatoria, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATA_OBRIGATORIA)
                add(dataFacultativa, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATA_FACULTATIVA)
                add(datahoraObrigatoria, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATAHORA_OBRIGATORIA)
                add(datahoraFacultativa, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATAHORA_FACULTATIVA)
                add(ativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_ATIVO)
                add(email, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_EMAIL)
                add(urlImagem, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_URL_IMAGEM)
                add(url, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_URL)
                add(idGrupoDoPrincipalFk, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_ID_GRUPO_DO_PRINCIPAL_FK)
                add(idGrupoDoPrincipalFacultativoFk, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_ID_GRUPO_DO_PRINCIPAL_FACULTATIVO_FK)
                add(unico, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_UNICO)
                add(dataCriacao, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATA_CRIACAO)
                add(dataAlteracao, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATA_ALTERACAO)
                add(nome, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_NOME)
                add(titulo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TITULO)
                add(cpf, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_CPF)
                add(cnpj, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_CNPJ)
                add(rg, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_RG)
                add(celular, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_CELULAR)
                add(textoGrande, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TEXTO_GRANDE)
                add(snakeCase, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_SNAKE_CASE)
                add(preco, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_PRECO)
            }
        }

    val fieldsToSearch: Array<VirtualColumn<Principal>>
        get() = permission.buildArray {
            Permission.apply {
                add(idPrincipalPk, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_ID_PRINCIPAL_PK)
                add(textoObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TEXTO_OBRIGATORIO)
                add(textoFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TEXTO_FACULTATIVO)
                add(email, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_EMAIL)
                add(unico, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_UNICO)
                add(nome, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_NOME)
                add(titulo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TITULO)
                add(cpf, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_CPF)
                add(cnpj, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_CNPJ)
                add(rg, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_RG)
                add(celular, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_CELULAR)
                add(textoGrande, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TEXTO_GRANDE)
                add(snakeCase, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_SNAKE_CASE)
            }
        }

    val orderMap: Map<String, VirtualColumn<Principal>>
        get() = permission.buildMap {
            Permission.apply {
                add("grupoDoPrincipal" to idGrupoDoPrincipalFk, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_ID_GRUPO_DO_PRINCIPAL_FK)
                add("grupoDoPrincipalFacultativo" to idGrupoDoPrincipalFacultativoFk, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_ID_GRUPO_DO_PRINCIPAL_FACULTATIVO_FK)
                add("idPrincipalPk" to idPrincipalPk, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_ID_PRINCIPAL_PK)
                add("textoObrigatorio" to textoObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TEXTO_OBRIGATORIO)
                add("textoFacultativo" to textoFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TEXTO_FACULTATIVO)
                add("decimalObrigatorio" to decimalObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DECIMAL_OBRIGATORIO)
                add("decimalFacultativo" to decimalFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DECIMAL_FACULTATIVO)
                add("inteiroObrigatorio" to inteiroObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_INTEIRO_OBRIGATORIO)
                add("inteiroFacultativo" to inteiroFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_INTEIRO_FACULTATIVO)
                add("booleanoObrigatorio" to booleanoObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_BOOLEANO_OBRIGATORIO)
                add("booleanoFacultativo" to booleanoFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_BOOLEANO_FACULTATIVO)
                add("dataObrigatoria" to dataObrigatoria, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATA_OBRIGATORIA)
                add("dataFacultativa" to dataFacultativa, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATA_FACULTATIVA)
                add("datahoraObrigatoria" to datahoraObrigatoria, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATAHORA_OBRIGATORIA)
                add("datahoraFacultativa" to datahoraFacultativa, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATAHORA_FACULTATIVA)
                add("ativo" to ativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_ATIVO)
                add("email" to email, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_EMAIL)
                add("urlImagem" to urlImagem, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_URL_IMAGEM)
                add("url" to url, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_URL)
                add("unico" to unico, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_UNICO)
                add("dataCriacao" to dataCriacao, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATA_CRIACAO)
                add("dataAlteracao" to dataAlteracao, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_DATA_ALTERACAO)
                add("nome" to nome, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_NOME)
                add("titulo" to titulo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TITULO)
                add("cpf" to cpf, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_CPF)
                add("cnpj" to cnpj, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_CNPJ)
                add("rg" to rg, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_RG)
                add("celular" to celular, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_CELULAR)
                add("textoGrande" to textoGrande, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_TEXTO_GRANDE)
                add("snakeCase" to snakeCase, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_SNAKE_CASE)
                add("preco" to preco, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_READ_ALL, PRINCIPAL_READ_PRECO)
            }
        }

    fun updateSet(principal: Principal) = colsToMap(principal, *permission.buildArray {
        Permission.apply {
            add(textoObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_TEXTO_OBRIGATORIO)
            add(textoFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_TEXTO_FACULTATIVO)
            add(decimalObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_DECIMAL_OBRIGATORIO)
            add(decimalFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_DECIMAL_FACULTATIVO)
            add(inteiroObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_INTEIRO_OBRIGATORIO)
            add(inteiroFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_INTEIRO_FACULTATIVO)
            add(booleanoObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_BOOLEANO_OBRIGATORIO)
            add(booleanoFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_BOOLEANO_FACULTATIVO)
            add(dataObrigatoria, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_DATA_OBRIGATORIA)
            add(dataFacultativa, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_DATA_FACULTATIVA)
            add(datahoraObrigatoria, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_DATAHORA_OBRIGATORIA)
            add(datahoraFacultativa, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_DATAHORA_FACULTATIVA)
            add(email, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_EMAIL)
            add(urlImagem, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_URL_IMAGEM)
            add(url, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_URL)
            add(idGrupoDoPrincipalFk, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_ID_GRUPO_DO_PRINCIPAL_FK)
            add(idGrupoDoPrincipalFacultativoFk, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_ID_GRUPO_DO_PRINCIPAL_FACULTATIVO_FK)
            add(unico, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_UNICO)
            add(dataAlteracao, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_DATA_ALTERACAO)
            add(nome, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_NOME)
            add(titulo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_TITULO)
            add(cpf, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_CPF)
            add(cnpj, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_CNPJ)
            add(rg, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_RG)
            add(celular, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_CELULAR)
            add(textoGrande, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_TEXTO_GRANDE)
            add(snakeCase, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_SNAKE_CASE)
            add(preco, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_PRECO)

            if (principal.senha.isNullOrBlank()) {
                add(senha, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_UPDATE_ALL, PRINCIPAL_UPDATE_SENHA)
            }
        }
    })

    fun insertValues(principal: Principal) = colsToMap(principal, *permission.buildArray {
        Permission.apply {
            add(textoObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_TEXTO_OBRIGATORIO)
            add(textoFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_TEXTO_FACULTATIVO)
            add(decimalObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_DECIMAL_OBRIGATORIO)
            add(decimalFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_DECIMAL_FACULTATIVO)
            add(inteiroObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_INTEIRO_OBRIGATORIO)
            add(inteiroFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_INTEIRO_FACULTATIVO)
            add(booleanoObrigatorio, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_BOOLEANO_OBRIGATORIO)
            add(booleanoFacultativo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_BOOLEANO_FACULTATIVO)
            add(dataObrigatoria, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_DATA_OBRIGATORIA)
            add(dataFacultativa, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_DATA_FACULTATIVA)
            add(datahoraObrigatoria, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_DATAHORA_OBRIGATORIA)
            add(datahoraFacultativa, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_DATAHORA_FACULTATIVA)
            add(email, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_EMAIL)
            add(urlImagem, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_URL_IMAGEM)
            add(url, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_URL)
            add(idGrupoDoPrincipalFk, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_ID_GRUPO_DO_PRINCIPAL_FK)
            add(idGrupoDoPrincipalFacultativoFk, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_ID_GRUPO_DO_PRINCIPAL_FACULTATIVO_FK)
            add(unico, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_UNICO)
            add(dataCriacao, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_DATA_CRIACAO)
            add(nome, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_NOME)
            add(titulo, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_TITULO)
            add(cpf, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_CPF)
            add(cnpj, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_CNPJ)
            add(rg, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_RG)
            add(celular, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_CELULAR)
            add(textoGrande, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_TEXTO_GRANDE)
            add(snakeCase, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_SNAKE_CASE)
            add(preco, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_PRECO)
            add(senha, FULL_CONTROL, PRINCIPAL_FULL_CONTROL, PRINCIPAL_INSERT_ALL, PRINCIPAL_INSERT_SENHA)
        }
    })
}

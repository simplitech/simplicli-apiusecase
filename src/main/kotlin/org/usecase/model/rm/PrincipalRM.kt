package org.usecase.model.rm

import br.com.simpli.sql.RelationalMapper
import br.com.simpli.sql.VirtualColumn
import org.usecase.model.resource.Principal
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.PRINCIPAL_UPDATE_ALL
import java.sql.ResultSet

/**
 * Relational Mapping of Principal from table principal
 * @author Simpli CLI generator
 */
class PrincipalRM(val permission: Permission, override var alias: String? = null) : RelationalMapper<Principal>() {
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
            add(PRINCIPAL_READ_ALL, idPrincipalPk)
            add(PRINCIPAL_READ_ALL, textoObrigatorio)
            add(PRINCIPAL_READ_ALL, textoFacultativo)
            add(PRINCIPAL_READ_ALL, decimalObrigatorio)
            add(PRINCIPAL_READ_ALL, decimalFacultativo)
            add(PRINCIPAL_READ_ALL, inteiroObrigatorio)
            add(PRINCIPAL_READ_ALL, inteiroFacultativo)
            add(PRINCIPAL_READ_ALL, booleanoObrigatorio)
            add(PRINCIPAL_READ_ALL, booleanoFacultativo)
            add(PRINCIPAL_READ_ALL, dataObrigatoria)
            add(PRINCIPAL_READ_ALL, dataFacultativa)
            add(PRINCIPAL_READ_ALL, datahoraObrigatoria)
            add(PRINCIPAL_READ_ALL, datahoraFacultativa)
            add(PRINCIPAL_READ_ALL, ativo)
            add(PRINCIPAL_READ_ALL, email)
            add(PRINCIPAL_READ_ALL, urlImagem)
            add(PRINCIPAL_READ_ALL, url)
            add(PRINCIPAL_READ_ALL, idGrupoDoPrincipalFk)
            add(PRINCIPAL_READ_ALL, idGrupoDoPrincipalFacultativoFk)
            add(PRINCIPAL_READ_ALL, unico)
            add(PRINCIPAL_READ_ALL, dataCriacao)
            add(PRINCIPAL_READ_ALL, dataAlteracao)
            add(PRINCIPAL_READ_ALL, nome)
            add(PRINCIPAL_READ_ALL, titulo)
            add(PRINCIPAL_READ_ALL, cpf)
            add(PRINCIPAL_READ_ALL, cnpj)
            add(PRINCIPAL_READ_ALL, rg)
            add(PRINCIPAL_READ_ALL, celular)
            add(PRINCIPAL_READ_ALL, textoGrande)
            add(PRINCIPAL_READ_ALL, snakeCase)
            add(PRINCIPAL_READ_ALL, preco)
        }

    val fieldsToSearch: Array<VirtualColumn<Principal>>
        get() = permission.buildArray {
            add(PRINCIPAL_READ_ALL, idPrincipalPk)
            add(PRINCIPAL_READ_ALL, textoObrigatorio)
            add(PRINCIPAL_READ_ALL, textoFacultativo)
            add(PRINCIPAL_READ_ALL, email)
            add(PRINCIPAL_READ_ALL, unico)
            add(PRINCIPAL_READ_ALL, nome)
            add(PRINCIPAL_READ_ALL, titulo)
            add(PRINCIPAL_READ_ALL, cpf)
            add(PRINCIPAL_READ_ALL, cnpj)
            add(PRINCIPAL_READ_ALL, rg)
            add(PRINCIPAL_READ_ALL, celular)
            add(PRINCIPAL_READ_ALL, textoGrande)
            add(PRINCIPAL_READ_ALL, snakeCase)
        }

    val orderMap: Map<String, VirtualColumn<Principal>>
        get() = permission.buildMap {
            add(PRINCIPAL_READ_ALL, "grupoDoPrincipal" to idGrupoDoPrincipalFk)
            add(PRINCIPAL_READ_ALL, "grupoDoPrincipalFacultativo" to idGrupoDoPrincipalFacultativoFk)
            add(PRINCIPAL_READ_ALL, "idPrincipalPk" to idPrincipalPk)
            add(PRINCIPAL_READ_ALL, "textoObrigatorio" to textoObrigatorio)
            add(PRINCIPAL_READ_ALL, "textoFacultativo" to textoFacultativo)
            add(PRINCIPAL_READ_ALL, "decimalObrigatorio" to decimalObrigatorio)
            add(PRINCIPAL_READ_ALL, "decimalFacultativo" to decimalFacultativo)
            add(PRINCIPAL_READ_ALL, "inteiroObrigatorio" to inteiroObrigatorio)
            add(PRINCIPAL_READ_ALL, "inteiroFacultativo" to inteiroFacultativo)
            add(PRINCIPAL_READ_ALL, "booleanoObrigatorio" to booleanoObrigatorio)
            add(PRINCIPAL_READ_ALL, "booleanoFacultativo" to booleanoFacultativo)
            add(PRINCIPAL_READ_ALL, "dataObrigatoria" to dataObrigatoria)
            add(PRINCIPAL_READ_ALL, "dataFacultativa" to dataFacultativa)
            add(PRINCIPAL_READ_ALL, "datahoraObrigatoria" to datahoraObrigatoria)
            add(PRINCIPAL_READ_ALL, "datahoraFacultativa" to datahoraFacultativa)
            add(PRINCIPAL_READ_ALL, "ativo" to ativo)
            add(PRINCIPAL_READ_ALL, "email" to email)
            add(PRINCIPAL_READ_ALL, "urlImagem" to urlImagem)
            add(PRINCIPAL_READ_ALL, "url" to url)
            add(PRINCIPAL_READ_ALL, "unico" to unico)
            add(PRINCIPAL_READ_ALL, "dataCriacao" to dataCriacao)
            add(PRINCIPAL_READ_ALL, "dataAlteracao" to dataAlteracao)
            add(PRINCIPAL_READ_ALL, "nome" to nome)
            add(PRINCIPAL_READ_ALL, "titulo" to titulo)
            add(PRINCIPAL_READ_ALL, "cpf" to cpf)
            add(PRINCIPAL_READ_ALL, "cnpj" to cnpj)
            add(PRINCIPAL_READ_ALL, "rg" to rg)
            add(PRINCIPAL_READ_ALL, "celular" to celular)
            add(PRINCIPAL_READ_ALL, "textoGrande" to textoGrande)
            add(PRINCIPAL_READ_ALL, "snakeCase" to snakeCase)
            add(PRINCIPAL_READ_ALL, "preco" to preco)
        }

    fun updateSet(principal: Principal) = colsToMap(principal, *permission.buildArray {
        add(PRINCIPAL_UPDATE_ALL, textoObrigatorio)
        add(PRINCIPAL_UPDATE_ALL, textoFacultativo)
        add(PRINCIPAL_UPDATE_ALL, decimalObrigatorio)
        add(PRINCIPAL_UPDATE_ALL, decimalFacultativo)
        add(PRINCIPAL_UPDATE_ALL, inteiroObrigatorio)
        add(PRINCIPAL_UPDATE_ALL, inteiroFacultativo)
        add(PRINCIPAL_UPDATE_ALL, booleanoObrigatorio)
        add(PRINCIPAL_UPDATE_ALL, booleanoFacultativo)
        add(PRINCIPAL_UPDATE_ALL, dataObrigatoria)
        add(PRINCIPAL_UPDATE_ALL, dataFacultativa)
        add(PRINCIPAL_UPDATE_ALL, datahoraObrigatoria)
        add(PRINCIPAL_UPDATE_ALL, datahoraFacultativa)
        add(PRINCIPAL_UPDATE_ALL, ativo)
        add(PRINCIPAL_UPDATE_ALL, email)
        add(PRINCIPAL_UPDATE_ALL, senha)
        add(PRINCIPAL_UPDATE_ALL, urlImagem)
        add(PRINCIPAL_UPDATE_ALL, url)
        add(PRINCIPAL_UPDATE_ALL, idGrupoDoPrincipalFk)
        add(PRINCIPAL_UPDATE_ALL, idGrupoDoPrincipalFacultativoFk)
        add(PRINCIPAL_UPDATE_ALL, unico)
        add(PRINCIPAL_UPDATE_ALL, dataAlteracao)
        add(PRINCIPAL_UPDATE_ALL, nome)
        add(PRINCIPAL_UPDATE_ALL, titulo)
        add(PRINCIPAL_UPDATE_ALL, cpf)
        add(PRINCIPAL_UPDATE_ALL, cnpj)
        add(PRINCIPAL_UPDATE_ALL, rg)
        add(PRINCIPAL_UPDATE_ALL, celular)
        add(PRINCIPAL_UPDATE_ALL, textoGrande)
        add(PRINCIPAL_UPDATE_ALL, snakeCase)
        add(PRINCIPAL_UPDATE_ALL, preco)
    })

    fun insertValues(principal: Principal) = colsToMap(principal, *permission.buildArray {
        add(PRINCIPAL_INSERT_ALL, textoObrigatorio)
        add(PRINCIPAL_INSERT_ALL, textoFacultativo)
        add(PRINCIPAL_INSERT_ALL, decimalObrigatorio)
        add(PRINCIPAL_INSERT_ALL, decimalFacultativo)
        add(PRINCIPAL_INSERT_ALL, inteiroObrigatorio)
        add(PRINCIPAL_INSERT_ALL, inteiroFacultativo)
        add(PRINCIPAL_INSERT_ALL, booleanoObrigatorio)
        add(PRINCIPAL_INSERT_ALL, booleanoFacultativo)
        add(PRINCIPAL_INSERT_ALL, dataObrigatoria)
        add(PRINCIPAL_INSERT_ALL, dataFacultativa)
        add(PRINCIPAL_INSERT_ALL, datahoraObrigatoria)
        add(PRINCIPAL_INSERT_ALL, datahoraFacultativa)
        add(PRINCIPAL_INSERT_ALL, ativo)
        add(PRINCIPAL_INSERT_ALL, email)
        add(PRINCIPAL_INSERT_ALL, senha)
        add(PRINCIPAL_INSERT_ALL, urlImagem)
        add(PRINCIPAL_INSERT_ALL, url)
        add(PRINCIPAL_INSERT_ALL, idGrupoDoPrincipalFk)
        add(PRINCIPAL_INSERT_ALL, idGrupoDoPrincipalFacultativoFk)
        add(PRINCIPAL_INSERT_ALL, unico)
        add(PRINCIPAL_INSERT_ALL, dataCriacao)
        add(PRINCIPAL_INSERT_ALL, nome)
        add(PRINCIPAL_INSERT_ALL, titulo)
        add(PRINCIPAL_INSERT_ALL, cpf)
        add(PRINCIPAL_INSERT_ALL, cnpj)
        add(PRINCIPAL_INSERT_ALL, rg)
        add(PRINCIPAL_INSERT_ALL, celular)
        add(PRINCIPAL_INSERT_ALL, textoGrande)
        add(PRINCIPAL_INSERT_ALL, snakeCase)
        add(PRINCIPAL_INSERT_ALL, preco)
    })
}

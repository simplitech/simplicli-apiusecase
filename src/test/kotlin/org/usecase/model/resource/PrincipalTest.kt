package org.usecase.model.resource

import org.usecase.AppTest
import org.usecase.exception.response.BadRequestException
import org.usecase.locale.EnUs
import java.util.Date
import org.apache.commons.lang3.RandomStringUtils
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * Tests Principal
 * @author Simpli CLI generator
 */
class PrincipalTest: AppTest() {
    private val lang = EnUs()
    private val model = Principal()

    init {
        model.idPrincipalPk = 1
        model.textoObrigatorio = "1"
        model.decimalObrigatorio = 1.0
        model.inteiroObrigatorio = 1
        model.booleanoObrigatorio = true
        model.dataObrigatoria = Date()
        model.datahoraObrigatoria = Date()
        model.ativo = true
        model.idGrupoDoPrincipalFk = 1
        model.unico = "1"
        model.dataCriacao = Date()
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTextoObrigatorioNullFail() {
        model.textoObrigatorio = ""

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTextoObrigatorioLengthFail() {
        model.textoObrigatorio = RandomStringUtils.randomAlphabetic(161)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTextoFacultativoLengthFail() {
        model.textoFacultativo = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateInteiroObrigatorioNullFail() {
        model.inteiroObrigatorio = 0L

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateDataObrigatoriaNullFail() {
        model.dataObrigatoria = null

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateDatahoraObrigatoriaNullFail() {
        model.datahoraObrigatoria = null

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateEmailLengthFail() {
        model.email = RandomStringUtils.randomAlphabetic(201)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateEmailInvalidEmailFail() {
        model.email = "notAnEmail"

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateSenhaLengthFail() {
        model.senha = RandomStringUtils.randomAlphabetic(201)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateUrlImagemLengthFail() {
        model.urlImagem = RandomStringUtils.randomAlphabetic(201)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateUrlLengthFail() {
        model.url = RandomStringUtils.randomAlphabetic(201)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateIdGrupoDoPrincipalFkNullFail() {
        model.idGrupoDoPrincipalFk = 0L

        model.validate(lang)
    }

    @Test
    fun testSetGrupoDoPrincipalFacultativoNull() {
        model.grupoDoPrincipalFacultativo = GrupoDoPrincipal()
        model.idGrupoDoPrincipalFacultativoFk = null
        assertNull(model.grupoDoPrincipalFacultativo)
        model.idGrupoDoPrincipalFacultativoFk = 1L
        assertNotNull(model.grupoDoPrincipalFacultativo)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateUnicoNullFail() {
        model.unico = ""

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateUnicoLengthFail() {
        model.unico = RandomStringUtils.randomAlphabetic(41)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateDataCriacaoNullFail() {
        model.dataCriacao = null

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateNomeLengthFail() {
        model.nome = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTituloLengthFail() {
        model.titulo = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateCpfLengthFail() {
        model.cpf = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateCnpjLengthFail() {
        model.cnpj = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateRgLengthFail() {
        model.rg = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateCelularLengthFail() {
        model.celular = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTextoGrandeLengthFail() {
        model.textoGrande = RandomStringUtils.randomAlphabetic(301)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateSnakeCaseLengthFail() {
        model.snakeCase = RandomStringUtils.randomAlphabetic(201)

        model.validate(lang)
    }

    @Test
    fun testValidateSuccess() {
        model.validate(lang)
    }
}

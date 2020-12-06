package org.usecase.user.process

import org.apache.commons.lang3.RandomStringUtils
import org.usecase.user.ProcessTest
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.model.resource.Principal
import org.usecase.model.param.PrincipalListParam
import org.usecase.model.resource.Tag
import java.util.Date
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNotSame
import kotlin.test.assertNull
import org.junit.Test
import org.usecase.model.resource.GrupoDoPrincipal
import org.usecase.user.context.Permission

/**
 * Tests Principal business logic
 * @author Simpli CLI generator
 */
class PrincipalProcessTest : ProcessTest() {
    private val id = 1L
    private val model = Principal()

    private val listFilter = PrincipalListParam()

    private val subject = PrincipalProcess(context)

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

        val tag = Tag()
        tag.id = 1L
        model.tagPrincipal = mutableListOf(tag)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTextoObrigatorioNullFail() {
        model.textoObrigatorio = ""

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTextoObrigatorioLengthFail() {
        model.textoObrigatorio = RandomStringUtils.randomAlphabetic(161)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTextoFacultativoLengthFail() {
        model.textoFacultativo = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateInteiroObrigatorioNullFail() {
        model.inteiroObrigatorio = 0L

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateDataObrigatoriaNullFail() {
        model.dataObrigatoria = null

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateDatahoraObrigatoriaNullFail() {
        model.datahoraObrigatoria = null

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateEmailLengthFail() {
        model.email = RandomStringUtils.randomAlphabetic(201)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateEmailInvalidEmailFail() {
        model.email = "notAnEmail"

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateSenhaLengthFail() {
        model.senha = RandomStringUtils.randomAlphabetic(201)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateUrlImagemLengthFail() {
        model.urlImagem = RandomStringUtils.randomAlphabetic(201)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateUrlLengthFail() {
        model.url = RandomStringUtils.randomAlphabetic(201)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateIdGrupoDoPrincipalFkNullFail() {
        model.idGrupoDoPrincipalFk = 0L

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
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

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateUnicoLengthFail() {
        model.unico = RandomStringUtils.randomAlphabetic(41)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateDataCriacaoNullFail() {
        model.dataCriacao = null

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateNomeLengthFail() {
        model.nome = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTituloLengthFail() {
        model.titulo = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateCpfLengthFail() {
        model.cpf = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateCnpjLengthFail() {
        model.cnpj = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateRgLengthFail() {
        model.rg = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateCelularLengthFail() {
        model.celular = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTextoGrandeLengthFail() {
        model.textoGrande = RandomStringUtils.randomAlphabetic(301)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateSnakeCaseLengthFail() {
        model.snakeCase = RandomStringUtils.randomAlphabetic(201)

        val permission = Permission(Permission.PRINCIPAL_READ_ALL)
        subject.validatePrincipal(permission, model, updating = true)
    }

    @Test
    fun testList() {
        val result = subject.list(listFilter)
        assertFalse(result.items.isEmpty())
        assertNotEquals(0, result.total)
    }

    @Test
    fun testListPaginated() {
        listFilter.page = 0
        listFilter.limit = 1

        val result = subject.list(listFilter)
        assertFalse(result.items.isEmpty())
        assertNotEquals(0, result.total)
        assertTrue(result.items.size <= listFilter.limit ?: 0)
    }

    @Test
    fun testGetSuccess() {
        val result = subject.get(id)
        assertNotSame(0, result.id)
        assertNotNull(result.tagPrincipal)
    }

    @Test(expected = NotFoundException::class)
    fun testGetFail() {
        subject.get(0)
    }

    @Test
    fun testCreateSuccess() {
        model.id = 0

        val result = subject.create(model)
        assertTrue(result > 0)
    }

    @Test(expected = BadRequestException::class)
    fun testCreateFail() {
        model.id = id

        subject.create(model)
    }

    @Test
    fun testUpdateSuccess() {
        model.id = id

        val result = subject.update(model)
        assertTrue(result > 0)
    }

    @Test(expected = BadRequestException::class)
    fun testUpdateFail() {
        model.id = 0

        subject.update(model)
    }

    @Test
    fun testRemoveSuccess() {
        subject.remove(id)
    }

    @Test(expected = NotFoundException::class)
    fun testRemoveFail() {
        subject.remove(0)
    }
}

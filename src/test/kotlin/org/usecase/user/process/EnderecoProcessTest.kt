package org.usecase.user.process

import org.apache.commons.lang3.RandomStringUtils
import org.usecase.user.ProcessTest
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.model.resource.Endereco
import org.usecase.model.param.EnderecoListParam
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertNotEquals
import kotlin.test.assertNotSame
import org.junit.Test
import org.usecase.user.context.Permission

/**
 * Tests Endereco business logic
 * @author Simpli CLI generator
 */
class EnderecoProcessTest : ProcessTest() {
    private val id = 1L
    private val model = Endereco()

    private val listFilter = EnderecoListParam()

    private val subject = EnderecoProcess(context)

    init {
        model.idEnderecoPk = 1
    }

    @Test(expected = BadRequestException::class)
    fun testValidateCepLengthFail() {
        model.cep = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.ENDERECO_READ_ALL)
        subject.validateEndereco(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateZipcodeLengthFail() {
        model.zipcode = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.ENDERECO_READ_ALL)
        subject.validateEndereco(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateRuaLengthFail() {
        model.rua = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.ENDERECO_READ_ALL)
        subject.validateEndereco(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateCidadeLengthFail() {
        model.cidade = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.ENDERECO_READ_ALL)
        subject.validateEndereco(permission, model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateUfLengthFail() {
        model.uf = RandomStringUtils.randomAlphabetic(46)

        val permission = Permission(Permission.ENDERECO_READ_ALL)
        subject.validateEndereco(permission, model, updating = true)
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
}

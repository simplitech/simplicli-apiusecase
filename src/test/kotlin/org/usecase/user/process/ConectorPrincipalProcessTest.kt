package org.usecase.user.process

import org.apache.commons.lang3.RandomStringUtils
import org.usecase.user.ProcessTest
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.model.resource.ConectorPrincipal
import org.usecase.model.param.ConectorPrincipalListParam
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertNotEquals
import kotlin.test.assertNotSame
import org.junit.Test

/**
 * Tests ConectorPrincipal business logic
 * @author Simpli CLI generator
 */
class ConectorPrincipalProcessTest : ProcessTest() {
    private val idPrincipalFk = 1L
    private val idConectadoFk = 1L
    private val model = ConectorPrincipal()

    private val listFilter = ConectorPrincipalListParam()

    private val subject = ConectorPrincipalProcess(context)

    init {
        model.idPrincipalFk = 1
        model.idConectadoFk = 1
        model.titulo = "1"
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTituloNullFail() {
        model.titulo = ""

        subject.validateConectorPrincipal(model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTituloLengthFail() {
        model.titulo = RandomStringUtils.randomAlphabetic(46)

        subject.validateConectorPrincipal(model, updating = true)
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
        val result = subject.get(idPrincipalFk, idConectadoFk)
        assertNotSame(0, result.idPrincipalFk)
        assertNotSame(0, result.idConectadoFk)
    }

    @Test(expected = NotFoundException::class)
    fun testGetFail() {
        subject.get(0, 0)
    }

    @Test
    fun testCreateSuccess() {
        model.idPrincipalFk = 1
        model.idConectadoFk = 2

        val result = subject.persist(model)
        assertTrue(result > 0)
    }

    @Test
    fun testUpdateSuccess() {
        model.idPrincipalFk = idPrincipalFk
        model.idConectadoFk = idConectadoFk

        val result = subject.persist(model)
        assertTrue(result > 0)
    }
}

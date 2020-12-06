package org.usecase.user.process

import br.com.simpli.sql.Query
import org.apache.commons.lang3.RandomStringUtils
import org.usecase.user.ProcessTest
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.model.resource.ExtensaoDoPrincipal
import org.usecase.model.param.ExtensaoDoPrincipalListParam
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertNotEquals
import kotlin.test.assertNotSame
import org.junit.Test
import org.usecase.model.rm.ExtensaoDoPrincipalRM
import org.usecase.user.context.Permission

/**
 * Tests ExtensaoDoPrincipal business logic
 * @author Simpli CLI generator
 */
class ExtensaoDoPrincipalProcessTest : ProcessTest() {
    private val id = 1L
    private val model = ExtensaoDoPrincipal()

    private val listFilter = ExtensaoDoPrincipalListParam()

    private val subject = ExtensaoDoPrincipalProcess(context)

    init {
        model.idPrincipalFk = 1
        model.titulo = "1"
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTituloNullFail() {
        model.titulo = ""

        subject.validateExtensaoDoPrincipal(model, updating = true)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTituloLengthFail() {
        model.titulo = RandomStringUtils.randomAlphabetic(46)

        subject.validateExtensaoDoPrincipal(model, updating = true)
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
        model.id = id

        val extensaoDoPrincipalRm = ExtensaoDoPrincipalRM(Permission())

        transacConnector.execute(Query()
                .deleteFrom(extensaoDoPrincipalRm.table)
                .whereEq(extensaoDoPrincipalRm.idPrincipalFk.column, id))

        val result = subject.persist(model)
        assertTrue(result > 0)
    }

    @Test
    fun testUpdateSuccess() {
        model.id = id

        val result = subject.persist(model)
        assertTrue(result > 0)
    }
}

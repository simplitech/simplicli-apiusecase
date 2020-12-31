package org.usecase.process

import br.com.simpli.sql.Query
import org.apache.commons.lang3.RandomStringUtils
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.model.resource.ExtensaoDoPrincipal
import org.usecase.model.param.ExtensaoDoPrincipalListParam
import org.junit.Test
import org.usecase.AppTest
import org.usecase.model.rm.ExtensaoDoPrincipalRM
import kotlin.test.*

/**
 * Tests ExtensaoDoPrincipal business logic
 * @author Simpli CLI generator
 */
class ExtensaoDoPrincipalProcessTest : AppTest() {
    private val subject = ExtensaoDoPrincipalProcess(context)

    private fun createModel(id: Long = 0) = ExtensaoDoPrincipal().apply {
        idPrincipalFk = id
        titulo = "1"
    }

    @Test
    fun testGet() {
        val result = subject.get(1)
        assertNotSame(0, result.id)

        assertFailsWith<NotFoundException> {
            subject.get(0)
        }
    }

    @Test
    fun testList() {
        ExtensaoDoPrincipalListParam().also {
            val result = subject.list(it)
            assertFalse(result.items.isEmpty())
            assertNotEquals(0, result.total)

            it.page = 0
            it.limit = 1

            val resultPaginated = subject.list(it)
            assertFalse(resultPaginated.items.isEmpty())
            assertNotEquals(0, resultPaginated.total)
            assertTrue(resultPaginated.items.size <= it.limit ?: 0)
        }
    }

    @Test
    fun testPersist() {
        val resultUpdate = subject.persist(createModel(1))
        assertTrue(resultUpdate > 0)

        ExtensaoDoPrincipalRM(context.permission).apply {
            Query().apply {
                deleteFrom(table)
                whereEq(idPrincipalFk.column, 1)
                pipe.execute(this)
            }
        }

        val resultCreate = subject.persist(createModel(1))
        assertTrue(resultCreate > 0)
    }

    @Test
    fun testValidateExtensaoDoPrincipal() {
        assertFailsWith<BadRequestException> {
            val model = createModel().apply { titulo = "" }
            subject.validateExtensaoDoPrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { titulo = RandomStringUtils.randomAlphabetic(46) }
            subject.validateExtensaoDoPrincipal(model, updating = true)
        }
    }
}

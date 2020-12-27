package org.usecase.process

import br.com.simpli.sql.Query
import org.apache.commons.lang3.RandomStringUtils
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.model.resource.ConectorPrincipal
import org.usecase.model.param.ConectorPrincipalListParam
import org.junit.Test
import org.usecase.AppTest
import org.usecase.model.rm.ConectorPrincipalRM
import org.usecase.model.rm.ExtensaoDoPrincipalRM
import kotlin.test.*

/**
 * Tests ConectorPrincipal business logic
 * @author Simpli CLI generator
 */
class ConectorPrincipalProcessTest : AppTest() {
    private val subject = ConectorPrincipalProcess(context)

    private fun createModel(id: Long = 0) = ConectorPrincipal().apply {
        idPrincipalFk = id
        idConectadoFk = id
        titulo = "1"
    }

    @Test
    fun testGet() {
        val result = subject.get(1, 1)
        assertNotSame(0, result.idPrincipalFk)
        assertNotSame(0, result.idConectadoFk)

        assertFailsWith<NotFoundException> {
            subject.get(0, 0)
        }
    }

    @Test
    fun testList() {
        ConectorPrincipalListParam().also {
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

        ConectorPrincipalRM(context.permission).apply {
            Query().apply {
                deleteFrom(table)
                whereEq(idPrincipalFk.column, 1)
                whereEq(idConectadoFk.column, 1)
                pipe.execute(this)
            }
        }

        val resultCreate = subject.persist(createModel(1))
        assertTrue(resultCreate > 0)
    }

    @Test
    fun testValidateConectorPrincipal() {
        assertFailsWith<BadRequestException> {
            val model = createModel().apply { titulo = "" }
            subject.validateConectorPrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { titulo = RandomStringUtils.randomAlphabetic(46) }
            subject.validateConectorPrincipal(model, updating = true)
        }
    }
}

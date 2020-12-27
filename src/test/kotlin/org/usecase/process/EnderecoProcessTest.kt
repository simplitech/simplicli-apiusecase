package org.usecase.process

import org.apache.commons.lang3.RandomStringUtils
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.model.resource.Endereco
import org.usecase.model.param.EnderecoListParam
import org.junit.Test
import org.usecase.AppTest
import kotlin.test.*

/**
 * Tests Endereco business logic
 * @author Simpli CLI generator
 */
class EnderecoProcessTest : AppTest() {
    private val subject = EnderecoProcess(context)

    private fun createModel(id: Long = 0) = Endereco().apply {
        idEnderecoPk = id
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
        EnderecoListParam().also {
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
        subject.persist(createModel())
    }

    @Test
    fun testCreate() {
        val result = subject.create(createModel(0))
        assertTrue(result > 0)

        assertFailsWith<BadRequestException> {
            subject.create(createModel(1))
        }
    }

    @Test
    fun testUpdate() {
        val result = subject.update(createModel(1))
        assertTrue(result > 0)

        assertFailsWith<BadRequestException> {
            subject.update(createModel(0))
        }
    }

    @Test
    fun testValidateEndereco() {
        assertFailsWith<BadRequestException> {
            val model = createModel().apply { cep = RandomStringUtils.randomAlphabetic(46) }
            subject.validateEndereco(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { zipcode = RandomStringUtils.randomAlphabetic(46) }
            subject.validateEndereco(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { rua = RandomStringUtils.randomAlphabetic(46) }
            subject.validateEndereco(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { cidade = RandomStringUtils.randomAlphabetic(46) }
            subject.validateEndereco(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { uf = RandomStringUtils.randomAlphabetic(46) }
            subject.validateEndereco(model, updating = true)
        }
    }
}

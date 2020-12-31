package org.usecase.process

import org.apache.commons.lang3.RandomStringUtils
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.model.resource.Principal
import org.usecase.model.param.PrincipalListParam
import org.junit.Test
import org.usecase.AppTest
import org.usecase.model.resource.Tag
import java.util.*
import kotlin.test.*

/**
 * Tests Principal business logic
 * @author Simpli CLI generator
 */
class PrincipalProcessTest : AppTest() {
    private val subject = PrincipalProcess(context)

    private fun createModel(id: Long = 0) = Principal().apply {
        idPrincipalPk = id
        textoObrigatorio = "1"
        decimalObrigatorio = 1.0
        inteiroObrigatorio = 1
        booleanoObrigatorio = true
        dataObrigatoria = Date()
        datahoraObrigatoria = Date()
        ativo = true
        idGrupoDoPrincipalFk = 1
        unico = "1"
        dataCriacao = Date()

        tagPrincipal = mutableListOf(Tag().apply { this.id = 1 })
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
        PrincipalListParam().also {
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
    fun testRemove() {
        subject.remove(1)

        assertFailsWith<NotFoundException> {
            subject.remove(0)
        }
    }

    @Test
    fun testValidatePrincipal() {
        assertFailsWith<BadRequestException> {
            val model = createModel().apply { textoObrigatorio = "" }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { textoObrigatorio = RandomStringUtils.randomAlphabetic(161) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { textoFacultativo = RandomStringUtils.randomAlphabetic(46) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { inteiroObrigatorio = 0L }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { dataObrigatoria = null }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { datahoraObrigatoria = null }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { email = RandomStringUtils.randomAlphabetic(201) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { email = "notAnEmail" }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { senha = RandomStringUtils.randomAlphabetic(201) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { urlImagem = RandomStringUtils.randomAlphabetic(201) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { url = RandomStringUtils.randomAlphabetic(201) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { idGrupoDoPrincipalFk = 0L }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { unico = "" }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { unico = RandomStringUtils.randomAlphabetic(41) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { dataCriacao = null }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { nome = RandomStringUtils.randomAlphabetic(46) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { titulo = RandomStringUtils.randomAlphabetic(46) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { cpf = RandomStringUtils.randomAlphabetic(46) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { cnpj = RandomStringUtils.randomAlphabetic(46) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { rg = RandomStringUtils.randomAlphabetic(46) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { celular = RandomStringUtils.randomAlphabetic(46) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { textoGrande = RandomStringUtils.randomAlphabetic(301) }
            subject.validatePrincipal(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { snakeCase = RandomStringUtils.randomAlphabetic(201) }
            subject.validatePrincipal(model, updating = true)
        }
    }
}

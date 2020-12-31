package org.usecase.process

import org.apache.commons.lang3.RandomStringUtils
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.model.resource.User
import org.usecase.model.param.UserListParam
import org.junit.Test
import org.usecase.AppTest
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role
import kotlin.test.*

/**
 * Tests User business logic
 * @author Simpli CLI generator
 */
class UserProcessTest : AppTest() {
    private val subject = UserProcess(context)

    private fun createModel(id: Long = 0) = User().apply {
        idUserPk = id
        email = "any@email.com"
        senha = "1"

        permissions = mutableListOf(Permission().apply { this.id = 1 })
        roles = mutableListOf(Role().apply { this.id = 1 })
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
        UserListParam().also {
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
    fun testValidateUser() {
        assertFailsWith<BadRequestException> {
            val model = createModel().apply { email = "" }
            subject.validateUser(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { email = RandomStringUtils.randomAlphabetic(46) }
            subject.validateUser(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { email = "notAnEmail" }
            subject.validateUser(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { senha = "" }
            subject.validateUser(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { senha = RandomStringUtils.randomAlphabetic(201) }
            subject.validateUser(model, updating = true)
        }
    }
}

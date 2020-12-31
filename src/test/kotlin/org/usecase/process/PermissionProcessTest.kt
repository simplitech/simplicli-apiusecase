package org.usecase.process

import org.apache.commons.lang3.RandomStringUtils
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.model.resource.Permission
import org.usecase.model.param.PermissionListParam
import org.junit.Test
import org.usecase.AppTest
import org.usecase.model.resource.Role
import org.usecase.model.resource.User
import kotlin.test.*

/**
 * Tests Permission business logic
 * @author Simpli CLI generator
 */
class PermissionProcessTest : AppTest() {
    private val subject = PermissionProcess(context)

    private fun createModel(id: Long = 0) = Permission().apply {
        idPermissionPk = id
        scope = "1"
        name = "1"

        permissions = mutableListOf(Permission().apply { this.id = 1 })
        roles = mutableListOf(Role().apply { this.id = 1 })
        users = mutableListOf(User().apply { this.id = 1 })
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
        PermissionListParam().also {
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
    fun testValidatePermission() {
        assertFailsWith<BadRequestException> {
            val model = createModel().apply { scope = "" }
            subject.validatePermission(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { scope = RandomStringUtils.randomAlphabetic(128) }
            subject.validatePermission(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { name = "" }
            subject.validatePermission(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { name = RandomStringUtils.randomAlphabetic(128) }
            subject.validatePermission(model, updating = true)
        }

        assertFailsWith<BadRequestException> {
            val model = createModel().apply { description = RandomStringUtils.randomAlphabetic(256) }
            subject.validatePermission(model, updating = true)
        }
    }
}

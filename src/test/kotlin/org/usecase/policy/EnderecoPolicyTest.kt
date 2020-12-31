package org.usecase.policy

import org.junit.Test
import org.usecase.AppTest
import org.usecase.exception.response.ForbiddenException
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Permission.Companion.groupOf
import org.usecase.model.resource.Endereco
import kotlin.test.assertFailsWith

/**
 * Tests Endereco Policy
 * @author Simpli CLI generator
 */
class EnderecoPolicyTest : AppTest() {
    private val subject = EnderecoPolicy(context)

    @Test
    fun testRead() {
        switchUser(ID.admin)
        subject.read()

        switchUser(ID.manager)
        subject.read()

        switchUser(ID.viewer)
        subject.read()

        switchToAnonymous(groupOf(Permission.ENDERECO_READ_ALL))
        subject.read()

        switchToAnonymous(groupOf(Permission.ENDERECO_FULL_CONTROL))
        subject.read()

        switchToAnonymous(groupOf(Permission.FULL_CONTROL))
        subject.read()

        assertFailsWith<ForbiddenException> {
            switchUser(ID.guest)
            subject.read()
        }

        assertFailsWith<ForbiddenException> {
            switchToAnonymous()
            subject.read()
        }
    }

    @Test
    fun testPersist() {
        val toCreate = Endereco().apply { id = 0 }
        val toUpdate = Endereco().apply { id = 1 }

        switchUser(ID.admin)
        subject.persist(toCreate)
        subject.persist(toUpdate)

        switchUser(ID.manager)
        subject.persist(toCreate)
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.ENDERECO_INSERT_ALL))
        subject.persist(toCreate)

        switchToAnonymous(groupOf(Permission.ENDERECO_UPDATE_ALL))
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.ENDERECO_FULL_CONTROL))
        subject.persist(toCreate)
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.FULL_CONTROL))
        subject.persist(toCreate)
        subject.persist(toUpdate)

        assertFailsWith<ForbiddenException> {
            switchToAnonymous(groupOf(Permission.ENDERECO_UPDATE_ALL))
            subject.persist(toCreate)
        }

        assertFailsWith<ForbiddenException> {
            switchToAnonymous(groupOf(Permission.ENDERECO_INSERT_ALL))
            subject.persist(toUpdate)
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.viewer)
            subject.persist(Endereco())
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.guest)
            subject.persist(Endereco())
        }

        assertFailsWith<ForbiddenException> {
            switchToAnonymous()
            subject.persist(Endereco())
        }
    }
}

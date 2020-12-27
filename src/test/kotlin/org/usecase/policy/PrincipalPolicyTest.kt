package org.usecase.policy

import org.junit.Test
import org.usecase.AppTest
import org.usecase.exception.response.ForbiddenException
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Permission.Companion.groupOf
import org.usecase.model.resource.Principal
import kotlin.test.assertFailsWith

/**
 * Tests Principal Policy
 * @author Simpli CLI generator
 */
class PrincipalPolicyTest : AppTest() {
    private val subject = PrincipalPolicy(context)

    @Test
    fun testRead() {
        switchUser(ID.admin)
        subject.read()

        switchUser(ID.manager)
        subject.read()

        switchUser(ID.viewer)
        subject.read()

        switchToAnonymous(groupOf(Permission.PRINCIPAL_READ_ALL))
        subject.read()

        switchToAnonymous(groupOf(Permission.PRINCIPAL_FULL_CONTROL))
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
        val toCreate = Principal().apply { id = 0 }
        val toUpdate = Principal().apply { id = 1 }

        switchUser(ID.admin)
        subject.persist(toCreate)
        subject.persist(toUpdate)

        switchUser(ID.manager)
        subject.persist(toCreate)
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.PRINCIPAL_INSERT_ALL))
        subject.persist(toCreate)

        switchToAnonymous(groupOf(Permission.PRINCIPAL_UPDATE_ALL))
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.PRINCIPAL_FULL_CONTROL))
        subject.persist(toCreate)
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.FULL_CONTROL))
        subject.persist(toCreate)
        subject.persist(toUpdate)

        assertFailsWith<ForbiddenException> {
            switchToAnonymous(groupOf(Permission.PRINCIPAL_UPDATE_ALL))
            subject.persist(toCreate)
        }

        assertFailsWith<ForbiddenException> {
            switchToAnonymous(groupOf(Permission.PRINCIPAL_INSERT_ALL))
            subject.persist(toUpdate)
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.viewer)
            subject.persist(Principal())
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.guest)
            subject.persist(Principal())
        }

        assertFailsWith<ForbiddenException> {
            switchToAnonymous()
            subject.persist(Principal())
        }
    }

    @Test
    fun testDelete() {
        switchUser(ID.admin)
        subject.delete()

        switchToAnonymous(groupOf(Permission.PRINCIPAL_DELETE))
        subject.delete()

        assertFailsWith<ForbiddenException> {
            switchUser(ID.manager)
            subject.delete()
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.viewer)
            subject.delete()
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.guest)
            subject.delete()
        }

        assertFailsWith<ForbiddenException> {
            switchToAnonymous()
            subject.delete()
        }
    }
}

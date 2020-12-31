package org.usecase.policy

import org.junit.Test
import org.usecase.AppTest
import org.usecase.exception.response.ForbiddenException
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Role
import org.usecase.model.resource.Permission.Companion.groupOf
import kotlin.test.assertFailsWith

/**
 * Tests Role Policy
 * @author Simpli CLI generator
 */
class RolePolicyTest : AppTest() {
    private val subject = RolePolicy(context)

    @Test
    fun testRead() {
        switchUser(ID.admin)
        subject.read()

        switchUser(ID.manager)
        subject.read()

        switchUser(ID.viewer)
        subject.read()

        switchToAnonymous(groupOf(Permission.ROLE_READ_ALL))
        subject.read()

        switchToAnonymous(groupOf(Permission.ROLE_FULL_CONTROL))
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
        val toCreate = Role().apply { id = 0 }
        val toUpdate = Role().apply { id = 1 }

        switchUser(ID.admin)
        subject.persist(toCreate)
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.ROLE_INSERT_ALL))
        subject.persist(toCreate)

        switchToAnonymous(groupOf(Permission.ROLE_UPDATE_ALL))
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.ROLE_FULL_CONTROL))
        subject.persist(toCreate)
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.FULL_CONTROL))
        subject.persist(toCreate)
        subject.persist(toUpdate)

        assertFailsWith<ForbiddenException> {
            switchToAnonymous(groupOf(Permission.ROLE_UPDATE_ALL))
            subject.persist(toCreate)
        }

        assertFailsWith<ForbiddenException> {
            switchToAnonymous(groupOf(Permission.ROLE_INSERT_ALL))
            subject.persist(toUpdate)
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.manager)
            subject.persist(Role())
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.viewer)
            subject.persist(Role())
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.guest)
            subject.persist(Role())
        }

        assertFailsWith<ForbiddenException> {
            switchToAnonymous()
            subject.persist(Role())
        }
    }
}

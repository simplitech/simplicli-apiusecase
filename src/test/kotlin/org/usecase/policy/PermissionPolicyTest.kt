package org.usecase.policy

import org.junit.Test
import org.usecase.AppTest
import org.usecase.exception.response.ForbiddenException
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Permission.Companion.groupOf
import kotlin.test.assertFailsWith

/**
 * Tests Permission Policy
 * @author Simpli CLI generator
 */
class PermissionPolicyTest : AppTest() {
    private val subject = PermissionPolicy(context)

    @Test
    fun testRead() {
        switchUser(ID.admin)
        subject.read()

        switchUser(ID.manager)
        subject.read()

        switchUser(ID.viewer)
        subject.read()

        switchToAnonymous(groupOf(Permission.PERMISSION_READ_ALL))
        subject.read()

        switchToAnonymous(groupOf(Permission.PERMISSION_FULL_CONTROL))
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
        val toCreate = Permission().apply { id = 0 }
        val toUpdate = Permission().apply { id = 1 }

        switchUser(ID.admin)
        subject.persist(toCreate)
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.PERMISSION_INSERT_ALL))
        subject.persist(toCreate)

        switchToAnonymous(groupOf(Permission.PERMISSION_UPDATE_ALL))
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.PERMISSION_FULL_CONTROL))
        subject.persist(toCreate)
        subject.persist(toUpdate)

        switchToAnonymous(groupOf(Permission.FULL_CONTROL))
        subject.persist(toCreate)
        subject.persist(toUpdate)

        assertFailsWith<ForbiddenException> {
            switchToAnonymous(groupOf(Permission.PERMISSION_UPDATE_ALL))
            subject.persist(toCreate)
        }

        assertFailsWith<ForbiddenException> {
            switchToAnonymous(groupOf(Permission.PERMISSION_INSERT_ALL))
            subject.persist(toUpdate)
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.manager)
            subject.persist(Permission())
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.viewer)
            subject.persist(Permission())
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.guest)
            subject.persist(Permission())
        }

        assertFailsWith<ForbiddenException> {
            switchToAnonymous()
            subject.persist(Permission())
        }
    }
}

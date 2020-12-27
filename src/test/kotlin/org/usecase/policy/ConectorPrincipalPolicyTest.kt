package org.usecase.policy

import org.junit.Test
import org.usecase.AppTest
import org.usecase.exception.response.ForbiddenException
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Permission.Companion.groupOf
import org.usecase.model.resource.ConectorPrincipal
import kotlin.test.assertFailsWith

/**
 * Tests ConectorPrincipal Policy
 * @author Simpli CLI generator
 */
class ConectorPrincipalPolicyTest : AppTest() {
    private val subject = ConectorPrincipalPolicy(context)

    @Test
    fun testRead() {
        switchUser(ID.admin)
        subject.read()

        switchUser(ID.manager)
        subject.read()

        switchUser(ID.viewer)
        subject.read()

        switchToAnonymous(groupOf(Permission.CONECTOR_PRINCIPAL_READ_ALL))
        subject.read()

        switchToAnonymous(groupOf(Permission.CONECTOR_PRINCIPAL_FULL_CONTROL))
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
        val toCreate = ConectorPrincipal()

        switchUser(ID.admin)
        subject.persist(toCreate)

        switchUser(ID.manager)
        subject.persist(toCreate)

        switchToAnonymous(groupOf(Permission.CONECTOR_PRINCIPAL_INSERT_ALL))
        subject.persist(toCreate)

        switchToAnonymous(groupOf(Permission.CONECTOR_PRINCIPAL_FULL_CONTROL))
        subject.persist(toCreate)

        switchToAnonymous(groupOf(Permission.FULL_CONTROL))
        subject.persist(toCreate)

        assertFailsWith<ForbiddenException> {
            switchUser(ID.viewer)
            subject.persist(ConectorPrincipal())
        }

        assertFailsWith<ForbiddenException> {
            switchUser(ID.guest)
            subject.persist(ConectorPrincipal())
        }

        assertFailsWith<ForbiddenException> {
            switchToAnonymous()
            subject.persist(ConectorPrincipal())
        }
    }
}

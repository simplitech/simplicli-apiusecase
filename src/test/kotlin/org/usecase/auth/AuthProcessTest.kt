package org.usecase.auth

import br.com.simpli.tools.SecurityUtils
import org.usecase.model.request.ChangePasswordRequest
import org.usecase.exception.response.UnauthorizedException
import org.usecase.model.param.DefaultParam
import org.junit.Test
import org.usecase.AppTest
import org.usecase.dao.*
import org.usecase.model.request.AuthRequest
import org.usecase.model.request.RecoverPasswordByMailRequest
import org.usecase.model.resource.*
import kotlin.test.*

/**
 * Tests the login service
 * @author Simpli CLI generator
 */
class AuthProcessTest : AppTest() {
    private val subject = AuthProcess(context)

    private val user = User().apply {
        email = "test@test.com"
        senha = SecurityUtils.sha256("tester")
        id = subject.dao.insert(this)
    }

    @Test
    fun testAuth() {
        param.authorization = "Bearer $token"
        subject.authenticate(param)

        assertFailsWith<UnauthorizedException> {
            param.authorization = "Bearer invalidtoken"
            subject.authenticate(param)
        }
    }

    @Test
    fun testSignIn() {
        val request = AuthRequest(user.email, user.senha)
        val result = subject.signIn(request)
        assertNull(result.requestOptions.isAnonymous)
        assertNull(result.requestOptions.isDirectAuth)
        assertNull(result.requestOptions.permission)

        assertFailsWith<UnauthorizedException> {
            subject.signIn(AuthRequest())
        }
    }

    @Test
    fun testRecoverPasswordByMail() {
        val request = RecoverPasswordByMailRequest(user.email)

        val result = subject.recoverPasswordByMail(request)
        assertEquals(1L, result)
    }

    @Test
    fun testChangePassword() {
        Permission.apply {
            switchUser(user.id, groupOf(USER_READ_ID_USER_PK, USER_UPDATE_SENHA, ROLE_READ_ALL, PERMISSION_READ_ALL))
        }

        val request = ChangePasswordRequest(
                user.senha,
                SecurityUtils.sha256("newpassword")
        )

        val result = subject.changePassword(request)
        assertEquals(1, result)

        assertFailsWith<UnauthorizedException> {
            val request = ChangePasswordRequest(
                    SecurityUtils.sha256("wrongpassword"),
                    SecurityUtils.sha256("newpassword")
            )

            subject.changePassword(request)
        }
    }

    @Test
    fun testGetUserByLoginInfo() {
        val mapPermission = hashMapOf<String, Permission>().apply {
            PermissionDao(context.con, context.permission).run {
                Permission().apply { name = "Root 1"; scope = "root-1"; id = insert(this); put("root-1", this) }
                Permission().apply { name = "Root 2"; scope = "root-2"; id = insert(this); put("root-2", this) }
                Permission().apply { name = "Parent 1"; scope = "parent-1"; id = insert(this); put("parent-1", this) }
                Permission().apply { name = "Parent 2"; scope = "parent-2"; id = insert(this); put("parent-2", this) }
                Permission().apply { name = "Parent 3"; scope = "parent-3"; id = insert(this); put("parent-3", this) }
                Permission().apply { name = "Child 1"; scope = "child-1"; id = insert(this); put("child-1", this) }
                Permission().apply { name = "Child 2"; scope = "child-2"; id = insert(this); put("child-2", this) }
                Permission().apply { name = "Child 3"; scope = "child-3"; id = insert(this); put("child-3", this) }
                Permission().apply { name = "Grandchild"; scope = "grandchild"; id = insert(this); put("grandchild", this) }
                Permission().apply { name = "Extra"; scope = "extra"; id = insert(this); put("extra", this) }
            }
        }

        PermissionPermissionDao(context.con, context.permission).run {
            PermissionPermission().apply {
                idPermissionParentFk = mapPermission["root-1"]?.id ?: 0
                idPermissionChildFk = mapPermission["parent-1"]?.id ?: 0
                insert(this)
            }
            PermissionPermission().apply {
                idPermissionParentFk = mapPermission["root-1"]?.id ?: 0
                idPermissionChildFk = mapPermission["parent-2"]?.id ?: 0
                insert(this)
            }
            PermissionPermission().apply {
                idPermissionParentFk = mapPermission["root-2"]?.id ?: 0
                idPermissionChildFk = mapPermission["parent-3"]?.id ?: 0
                insert(this)
            }
            PermissionPermission().apply {
                idPermissionParentFk = mapPermission["parent-1"]?.id ?: 0
                idPermissionChildFk = mapPermission["child-1"]?.id ?: 0
                insert(this)
            }
            PermissionPermission().apply {
                idPermissionParentFk = mapPermission["parent-1"]?.id ?: 0
                idPermissionChildFk = mapPermission["child-2"]?.id ?: 0
                insert(this)
            }
            PermissionPermission().apply {
                idPermissionParentFk = mapPermission["parent-1"]?.id ?: 0
                idPermissionChildFk = mapPermission["child-3"]?.id ?: 0
                insert(this)
            }
            PermissionPermission().apply {
                idPermissionParentFk = mapPermission["child-3"]?.id ?: 0
                idPermissionChildFk = mapPermission["grandchild"]?.id ?: 0
                insert(this)
            }
        }

        val role = RoleDao(context.con, context.permission).run {
            Role().apply {
                name = "Test Role"
                slug = "test-role"
                level = 10
                id = insert(this)
            }
        }

        RolePermissionDao(context.con, context.permission).run {
            RolePermission().apply {
                idRoleFk = role.id
                idPermissionFk = mapPermission["root-1"]?.id ?: 0
                insert (this)
            }
        }

        UserRoleDao(context.con, context.permission).run {
            UserRole().apply {
                idUserFk = this@AuthProcessTest.user.id
                idRoleFk = role.id
                insert (this)
            }
        }

        UserPermissionDao(context.con, context.permission).run {
            UserPermission().apply {
                idUserFk = this@AuthProcessTest.user.id
                idPermissionFk = mapPermission["root-2"]?.id ?: 0
                insert (this)
            }
            UserPermission().apply {
                idUserFk = this@AuthProcessTest.user.id
                idPermissionFk = mapPermission["extra"]?.id ?: 0
                insert (this)
            }
        }

        Permission.apply {
            switchUser(user.id, groupOf(USER_READ_ALL, ROLE_READ_ALL, PERMISSION_READ_ALL))
        }

        val result = subject.getUserByLoginInfo(user.email, user.senha)

        assertNotNull(result.roles)
        assertNotNull(result.permissions)

        assertTrue(result.scopes.contains("root-1"))
        assertTrue(result.scopes.contains("root-2"))
        assertTrue(result.scopes.contains("parent-1"))
        assertTrue(result.scopes.contains("parent-2"))
        assertTrue(result.scopes.contains("parent-3"))
        assertTrue(result.scopes.contains("child-1"))
        assertTrue(result.scopes.contains("child-2"))
        assertTrue(result.scopes.contains("child-3"))
        assertTrue(result.scopes.contains("grandchild"))
        assertTrue(result.scopes.contains("extra"))

        result.permissions?.forEach {
            assertTrue(it.scope == "root-2" || it.scope == "extra")

            if (it.scope == "root-2") {
                assertTrue(it.scopes.contains("parent-3"))
            }

            if (it.scope == "extra") {
                assertEquals(0, it.permissions?.size)
            }
        }

        result.roles?.forEach {
            assertTrue(it.scopes.contains("root-1"))
            assertTrue(it.scopes.contains("parent-1"))
            assertTrue(it.scopes.contains("parent-2"))
            assertTrue(it.scopes.contains("child-1"))
            assertTrue(it.scopes.contains("child-2"))
            assertTrue(it.scopes.contains("child-3"))
            assertTrue(it.scopes.contains("grandchild"))

            it.permissions?.forEach { root1 ->
                assertTrue(root1.scopes.contains("parent-1"))
                assertTrue(root1.scopes.contains("parent-2"))
                assertTrue(root1.scopes.contains("child-1"))
                assertTrue(root1.scopes.contains("child-2"))
                assertTrue(root1.scopes.contains("child-3"))
                assertTrue(root1.scopes.contains("grandchild"))

                root1.permissions?.forEach { parent1Or2 ->
                    assertTrue(parent1Or2.scope == "parent-1" || parent1Or2.scope == "parent-2")

                    if (parent1Or2.scope == "parent-1") {
                        assertTrue(parent1Or2.scopes.contains("child-1"))
                        assertTrue(parent1Or2.scopes.contains("child-2"))
                        assertTrue(parent1Or2.scopes.contains("child-3"))
                        assertTrue(parent1Or2.scopes.contains("grandchild"))

                        parent1Or2.permissions?.forEach { child1Or2Or3 ->
                            assertTrue(child1Or2Or3.scope == "child-1" || child1Or2Or3.scope == "child-2" || child1Or2Or3.scope == "child-3")

                            if (child1Or2Or3.scope == "child-1") {
                                assertEquals(0, child1Or2Or3.permissions?.size)
                            }

                            if (child1Or2Or3.scope == "child-2") {
                                assertEquals(0, child1Or2Or3.permissions?.size)
                            }

                            if (child1Or2Or3.scope == "child-3") {
                                assertTrue(child1Or2Or3.scopes.contains("grandchild"))

                                child1Or2Or3.permissions?.forEach { grandchild ->
                                    assertEquals(0, grandchild.permissions?.size)
                                }
                            }
                        }
                    }

                    if (parent1Or2.scope == "parent-2") {
                        assertEquals(0, parent1Or2.permissions?.size)
                    }
                }
            }
        }
    }

    @Test
    fun testCreateDirectAuthToken() {
        val token = subject.createDirectAuthToken(user.id)

        val result = DefaultParam().run {
            authorization = "Bearer $token"
            subject.authenticate(this)
        }

        assertEquals(user.id, result.user.id)
        assertTrue(result.requestOptions.isDirectAuth == true)
    }

    @Test
    fun testCreateAnonymousToken() {
        val token = AuthProcess.createAnonymousToken()

        val result = DefaultParam().run {
            authorization = "Bearer $token"
            subject.authenticate(this)
        }

        assertEquals(0, result.user.id)
        assertTrue(result.requestOptions.isAnonymous == true)
    }

    @Test
    fun testToken() {
        val request = AuthRequest(user.email, user.senha)
        val token = AuthProcess.requestToToken(request)
        val result = AuthProcess.tokenToRequest(token)
        assertEquals(request.email, result?.email)
    }
}

package org.usecase.user.auth

import org.usecase.user.ProcessTest
import org.usecase.user.request.AuthRequest
import org.usecase.user.request.ChangePasswordRequest
import org.usecase.app.Facade.Env
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.exception.response.UnauthorizedException
import org.usecase.param.DefaultParam
import br.com.simpli.tools.SecurityUtils.sha256
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the login service
 * @author Simpli CLI generator
 */
class AuthProcessTest : ProcessTest() {
    private val unauthorizedRequest = AuthRequest(Env.TESTER_LOGIN, "wrongpassword")
    private val subject = AuthProcess(context)

    @Test(expected = UnauthorizedException::class)
    fun testAuthFail() {
        val param = DefaultParam.Auth()
        param.authorization = "invalidtoken"
        subject.authenticate(param)
    }

    @Test
    fun testAuth() {
        val param = DefaultParam.Auth()
        param.authorization = "Bearer $token"
        subject.authenticate(param)
    }

    @Test(expected = UnauthorizedException::class)
    fun testSignInFail() {
        subject.signIn(unauthorizedRequest)
    }

    @Test
    fun testSignIn() {
        val result = subject.signIn(authRequest)
        assertEquals(token, result.token)
    }

    @Test
    fun testChangePassword() {
        val request = ChangePasswordRequest(
                sha256(Env.TESTER_PASSWORD),
                sha256("""${Env.TESTER_PASSWORD}new"""),
                sha256("""${Env.TESTER_PASSWORD}new""")
        )

        val result = subject.changePassword(request, auth)
        assertEquals(1L, result)
    }

    @Test(expected = BadRequestException::class)
    fun testChangePasswordWrongPassword() {
        val request = ChangePasswordRequest(
                sha256("wrongpassword"),
                sha256("""${Env.TESTER_PASSWORD}new"""),
                sha256("""${Env.TESTER_PASSWORD}new""")
        )

        subject.changePassword(request, auth)
    }

    @Test(expected = BadRequestException::class)
    fun testChangePasswordPasswordNoMatch() {
        val request = ChangePasswordRequest(
                sha256(Env.TESTER_PASSWORD),
                sha256("""${Env.TESTER_PASSWORD}new"""),
                sha256("""${Env.TESTER_PASSWORD}different""")
        )

        subject.changePassword(request, auth)
    }

    @Test(expected = NotFoundException::class)
    fun testGetIdNotFound() {
        subject.getId(unauthorizedRequest)
    }

    @Test
    fun testGetId() {
        val result = subject.getId(authRequest)
        assertEquals(user.id, result)
    }

    @Test(expected = NotFoundException::class)
    fun testGetUserNotFound() {
        subject.getUser(0)
    }

    @Test
    fun testGetUser() {
        val result = subject.getUser(user.id)
        assertEquals(user.email, result.email)
    }

    @Test
    fun testRequestToToken() {
        val result = AuthProcess.requestToToken(authRequest)
        assertEquals(token, result)
    }

    @Test
    fun testTokenToRequest() {
        val result = AuthProcess.tokenToRequest(token)
        assertEquals(user.email, result.email)
    }
}

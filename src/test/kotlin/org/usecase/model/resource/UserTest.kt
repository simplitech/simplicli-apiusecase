package org.usecase.model.resource

import org.usecase.AppTest
import org.usecase.exception.response.BadRequestException
import org.usecase.locale.EnUs
import java.util.Date
import org.apache.commons.lang3.RandomStringUtils
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * Tests User
 * @author Simpli CLI generator
 */
class UserTest: AppTest() {
    private val lang = EnUs()
    private val model = User()

    init {
        model.idUserPk = 1
        model.email = "any@email.com"
        model.senha = "1"
    }

    @Test(expected = BadRequestException::class)
    fun testValidateEmailNullFail() {
        model.email = ""

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateEmailLengthFail() {
        model.email = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateEmailInvalidEmailFail() {
        model.email = "notAnEmail"

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateSenhaNullFail() {
        model.senha = ""

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateSenhaLengthFail() {
        model.senha = RandomStringUtils.randomAlphabetic(201)

        model.validate(lang)
    }

    @Test
    fun testValidateSuccess() {
        model.validate(lang)
    }
}

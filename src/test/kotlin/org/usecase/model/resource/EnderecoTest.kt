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
 * Tests Endereco
 * @author Simpli CLI generator
 */
class EnderecoTest: AppTest() {
    private val lang = EnUs()
    private val model = Endereco()

    init {
        model.idEnderecoPk = 1
    }

    @Test(expected = BadRequestException::class)
    fun testValidateCepLengthFail() {
        model.cep = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateZipcodeLengthFail() {
        model.zipcode = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateRuaLengthFail() {
        model.rua = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateCidadeLengthFail() {
        model.cidade = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test(expected = BadRequestException::class)
    fun testValidateUfLengthFail() {
        model.uf = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test
    fun testValidateSuccess() {
        model.validate(lang)
    }
}

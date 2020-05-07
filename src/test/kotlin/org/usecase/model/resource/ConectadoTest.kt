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
 * Tests Conectado
 * @author Simpli CLI generator
 */
class ConectadoTest: AppTest() {
    private val lang = EnUs()
    private val model = Conectado()

    init {
        model.idConectadoPk = 1
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTituloLengthFail() {
        model.titulo = RandomStringUtils.randomAlphabetic(46)

        model.validate(lang)
    }

    @Test
    fun testValidateSuccess() {
        model.validate(lang)
    }
}

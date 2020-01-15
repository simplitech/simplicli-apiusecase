package org.usecase.model.resource

import org.usecase.AppTest
import org.usecase.dao.GrupoDoPrincipalDao
import org.usecase.exception.response.BadRequestException
import org.usecase.locale.EnUs
import java.util.Date
import org.apache.commons.lang3.RandomStringUtils
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * Tests GrupoDoPrincipal
 * @author Simpli CLI generator
 */
class GrupoDoPrincipalTest: AppTest() {
    private val lang = EnUs()
    private val model = GrupoDoPrincipal()

    init {
        model.idGrupoDoPrincipalPk = 1
        model.titulo = "1"
    }

    @Test(expected = BadRequestException::class)
    fun testValidateTituloNullFail() {
        model.titulo = ""

        model.validate(lang)
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

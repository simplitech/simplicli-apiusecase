package org.usecase.user.process

import org.usecase.user.ProcessTest
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import org.usecase.model.resource.Principal
import org.usecase.param.DefaultParam
import org.usecase.model.resource.Tag
import java.util.Date
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNotSame
import org.junit.Ignore
import org.junit.Test
import org.usecase.param.AuthPrincipalListFilter

/**
 * Tests Principal business logic
 * @author Simpli CLI generator
 */
class PrincipalProcessTest : ProcessTest() {
    private val id = 1L
    private val model = Principal()

    private val listFilter = AuthPrincipalListFilter()

    private val subject = PrincipalProcess(context)

    init {
        model.idPrincipalPk = 1
        model.textoObrigatorio = "1"
        model.decimalObrigatorio = 1.0
        model.inteiroObrigatorio = 1
        model.booleanoObrigatorio = true
        model.dataObrigatoria = Date()
        model.datahoraObrigatoria = Date()
        model.ativo = true
        model.idGrupoDoPrincipalFk = 1
        model.unico = "1"
        model.dataCriacao = Date()

        val tag = Tag()
        tag.id = 1L
        model.tagPrincipal = mutableListOf(tag)
    }

    @Test
    fun testList() {
        val result = subject.list(listFilter)
        assertFalse(result.items.isEmpty())
        assertNotEquals(0, result.total)
    }

    @Test
    fun testListPaginated() {
        listFilter.page = 0
        listFilter.limit = 1

        val result = subject.list(listFilter)
        assertFalse(result.items.isEmpty())
        assertNotEquals(0, result.total)
        assertTrue(result.items.size <= listFilter.limit ?: 0)
    }

    @Test
    fun testGetSuccess() {
        val result = subject.get(id)
        assertNotSame(0, result.id)
        assertNotNull(result.tagPrincipal)
    }

    @Test(expected = NotFoundException::class)
    fun testGetFail() {
        subject.get(0)
    }

    @Test
    fun testCreateSuccess() {
        model.id = 0

        val result = subject.create(model)
        assertTrue(result > 0)
    }

    @Test(expected = BadRequestException::class)
    fun testCreateFail() {
        model.id = id

        subject.create(model)
    }

    @Test
    fun testUpdateSuccess() {
        model.id = id

        val result = subject.update(model)
        assertTrue(result > 0)
    }

    @Test(expected = BadRequestException::class)
    fun testUpdateFail() {
        model.id = 0

        subject.update(model)
    }

    @Test
    fun testRemoveSuccess() {
        subject.remove(id)
    }

    @Test(expected = NotFoundException::class)
    fun testRemoveFail() {
        subject.remove(0)
    }
}

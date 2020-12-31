package org.usecase.process

import org.usecase.context.RequestContext
import org.usecase.dao.ConectorPrincipalDao
import org.usecase.model.filter.ConectorPrincipalListFilter
import org.usecase.model.resource.ConectorPrincipal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

/**
 * ConectorPrincipal business logic
 * @author Simpli CLI generator
 */
class ConectorPrincipalProcess(val context: RequestContext) {

    val dao = ConectorPrincipalDao(context.con, context.permission)

    fun get(idPrincipalFk: Long?, idConectadoFk: Long?): ConectorPrincipal {
        if (idPrincipalFk == null) throw BadRequestException()
        if (idConectadoFk == null) throw BadRequestException()

        return dao.getOne(idPrincipalFk, idConectadoFk) ?: throw NotFoundException()
    }

    fun list(filter: ConectorPrincipalListFilter): PageCollection<ConectorPrincipal> {
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    fun persist(model: ConectorPrincipal): Long {
        val exist = dao.exist(model.idPrincipalFk, model.idConectadoFk)

        if (exist) {
            update(model)
        } else {
            create(model)
        }

        return model.idPrincipalFk
    }

    private fun create(model: ConectorPrincipal): Long {
        validateConectorPrincipal(model, updating = false)
        dao.insert(model)

        return 1L
    }

    private fun update(model: ConectorPrincipal): Int {
        validateConectorPrincipal(model, updating = true)
        return dao.update(model)
    }

    fun validateConectorPrincipal(model: ConectorPrincipal, updating: Boolean) {
        context.lang.handleValidation("modelConectorPrincipal") {
            validate(model) {
                validate(ConectorPrincipal::titulo).isNotBlank().hasSize(max = 45)
            }
        }
    }

}

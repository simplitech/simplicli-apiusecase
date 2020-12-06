package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.ConectorPrincipalDao
import org.usecase.model.filter.ConectorPrincipalListFilter
import org.usecase.model.resource.ConectorPrincipal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.CONECTOR_PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.CONECTOR_PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.CONECTOR_PRINCIPAL_UPDATE_ALL
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

/**
 * ConectorPrincipal business logic
 * @author Simpli CLI generator
 */
class ConectorPrincipalProcess(val context: RequestContext) {

    val dao = ConectorPrincipalDao(context.con)

    fun get(idPrincipalFk: Long?, idConectadoFk: Long?): ConectorPrincipal {
        if (idPrincipalFk == null) throw BadRequestException()
        if (idConectadoFk == null) throw BadRequestException()
        val permission = Permission(CONECTOR_PRINCIPAL_READ_ALL)

        return dao.getOne(idPrincipalFk, idConectadoFk, permission) ?: throw NotFoundException()
    }

    fun list(filter: ConectorPrincipalListFilter): PageCollection<ConectorPrincipal> {
        val permission = Permission(CONECTOR_PRINCIPAL_READ_ALL)

        val items = dao.getList(filter, permission)
        val total = dao.count(filter, permission)

        return PageCollection(items, total)
    }

    fun persist(model: ConectorPrincipal): Long {
        val permission = Permission(CONECTOR_PRINCIPAL_READ_ALL)
        val exist = dao.exist(model.idPrincipalFk, model.idConectadoFk, permission)

        if (exist) {
            update(model)
        } else {
            create(model)
        }

        return model.idPrincipalFk
    }

    private fun create(model: ConectorPrincipal): Long {
        val permission = Permission(CONECTOR_PRINCIPAL_INSERT_ALL)
        validateConectorPrincipal(model, updating = false)
        dao.insert(model, permission)

        return 1L
    }

    private fun update(model: ConectorPrincipal): Int {
        val permission = Permission(CONECTOR_PRINCIPAL_UPDATE_ALL)
        validateConectorPrincipal(model, updating = true)
        return dao.update(model, permission)
    }

    fun validateConectorPrincipal(model: ConectorPrincipal, updating: Boolean) {
        context.lang.handleValidation("modelConectorPrincipal") {
            validate(model) {
                validate(ConectorPrincipal::titulo).isNotBlank().hasSize(max = 45)
            }
        }
    }

}

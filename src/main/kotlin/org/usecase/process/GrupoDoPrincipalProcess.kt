package org.usecase.process

import org.usecase.context.RequestContext
import org.usecase.dao.GrupoDoPrincipalDao
import org.usecase.model.filter.GrupoDoPrincipalListFilter
import org.usecase.model.resource.GrupoDoPrincipal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

/**
 * GrupoDoPrincipal business logic
 * @author Simpli CLI generator
 */
class GrupoDoPrincipalProcess(val context: RequestContext) {

    val dao = GrupoDoPrincipalDao(context.con, context.permission)

    fun get(id: Long?): GrupoDoPrincipal {
        if (id == null) throw BadRequestException()

        return dao.getOne(id) ?: throw NotFoundException()
    }

    fun list(filter: GrupoDoPrincipalListFilter): PageCollection<GrupoDoPrincipal> {
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    fun persist(model: GrupoDoPrincipal): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        return model.id
    }

    fun create(model: GrupoDoPrincipal): Long {
        validateGrupoDoPrincipal(model, updating = false)
        model.id = dao.insert(model)

        return model.id
    }

    fun update(model: GrupoDoPrincipal): Int {
        validateGrupoDoPrincipal(model, updating = true)
        return dao.update(model)
    }

    fun validateGrupoDoPrincipal(model: GrupoDoPrincipal, updating: Boolean) {
        context.lang.handleValidation("modelGrupoDoPrincipal") {
            validate(model) {
                validate(GrupoDoPrincipal::titulo).isNotBlank().hasSize(max = 45)
            }
        }

        if (updating) {
            if (!dao.exist(model.id)) {
                throw BadRequestException(context.lang["error.doesNotExist"])
            }
        } else {
            if (dao.exist(model.id)) {
                throw BadRequestException(context.lang["error.alreadyExist"])
            }
        }
    }

}

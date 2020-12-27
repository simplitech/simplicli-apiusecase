package org.usecase.process

import org.usecase.context.RequestContext
import org.usecase.dao.ConectadoDao
import org.usecase.model.filter.ConectadoListFilter
import org.usecase.model.resource.Conectado
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.valiktor.functions.hasSize
import org.valiktor.validate

/**
 * Conectado business logic
 * @author Simpli CLI generator
 */
class ConectadoProcess(val context: RequestContext) {

    val dao = ConectadoDao(context.con, context.permission)

    fun get(id: Long?): Conectado {
        if (id == null) throw BadRequestException()

        return dao.getOne(id) ?: throw NotFoundException()
    }

    fun list(filter: ConectadoListFilter): PageCollection<Conectado> {
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    fun persist(model: Conectado): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        return model.id
    }

    fun create(model: Conectado): Long {
        validateConectado(model, updating = false)
        model.id = dao.insert(model)

        return model.id
    }

    fun update(model: Conectado): Int {
        validateConectado(model, updating = true)
        return dao.update(model)
    }

    fun validateConectado(model: Conectado, updating: Boolean) {
        context.lang.handleValidation("modelConectado") {
            validate(model) {
                validate(Conectado::titulo).hasSize(max = 45)
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

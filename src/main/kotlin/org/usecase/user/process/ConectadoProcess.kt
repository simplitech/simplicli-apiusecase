package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.ConectadoDao
import org.usecase.model.filter.ConectadoListFilter
import org.usecase.model.resource.Conectado
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.CONECTADO_INSERT_ALL
import org.usecase.user.context.Permission.Companion.CONECTADO_READ_ALL
import org.usecase.user.context.Permission.Companion.CONECTADO_UPDATE_ALL
import org.valiktor.functions.hasSize
import org.valiktor.validate

/**
 * Conectado business logic
 * @author Simpli CLI generator
 */
class ConectadoProcess(val context: RequestContext) {

    val dao = ConectadoDao(context.con)

    fun get(id: Long?): Conectado {
        if (id == null) throw BadRequestException()

        val permission = Permission(CONECTADO_READ_ALL)

        return dao.getOne(id, permission) ?: throw NotFoundException()
    }

    fun list(filter: ConectadoListFilter): PageCollection<Conectado> {
        val permission = Permission(CONECTADO_READ_ALL)

        val items = dao.getList(filter, permission)
        val total = dao.count(filter, permission)

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
        val permission = Permission(CONECTADO_READ_ALL, CONECTADO_INSERT_ALL)
        validateConectado(permission, model, updating = false)
        model.id = dao.insert(model, permission)

        return model.id
    }

    fun update(model: Conectado): Int {
        val permission = Permission(CONECTADO_READ_ALL, CONECTADO_UPDATE_ALL)
        validateConectado(permission, model, updating = true)
        return dao.update(model, permission)
    }

    fun validateConectado(permission: Permission, model: Conectado, updating: Boolean) {
        context.lang.handleValidation("modelConectado") {
            validate(model) {
                validate(Conectado::titulo).hasSize(max = 45)
            }
        }

        if (updating) {
            if (!dao.exist(model.id, permission)) {
                throw BadRequestException(context.lang["error.doesNotExist"])
            }
        } else {
            if (dao.exist(model.id, permission)) {
                throw BadRequestException(context.lang["error.alreadyExist"])
            }
        }
    }

}

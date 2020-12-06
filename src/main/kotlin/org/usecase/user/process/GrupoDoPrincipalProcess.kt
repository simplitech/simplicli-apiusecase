package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.GrupoDoPrincipalDao
import org.usecase.model.filter.GrupoDoPrincipalListFilter
import org.usecase.model.resource.GrupoDoPrincipal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.GRUPO_DO_PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.GRUPO_DO_PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.GRUPO_DO_PRINCIPAL_UPDATE_ALL
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

/**
 * GrupoDoPrincipal business logic
 * @author Simpli CLI generator
 */
class GrupoDoPrincipalProcess(val context: RequestContext) {

    val dao = GrupoDoPrincipalDao(context.con)

    fun get(id: Long?): GrupoDoPrincipal {
        if (id == null) throw BadRequestException()
        val permission = Permission(GRUPO_DO_PRINCIPAL_READ_ALL)

        return dao.getOne(id, permission) ?: throw NotFoundException()
    }

    fun list(filter: GrupoDoPrincipalListFilter): PageCollection<GrupoDoPrincipal> {
        val permission = Permission(GRUPO_DO_PRINCIPAL_READ_ALL)
        val items = dao.getList(filter, permission)
        val total = dao.count(filter, permission)

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
        val permission = Permission(GRUPO_DO_PRINCIPAL_READ_ALL, GRUPO_DO_PRINCIPAL_INSERT_ALL)
        validateGrupoDoPrincipal(permission, model, updating = false)
        model.id = dao.insert(model, permission)

        return model.id
    }

    fun update(model: GrupoDoPrincipal): Int {
        val permission = Permission(GRUPO_DO_PRINCIPAL_READ_ALL, GRUPO_DO_PRINCIPAL_UPDATE_ALL)
        validateGrupoDoPrincipal(permission, model, updating = true)
        return dao.update(model, permission)
    }

    fun validateGrupoDoPrincipal(permission: Permission, model: GrupoDoPrincipal, updating: Boolean) {
        context.lang.handleValidation("modelGrupoDoPrincipal") {
            validate(model) {
                validate(GrupoDoPrincipal::titulo).isNotBlank().hasSize(max = 45)
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

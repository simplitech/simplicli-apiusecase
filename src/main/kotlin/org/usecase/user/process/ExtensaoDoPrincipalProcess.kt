package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.ExtensaoDoPrincipalDao
import org.usecase.model.filter.ExtensaoDoPrincipalListFilter
import org.usecase.model.resource.ExtensaoDoPrincipal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.EXTENSAO_DO_PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.EXTENSAO_DO_PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.EXTENSAO_DO_PRINCIPAL_UPDATE_ALL
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank

/**
 * ExtensaoDoPrincipal business logic
 * @author Simpli CLI generator
 */
class ExtensaoDoPrincipalProcess(val context: RequestContext) {

    val dao = ExtensaoDoPrincipalDao(context.con)

    fun get(id: Long?): ExtensaoDoPrincipal {
        if (id == null) throw BadRequestException()
        val permission = Permission(EXTENSAO_DO_PRINCIPAL_READ_ALL)

        return dao.getOne(id, permission) ?: throw NotFoundException()
    }

    fun list(filter: ExtensaoDoPrincipalListFilter): PageCollection<ExtensaoDoPrincipal> {
        val permission = Permission(EXTENSAO_DO_PRINCIPAL_READ_ALL)
        val items = dao.getList(filter, permission)
        val total = dao.count(filter, permission)

        return PageCollection(items, total)
    }

    fun persist(model: ExtensaoDoPrincipal): Long {
        val permission = Permission(EXTENSAO_DO_PRINCIPAL_READ_ALL)
        if (dao.exist(model.id, permission)) {
            update(model)
        } else {
            create(model)
        }

        return model.id
    }

    private fun create(model: ExtensaoDoPrincipal): Long {
        validateExtensaoDoPrincipal(model, updating = false)

        val permission = Permission(EXTENSAO_DO_PRINCIPAL_INSERT_ALL)
        dao.insert(model, permission)

        return model.id
    }

    private fun update(model: ExtensaoDoPrincipal): Int {
        validateExtensaoDoPrincipal(model, updating = true)

        val permission = Permission(EXTENSAO_DO_PRINCIPAL_UPDATE_ALL)
        return dao.update(model, permission)
    }

    fun validateExtensaoDoPrincipal(model: ExtensaoDoPrincipal, updating: Boolean = false) {
        context.lang.handleValidation("modelExtensaoDoPrincipal") {
            org.valiktor.validate(model) {
                validate(ExtensaoDoPrincipal::titulo).isNotBlank().hasSize(max = 45)
            }
        }
    }

}

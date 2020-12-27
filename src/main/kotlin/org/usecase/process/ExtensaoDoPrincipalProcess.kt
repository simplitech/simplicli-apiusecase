package org.usecase.process

import org.usecase.context.RequestContext
import org.usecase.dao.ExtensaoDoPrincipalDao
import org.usecase.model.filter.ExtensaoDoPrincipalListFilter
import org.usecase.model.resource.ExtensaoDoPrincipal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank

/**
 * ExtensaoDoPrincipal business logic
 * @author Simpli CLI generator
 */
class ExtensaoDoPrincipalProcess(val context: RequestContext) {

    val dao = ExtensaoDoPrincipalDao(context.con, context.permission)

    fun get(id: Long?): ExtensaoDoPrincipal {
        if (id == null) throw BadRequestException()

        return dao.getOne(id) ?: throw NotFoundException()
    }

    fun list(filter: ExtensaoDoPrincipalListFilter): PageCollection<ExtensaoDoPrincipal> {
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    fun persist(model: ExtensaoDoPrincipal): Long {
        if (dao.exist(model.id)) {
            update(model)
        } else {
            create(model)
        }

        return model.id
    }

    private fun create(model: ExtensaoDoPrincipal): Long {
        validateExtensaoDoPrincipal(model, updating = false)

        dao.insert(model)

        return model.id
    }

    private fun update(model: ExtensaoDoPrincipal): Int {
        validateExtensaoDoPrincipal(model, updating = true)

        return dao.update(model)
    }

    fun validateExtensaoDoPrincipal(model: ExtensaoDoPrincipal, updating: Boolean = false) {
        context.lang.handleValidation("modelExtensaoDoPrincipal") {
            org.valiktor.validate(model) {
                validate(ExtensaoDoPrincipal::titulo).isNotBlank().hasSize(max = 45)
            }
        }
    }

}

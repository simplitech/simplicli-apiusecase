package org.usecase.process

import org.usecase.context.RequestContext
import org.usecase.dao.ItemDoPrincipalDao
import org.usecase.model.filter.ItemDoPrincipalListFilter
import org.usecase.model.resource.ItemDoPrincipal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotZero
import org.valiktor.validate

/**
 * ItemDoPrincipal business logic
 * @author Simpli CLI generator
 */
class ItemDoPrincipalProcess(val context: RequestContext) {

    val dao = ItemDoPrincipalDao(context.con, context.permission)

    fun get(id: Long?): ItemDoPrincipal {
        if (id == null) throw BadRequestException()

        return dao.getOne(id) ?: throw NotFoundException()
    }

    fun list(filter: ItemDoPrincipalListFilter): PageCollection<ItemDoPrincipal> {
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    fun persist(model: ItemDoPrincipal): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        return model.id
    }

    fun create(model: ItemDoPrincipal): Long {
        validateItemDoPrincipal(model, updating = false)

        model.id = dao.insert(model)

        return model.id
    }

    fun update(model: ItemDoPrincipal): Int {
        validateItemDoPrincipal(model, updating = true)

        return dao.update(model)
    }

    fun validateItemDoPrincipal(model: ItemDoPrincipal, updating: Boolean) {
        context.lang.handleValidation("modelItemDoPrincipal") {
            validate(model) {
                validate(ItemDoPrincipal::titulo).isNotBlank().hasSize(max = 45)
                validate(ItemDoPrincipal::idPrincipalFk).isNotZero()
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

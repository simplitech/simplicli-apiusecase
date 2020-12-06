package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.ItemDoPrincipalDao
import org.usecase.model.filter.ItemDoPrincipalListFilter
import org.usecase.model.resource.ItemDoPrincipal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.ITEM_DO_PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.ITEM_DO_PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.ITEM_DO_PRINCIPAL_UPDATE_ALL
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotZero
import org.valiktor.validate

/**
 * ItemDoPrincipal business logic
 * @author Simpli CLI generator
 */
class ItemDoPrincipalProcess(val context: RequestContext) {

    val dao = ItemDoPrincipalDao(context.con)

    fun get(id: Long?): ItemDoPrincipal {
        if (id == null) throw BadRequestException()
        val permission = Permission(ITEM_DO_PRINCIPAL_READ_ALL)

        return dao.getOne(id, permission) ?: throw NotFoundException()
    }

    fun list(filter: ItemDoPrincipalListFilter): PageCollection<ItemDoPrincipal> {
        val permission = Permission(ITEM_DO_PRINCIPAL_READ_ALL)
        val items = dao.getList(filter, permission)
        val total = dao.count(filter, permission)

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
        val permission = Permission(ITEM_DO_PRINCIPAL_READ_ALL, ITEM_DO_PRINCIPAL_INSERT_ALL)
        validateItemDoPrincipal(permission, model, updating = false)

        model.id = dao.insert(model, permission)

        return model.id
    }

    fun update(model: ItemDoPrincipal): Int {
        val permission = Permission(ITEM_DO_PRINCIPAL_READ_ALL, ITEM_DO_PRINCIPAL_UPDATE_ALL)
        validateItemDoPrincipal(permission, model, updating = true)

        return dao.update(model, permission)
    }

    fun validateItemDoPrincipal(permission: Permission, model: ItemDoPrincipal, updating: Boolean) {
        context.lang.handleValidation("modelItemDoPrincipal") {
            validate(model) {
                validate(ItemDoPrincipal::titulo).isNotBlank().hasSize(max = 45)
                validate(ItemDoPrincipal::idPrincipalFk).isNotZero()
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

package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.ConectorPrincipalDao
import org.usecase.model.filter.ListFilter
import org.usecase.model.resource.ConectorPrincipal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection

/**
 * ConectorPrincipal business logic
 * @author Simpli CLI generator
 */
class ConectorPrincipalProcess(val context: RequestContext) {

    val dao = ConectorPrincipalDao(context.con)

    fun get(id1: Long?, id2: Long?): ConectorPrincipal {
        // TODO: review generated method
        if (id1 == null) throw BadRequestException()
        if (id2 == null) throw BadRequestException()

        return dao.getOne(id1, id2) ?: throw NotFoundException()
    }

    fun list(filter: ListFilter): PageCollection<ConectorPrincipal> {
        // TODO: review generated method
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    /**
     * Use this to handle similarities between create and update
     */
    fun persist(model: ConectorPrincipal): Long {
        val exist = dao.exist(model.id1, model.id2)

        if (exist) {
            update(model)
        } else {
            create(model)
        }

        return model.id1
    }

    fun create(model: ConectorPrincipal): Long {
        // TODO: review generated method
        model.apply {
            validate(context.lang)
        }

        dao.run {
            validate(model, updating = false)
            insert(model)
        }

        return 1L
    }

    fun update(model: ConectorPrincipal): Int {
        // TODO: review generated method
        model.apply {
            validate(context.lang)
        }

        return dao.run {
            validate(model, updating = true)
            update(model)
        }
    }

    private fun ConectorPrincipalDao.validate(model: ConectorPrincipal, updating: Boolean) {
        if (updating) {
            if (!exist(model.id1, model.id2)) {
                throw BadRequestException(context.lang["does_not_exist"])
            }
        } else {
            if (exist(model.id1, model.id2)) {
                throw BadRequestException(context.lang["already_exists"])
            }
        }
    }

}

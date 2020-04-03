package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.EnderecoDao
import org.usecase.model.filter.ListFilter
import org.usecase.model.resource.Endereco
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection

/**
 * Endereco business logic
 * @author Simpli CLI generator
 */
class EnderecoProcess(val context: RequestContext) {

    val dao = EnderecoDao(context.con)

    fun get(id: Long?): Endereco {
        // TODO: review generated method
        if (id == null) throw BadRequestException()

        return dao.getOne(id) ?: throw NotFoundException()
    }

    fun list(filter: ListFilter): PageCollection<Endereco> {
        // TODO: review generated method
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    /**
     * Use this to handle similarities between create and update
     */
    fun persist(model: Endereco): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        return model.id
    }

    fun create(model: Endereco): Long {
        // TODO: review generated method
        model.apply {
            validate(context.lang)
        }

        model.id = dao.run {
            validate(model, updating = false)
            insert(model)
        }

        return model.id
    }

    fun update(model: Endereco): Int {
        // TODO: review generated method
        model.apply {
            validate(context.lang)
        }

        return dao.run {
            validate(model, updating = true)
            update(model)
        }
    }

    private fun EnderecoDao.validate(model: Endereco, updating: Boolean) {
        if (updating) {
            if (!exist(model.id)) {
                throw BadRequestException(context.lang["does_not_exist"])
            }
        } else {
            if (exist(model.id)) {
                throw BadRequestException(context.lang["already_exists"])
            }
        }
    }

}

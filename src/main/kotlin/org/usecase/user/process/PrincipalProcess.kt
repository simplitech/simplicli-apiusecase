package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.PrincipalDao
import org.usecase.dao.TagPrincipalDao
import org.usecase.model.filter.ListFilter
import org.usecase.model.resource.Principal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.model.filter.PrincipalListFilter
import java.util.Date

/**
 * Principal business logic
 * @author Simpli CLI generator
 */
class PrincipalProcess(val context: RequestContext) {

    val dao = PrincipalDao(context.con)

    fun get(id: Long?): Principal {
        // TODO: review generated method
        if (id == null) throw BadRequestException()

        val tagPrincipalDao = TagPrincipalDao(context.con)

        val model = dao.getOne(id) ?: throw NotFoundException()
        model.tagPrincipal = tagPrincipalDao.listTagOfPrincipal(id)

        return model
    }

    fun list(filter: PrincipalListFilter): PageCollection<Principal> {
        // TODO: review generated method
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    /**
     * Use this to handle similarities between create and update
     */
    fun persist(model: Principal): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        val tagPrincipalDao = TagPrincipalDao(context.con)

        tagPrincipalDao.removeAllFromPrincipal(model.id)

        model.tagPrincipal?.let { list ->
            list.forEach { tagPrincipalDao.insert(model.id, it.id) }
        }

        return model.id
    }

    fun create(model: Principal): Long {
        // TODO: review generated method
        model.apply {
            ativo = true
            dataCriacao = Date()
            validate(context.lang)
        }

        model.id = dao.run {
            validate(model, updating = false)
            insert(model)
        }

        return model.id
    }

    fun update(model: Principal): Int {
        // TODO: review generated method
        model.apply {
            ativo = true
            dataAlteracao = Date()
            validate(context.lang)
        }

        return dao.run {
            validate(model, updating = true)
            update(model)
        }
    }

    fun remove(id: Long?): Long {
        // TODO: review generated method
        if (id == null) throw BadRequestException()

        val affectedRows = dao.softDelete(id)
        if (affectedRows == 0) throw NotFoundException()

        return id
    }

    private fun PrincipalDao.validate(model: Principal, updating: Boolean) {
        model.unico?.also {
            if (existUnico(it, model.id)) {
                throw BadRequestException(context.lang.alreadyExist(context.lang["Principal.unico"]))
            }
        }

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

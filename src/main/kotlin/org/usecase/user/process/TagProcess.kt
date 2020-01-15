package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.TagDao
import org.usecase.dao.TagPrincipalDao
import org.usecase.model.collection.ListFilter
import org.usecase.model.resource.Tag
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import java.util.Date

/**
 * Tag business logic
 * @author Simpli CLI generator
 */
class TagProcess(val context: RequestContext) {

    val dao = TagDao(context.con)

    fun get(id: Long?): Tag {
        // TODO: review generated method
        if (id == null) throw BadRequestException()

        val tagPrincipalDao = TagPrincipalDao(context.con)

        val model = dao.getOne(id) ?: throw NotFoundException()
        model.tagPrincipal = tagPrincipalDao.listPrincipalOfTag(id)

        return model
    }

    fun list(filter: ListFilter): PageCollection<Tag> {
        // TODO: review generated method
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    /**
     * Use this to handle similarities between create and update
     */
    fun persist(model: Tag): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        val tagPrincipalDao = TagPrincipalDao(context.con)

        tagPrincipalDao.removeAllFromTag(model.id)

        model.tagPrincipal?.let { list ->
            list.forEach { tagPrincipalDao.insert(model.id, it.id) }
        }

        return model.id
    }

    fun create(model: Tag): Long {
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

    fun update(model: Tag): Int {
        // TODO: review generated method
        model.apply {
            validate(context.lang)
        }

        return dao.run {
            validate(model, updating = true)
            update(model)
        }
    }

    private fun TagDao.validate(model: Tag, updating: Boolean) {
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

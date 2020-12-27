package org.usecase.process

import org.usecase.context.RequestContext
import org.usecase.dao.TagDao
import org.usecase.dao.TagPrincipalDao
import org.usecase.model.filter.TagListFilter
import org.usecase.model.resource.Tag
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.model.resource.TagPrincipal
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

/**
 * Tag business logic
 * @author Simpli CLI generator
 */
class TagProcess(val context: RequestContext) {

    val dao = TagDao(context.con, context.permission)

    fun get(id: Long?): Tag {
        if (id == null) throw BadRequestException()

        val model = dao.getOne(id) ?: throw NotFoundException()

        return model.apply {
            tagPrincipal = TagPrincipalDao(context.con, context.permission).listPrincipalOfTag(id)
        }
    }

    fun list(filter: TagListFilter): PageCollection<Tag> {
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    fun persist(model: Tag): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        TagPrincipalDao(context.con, context.permission).apply {
            removeAllFromTag(model.id)

            model.tagPrincipal?.forEach {
                val tagPrincipal = TagPrincipal().apply {
                    idTagFk = model.id
                    idPrincipalFk = it.id
                }
                insert(tagPrincipal)
            }
        }

        return model.id
    }

    fun create(model: Tag): Long {
        validateTag(model, updating = false)

        model.id = dao.insert(model)

        return model.id
    }

    fun update(model: Tag): Int {
        validateTag(model, updating = true)

        return dao.update(model)
    }

    fun validateTag(model: Tag, updating: Boolean) {
        context.lang.handleValidation("modelTag") {
            validate(model) {
                validate(Tag::titulo).isNotBlank().hasSize(max = 45)
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

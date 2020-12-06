package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.TagDao
import org.usecase.dao.TagPrincipalDao
import org.usecase.model.filter.TagListFilter
import org.usecase.model.resource.Tag
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.model.resource.TagPrincipal
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.TAG_INSERT_ALL
import org.usecase.user.context.Permission.Companion.TAG_PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.TAG_READ_ALL
import org.usecase.user.context.Permission.Companion.TAG_UPDATE_ALL
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.validate
import java.util.Date

/**
 * Tag business logic
 * @author Simpli CLI generator
 */
class TagProcess(val context: RequestContext) {

    val dao = TagDao(context.con)

    fun get(id: Long?): Tag {
        if (id == null) throw BadRequestException()

        val tagPrincipalDao = TagPrincipalDao(context.con)

        val permission = Permission(TAG_READ_ALL, PRINCIPAL_READ_ALL)

        val model = dao.getOne(id, permission) ?: throw NotFoundException()
        model.tagPrincipal = tagPrincipalDao.listPrincipalOfTag(id, permission)

        return model
    }

    fun list(filter: TagListFilter): PageCollection<Tag> {
        val permission = Permission(TAG_READ_ALL)

        val items = dao.getList(filter, permission)
        val total = dao.count(filter, permission)

        return PageCollection(items, total)
    }

    fun persist(model: Tag): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        val tagPrincipalDao = TagPrincipalDao(context.con)

        val permission = Permission(TAG_PRINCIPAL_INSERT_ALL)

        tagPrincipalDao.removeAllFromTag(model.id, permission)

        model.tagPrincipal?.let { list ->
            list.forEach {
                val tagPrincipal = TagPrincipal().apply {
                    idTagFk = model.id
                    idPrincipalFk = it.id
                }
                tagPrincipalDao.insert(tagPrincipal, permission)
            }
        }

        return model.id
    }

    fun create(model: Tag): Long {
        val permission = Permission(TAG_READ_ALL, TAG_INSERT_ALL)
        validateTag(permission, model, updating = false)

        model.id = dao.insert(model, permission)

        return model.id
    }

    fun update(model: Tag): Int {
        val permission = Permission(TAG_READ_ALL, TAG_UPDATE_ALL)
        validateTag(permission, model, updating = true)

        return dao.update(model, permission)
    }

    fun validateTag(permission: Permission, model: Tag, updating: Boolean) {
        context.lang.handleValidation("modelTag") {
            validate(model) {
                validate(Tag::titulo).isNotBlank().hasSize(max = 45)
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

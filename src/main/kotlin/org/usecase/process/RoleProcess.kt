package org.usecase.process

import org.usecase.context.RequestContext
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.dao.RoleDao
import org.usecase.dao.RolePermissionDao
import org.usecase.dao.UserRoleDao
import org.usecase.model.filter.RoleListFilter
import org.usecase.model.resource.*
import org.valiktor.functions.*
import org.valiktor.validate

/**
 * Role business logic
 * @author Simpli CLI generator
 */
class RoleProcess(val context: RequestContext) {

    val dao = RoleDao(context.con, context.permission)

    fun get(id: Long?): Role {
        if (id == null) throw BadRequestException()

        val model = dao.getOne(id) ?: throw NotFoundException()

        return model.apply {
            permissions = RolePermissionDao(context.con, context.permission).listPermissionOfRole(id)
            users = UserRoleDao(context.con, context.permission).listUserOfRole(id)
        }
    }

    fun list(filter: RoleListFilter): PageCollection<Role> {
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    fun persist(model: Role): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        RolePermissionDao(context.con, context.permission).apply {
            removeAllFromRole(model.id)

            model.permissions?.forEach {
                val permission = RolePermission().apply {
                    idRoleFk = model.id
                    idPermissionFk = it.id
                }
                insert(permission)
            }
        }

        UserRoleDao(context.con, context.permission).apply {
            removeAllFromRole(model.id)

            model.users?.forEach {
                val user = UserRole().apply {
                    idRoleFk = model.id
                    idUserFk = it.id
                }
                insert(user)
            }
        }

        return model.id
    }

    fun create(model: Role): Long {
        model.apply {
            active = true
        }

        validateRole(model, updating = false)
        model.id = dao.insert(model)

        return model.id
    }

    fun update(model: Role): Int {
        model.apply {
            active = true
        }

        validateRole(model, updating = true)
        return dao.update(model)
    }

    fun remove(id: Long?): Long {
        if (id == null) throw BadRequestException()

        val affectedRows = dao.softDelete(id)
        if (affectedRows == 0) throw NotFoundException()

        return id
    }

    fun validateRole(model: Role, updating: Boolean) {
        context.lang.handleValidation("modelRole") {
            validate(model) {
                validate(Role::slug).isNotBlank().hasSize(max = 127)
                validate(Role::name).isNotBlank().hasSize(max = 127)
                validate(Role::description).hasSize(max = 255)
                validate(Role::level).isLessThan(100)
            }
        }

        model.slug?.also {
            if (dao.existSlug(it, model.id)) {
                throw BadRequestException("${context.lang["modelRole.slug"]}: ${context.lang["error.alreadyExist"]}")
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

package org.usecase.process

import org.usecase.context.RequestContext
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.dao.PermissionDao
import org.usecase.dao.PermissionPermissionDao
import org.usecase.dao.RolePermissionDao
import org.usecase.dao.UserPermissionDao
import org.usecase.model.filter.PermissionListFilter
import org.usecase.model.resource.*
import org.valiktor.functions.*
import org.valiktor.validate

/**
 * Permission business logic
 * @author Simpli CLI generator
 */
class PermissionProcess(val context: RequestContext) {

    val dao = PermissionDao(context.con, context.permission)

    fun get(id: Long?): Permission {
        if (id == null) throw BadRequestException()

        val model = dao.getOne(id) ?: throw NotFoundException()

        return model.apply {
            permissions = PermissionPermissionDao(context.con, context.permission).listPermissionChildOfPermissionParent(id)
            roles = RolePermissionDao(context.con, context.permission).listRoleOfPermission(id)
            users = UserPermissionDao(context.con, context.permission).listUserOfPermission(id)
        }
    }

    fun list(filter: PermissionListFilter): PageCollection<Permission> {
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    fun persist(model: Permission): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        PermissionPermissionDao(context.con, context.permission).apply {
            removeAllFromPermission(model.id)

            model.permissions?.forEach {
                val permission = PermissionPermission().apply {
                    idPermissionParentFk = model.id
                    idPermissionChildFk = it.id
                }
                insert(permission)
            }
        }

        RolePermissionDao(context.con, context.permission).apply {
            removeAllFromPermission(model.id)

            model.roles?.forEach {
                val role = RolePermission().apply {
                    idPermissionFk = model.id
                    idRoleFk = it.id
                }
                insert(role)
            }
        }

        UserPermissionDao(context.con, context.permission).apply {
            removeAllFromPermission(model.id)

            model.users?.forEach {
                val user = UserPermission().apply {
                    idPermissionFk = model.id
                    idUserFk = it.id
                }
                insert(user)
            }
        }

        return model.id
    }

    fun create(model: Permission): Long {
        model.apply {
            active = true
        }

        validatePermission(model, updating = false)
        model.id = dao.insert(model)

        return model.id
    }

    fun update(model: Permission): Int {
        model.apply {
            active = true
        }

        validatePermission(model, updating = true)
        return dao.update(model)
    }

    fun remove(id: Long?): Long {
        if (id == null) throw BadRequestException()

        val affectedRows = dao.softDelete(id)
        if (affectedRows == 0) throw NotFoundException()

        return id
    }

    fun validatePermission(model: Permission, updating: Boolean) {
        context.lang.handleValidation("modelPermission") {
            validate(model) {
                validate(Permission::scope).isNotBlank().hasSize(max = 127)
                validate(Permission::name).isNotBlank().hasSize(max = 127)
                validate(Permission::description).hasSize(max = 255)
            }
        }

        model.scope?.also {
            if (dao.existScope(it, model.id)) {
                throw BadRequestException("${context.lang["modelPermission.scope"]}: ${context.lang["error.alreadyExist"]}")
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

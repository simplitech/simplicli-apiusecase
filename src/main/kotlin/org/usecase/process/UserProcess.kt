package org.usecase.process

import org.usecase.context.RequestContext
import org.usecase.dao.UserDao
import org.usecase.model.filter.UserListFilter
import org.usecase.model.resource.User
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.dao.UserPermissionDao
import org.usecase.dao.UserRoleDao
import org.usecase.model.resource.UserPermission
import org.usecase.model.resource.UserRole
import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

/**
 * User business logic
 * @author Simpli CLI generator
 */
class UserProcess(val context: RequestContext) {

    val dao = UserDao(context.con, context.permission)

    fun get(id: Long?): User {
        if (id == null) throw BadRequestException()

        val model = dao.getOne(id) ?: throw NotFoundException()

        return model.apply {
            permissions = UserPermissionDao(context.con, context.permission).listPermissionOfUser(id)
            roles = UserRoleDao(context.con, context.permission).listRoleOfUser(id)
        }
    }

    fun list(filter: UserListFilter): PageCollection<User> {
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    fun persist(model: User): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        UserPermissionDao(context.con, context.permission).apply {
            removeAllFromUser(model.id)

            model.permissions?.forEach {
                val permissions = UserPermission().apply {
                    idUserFk = model.id
                    idPermissionFk = it.id
                }
                insert(permissions)
            }
        }

        UserRoleDao(context.con, context.permission).apply {
            removeAllFromUser(model.id)

            model.roles?.forEach {
                val user = UserRole().apply {
                    idUserFk = model.id
                    idRoleFk = it.id
                }
                insert(user)
            }
        }

        return model.id
    }

    fun create(model: User): Long {
        validateUser(model, updating = false)

        model.id = dao.insert(model)

        return model.id
    }

    fun update(model: User): Int {
        validateUser(model, updating = true)

        return dao.update(model)
    }

    fun validateUser(model: User, updating: Boolean) {
        context.lang.handleValidation("modelUser") {
            validate(model) {
                validate(User::email).isNotBlank().hasSize(max = 45).isEmail()
                validate(User::senha).isNotBlank().hasSize(max = 200)
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

package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.UserDao
import org.usecase.model.filter.UserListFilter
import org.usecase.model.resource.User
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.USER_INSERT_ALL
import org.usecase.user.context.Permission.Companion.USER_READ_ALL
import org.usecase.user.context.Permission.Companion.USER_UPDATE_ALL
import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotBlank
import org.valiktor.validate
import java.util.Date

/**
 * User business logic
 * @author Simpli CLI generator
 */
class UserProcess(val context: RequestContext) {

    val dao = UserDao(context.con)

    fun get(id: Long?): User {
        if (id == null) throw BadRequestException()

        val permission = Permission(USER_READ_ALL)

        return dao.getOne(id, permission) ?: throw NotFoundException()
    }

    fun list(filter: UserListFilter): PageCollection<User> {
        val permission = Permission(USER_READ_ALL)

        val items = dao.getList(filter, permission)
        val total = dao.count(filter, permission)

        return PageCollection(items, total)
    }

    fun persist(model: User): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        return model.id
    }

    fun create(model: User): Long {
        val permission = Permission(USER_READ_ALL, USER_INSERT_ALL)
        validateUser(permission, model, updating = false)

        model.id = dao.insert(model, permission)

        return model.id
    }

    fun update(model: User): Int {
        val permission = Permission(USER_READ_ALL, USER_UPDATE_ALL)
        validateUser(permission, model, updating = true)

        return dao.update(model, permission)
    }

    fun validateUser(permission: Permission, model: User, updating: Boolean) {
        context.lang.handleValidation("modelUser") {
            validate(model) {
                validate(User::email).isNotBlank().hasSize(max = 45).isEmail()
                validate(User::senha).isNotBlank().hasSize(max = 200)
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

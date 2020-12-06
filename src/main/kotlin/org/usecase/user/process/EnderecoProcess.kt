package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.EnderecoDao
import org.usecase.model.filter.EnderecoListFilter
import org.usecase.model.resource.Endereco
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.ENDERECO_INSERT_ALL
import org.usecase.user.context.Permission.Companion.ENDERECO_READ_ALL
import org.usecase.user.context.Permission.Companion.ENDERECO_UPDATE_ALL
import org.valiktor.functions.hasSize
import org.valiktor.validate

/**
 * Endereco business logic
 * @author Simpli CLI generator
 */
class EnderecoProcess(val context: RequestContext) {

    val dao = EnderecoDao(context.con)

    fun get(id: Long?): Endereco {
        if (id == null) throw BadRequestException()

        val permission = Permission(ENDERECO_READ_ALL)
        return dao.getOne(id, permission) ?: throw NotFoundException()
    }

    fun list(filter: EnderecoListFilter): PageCollection<Endereco> {
        val permission = Permission(ENDERECO_READ_ALL)
        val items = dao.getList(filter, permission)
        val total = dao.count(filter, permission)

        return PageCollection(items, total)
    }

    fun persist(model: Endereco): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        return model.id
    }

    fun create(model: Endereco): Long {
        val permission = Permission(ENDERECO_READ_ALL, ENDERECO_INSERT_ALL)
        validateEndereco(permission, model, updating = false)
        model.id = dao.insert(model, permission)

        return model.id
    }

    fun update(model: Endereco): Int {
        val permission = Permission(ENDERECO_READ_ALL, ENDERECO_UPDATE_ALL)
        validateEndereco(permission, model, updating = true)
        return dao.update(model, permission)
    }

    fun validateEndereco(permission: Permission, model: Endereco, updating: Boolean) {
        context.lang.handleValidation("modelEndereco") {
            validate(model) {
                validate(Endereco::cep).hasSize(max = 45)
                validate(Endereco::zipcode).hasSize(max = 45)
                validate(Endereco::rua).hasSize(max = 45)
                validate(Endereco::cidade).hasSize(max = 45)
                validate(Endereco::uf).hasSize(max = 45)
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

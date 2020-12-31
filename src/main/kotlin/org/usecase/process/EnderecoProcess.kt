package org.usecase.process

import org.usecase.context.RequestContext
import org.usecase.dao.EnderecoDao
import org.usecase.model.filter.EnderecoListFilter
import org.usecase.model.resource.Endereco
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.model.resource.Permission
import org.usecase.model.resource.Permission.Companion.ENDERECO_INSERT_ALL
import org.usecase.model.resource.Permission.Companion.ENDERECO_READ_ALL
import org.usecase.model.resource.Permission.Companion.ENDERECO_UPDATE_ALL
import org.valiktor.functions.hasSize
import org.valiktor.validate

/**
 * Endereco business logic
 * @author Simpli CLI generator
 */
class EnderecoProcess(val context: RequestContext) {

    val dao = EnderecoDao(context.con, context.permission)

    fun get(id: Long?): Endereco {
        if (id == null) throw BadRequestException()

        return dao.getOne(id) ?: throw NotFoundException()
    }

    fun list(filter: EnderecoListFilter): PageCollection<Endereco> {
        val items = dao.getList(filter)
        val total = dao.count(filter)

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
        validateEndereco(model, updating = false)
        model.id = dao.insert(model)

        return model.id
    }

    fun update(model: Endereco): Int {
        validateEndereco(model, updating = true)
        return dao.update(model)
    }

    fun validateEndereco(model: Endereco, updating: Boolean) {
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

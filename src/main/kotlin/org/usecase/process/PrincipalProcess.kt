package org.usecase.process

import org.usecase.context.RequestContext
import org.usecase.dao.PrincipalDao
import org.usecase.dao.TagPrincipalDao
import org.usecase.model.filter.PrincipalListFilter
import org.usecase.model.resource.Principal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.model.resource.TagPrincipal
import org.usecase.extension.isCNPJ
import org.usecase.extension.isCPF
import org.valiktor.functions.*
import org.valiktor.validate
import java.util.Date

/**
 * Principal business logic
 * @author Simpli CLI generator
 */
class PrincipalProcess(val context: RequestContext) {

    val dao = PrincipalDao(context.con, context.permission)

    fun get(id: Long?): Principal {
        if (id == null) throw BadRequestException()

        val model = dao.getOne(id) ?: throw NotFoundException()

        return model.apply {
            tagPrincipal = TagPrincipalDao(context.con, context.permission).listTagOfPrincipal(id)
        }
    }

    fun list(filter: PrincipalListFilter): PageCollection<Principal> {
        val items = dao.getList(filter)
        val total = dao.count(filter)

        return PageCollection(items, total)
    }

    fun persist(model: Principal): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        TagPrincipalDao(context.con, context.permission).apply {
            removeAllFromPrincipal(model.id)

            model.tagPrincipal?.forEach {
                val tagPrincipal = TagPrincipal().apply {
                    idPrincipalFk = model.id
                    idTagFk = it.id
                }
                insert(tagPrincipal)
            }
        }

        return model.id
    }

    fun create(model: Principal): Long {
        model.apply {
            ativo = true
            dataCriacao = Date()
        }

        validatePrincipal(model, updating = false)
        model.id = dao.insert(model)

        return model.id
    }

    fun update(model: Principal): Int {
        model.apply {
            ativo = true
            dataAlteracao = Date()
        }

        validatePrincipal(model, updating = true)
        return dao.update(model)
    }

    fun remove(id: Long?): Long {
        if (id == null) throw BadRequestException()

        val affectedRows = dao.softDelete(id)
        if (affectedRows == 0) throw NotFoundException()

        return id
    }

    fun validatePrincipal(model: Principal, updating: Boolean) {
        context.lang.handleValidation("modelPrincipal") {
            validate(model) {
                validate(Principal::textoObrigatorio).isNotBlank().hasSize(max = 160)
                validate(Principal::textoFacultativo).hasSize(max = 45)
                validate(Principal::inteiroObrigatorio).isGreaterThan(0)
                validate(Principal::dataObrigatoria).isNotNull()
                validate(Principal::datahoraObrigatoria).isNotNull()
                validate(Principal::email).isNotBlank().isEmail().hasSize(max = 200)
                validate(Principal::senha).hasSize(max = 200)
                validate(Principal::urlImagem).hasSize(max = 200)
                validate(Principal::url).hasSize(max = 200)
                validate(Principal::idGrupoDoPrincipalFk).isGreaterThan(0)
                validate(Principal::unico).isNotBlank().hasSize(max = 40)
                validate(Principal::dataCriacao).isNotNull()
                validate(Principal::nome).hasSize(max = 45)
                validate(Principal::titulo).hasSize(max = 45)
                validate(Principal::cpf).isNotBlank().hasSize(max = 45).isCPF()
                validate(Principal::cnpj).isNotBlank().hasSize(max = 45).isCNPJ()
                validate(Principal::rg).hasSize(max = 45)
                validate(Principal::celular).hasSize(max = 45)
                validate(Principal::textoGrande).hasSize(max = 300)
                validate(Principal::snakeCase).hasSize(max = 200)
            }
        }

        model.unico?.also {
            if (dao.existUnico(it, model.id)) {
                throw BadRequestException("${context.lang["modelPrincipal.unico"]}: ${context.lang["error.alreadyExist"]}")
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

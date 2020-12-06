package org.usecase.user.process

import org.usecase.user.context.RequestContext
import org.usecase.dao.PrincipalDao
import org.usecase.dao.TagPrincipalDao
import org.usecase.model.filter.PrincipalListFilter
import org.usecase.model.resource.Principal
import org.usecase.exception.response.BadRequestException
import org.usecase.exception.response.NotFoundException
import br.com.simpli.model.PageCollection
import org.usecase.model.resource.TagPrincipal
import org.usecase.user.context.Permission
import org.usecase.user.context.Permission.Companion.GRUPO_DO_PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.PRINCIPAL_INSERT_ALL
import org.usecase.user.context.Permission.Companion.PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.PRINCIPAL_UPDATE_ALL
import org.usecase.user.context.Permission.Companion.TAG_PRINCIPAL_INSERT_ALL
import org.usecase.extension.isCNPJ
import org.usecase.extension.isCPF
import org.usecase.user.context.Permission.Companion.TAG_PRINCIPAL_READ_ALL
import org.usecase.user.context.Permission.Companion.TAG_READ_ALL
import org.valiktor.functions.*
import org.valiktor.validate
import java.util.Date

/**
 * Principal business logic
 * @author Simpli CLI generator
 */
class PrincipalProcess(val context: RequestContext) {

    val dao = PrincipalDao(context.con)

    fun get(id: Long?): Principal {
        if (id == null) throw BadRequestException()

        val permission = Permission(PRINCIPAL_READ_ALL, GRUPO_DO_PRINCIPAL_READ_ALL, TAG_PRINCIPAL_READ_ALL, TAG_READ_ALL)

        val tagPrincipalDao = TagPrincipalDao(context.con)

        val model = dao.getOne(id, permission) ?: throw NotFoundException()
        model.tagPrincipal = tagPrincipalDao.listTagOfPrincipal(id, permission)

        return model
    }

    fun list(filter: PrincipalListFilter): PageCollection<Principal> {
        val permission = Permission(PRINCIPAL_READ_ALL, GRUPO_DO_PRINCIPAL_READ_ALL)

        val items = dao.getList(filter, permission)
        val total = dao.count(filter, permission)

        return PageCollection(items, total)
    }

    fun persist(model: Principal): Long {
        if (model.id > 0) {
            update(model)
        } else {
            create(model)
        }

        val tagPrincipalDao = TagPrincipalDao(context.con)

        val permission = Permission(TAG_PRINCIPAL_INSERT_ALL)

        tagPrincipalDao.removeAllFromPrincipal(model.id, permission)

        model.tagPrincipal?.forEach {
            val tagPrincipal = TagPrincipal().apply {
                idPrincipalFk = model.id
                idTagFk = it.id
            }
            tagPrincipalDao.insert(tagPrincipal, permission)
        }

        return model.id
    }

    fun create(model: Principal): Long {
        model.apply {
            ativo = true
            dataCriacao = Date()
        }

        val permission = Permission(PRINCIPAL_READ_ALL, PRINCIPAL_INSERT_ALL)

        validatePrincipal(permission, model, updating = false)
        model.id = dao.insert(model, permission)

        return model.id
    }

    fun update(model: Principal): Int {
        model.apply {
            ativo = true
            dataAlteracao = Date()
        }

        val permission = Permission(PRINCIPAL_READ_ALL, PRINCIPAL_UPDATE_ALL)

        validatePrincipal(permission, model, updating = true)
        return dao.update(model, permission)
    }

    fun remove(id: Long?): Long {
        if (id == null) throw BadRequestException()

        val affectedRows = dao.softDelete(id, Permission())
        if (affectedRows == 0) throw NotFoundException()

        return id
    }

    fun validatePrincipal(permission: Permission, model: Principal, updating: Boolean) {
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
            if (dao.existUnico(it, model.id, permission)) {
                throw BadRequestException("${context.lang["modelPrincipal.unico"]}: ${context.lang["error.alreadyExist"]}")
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

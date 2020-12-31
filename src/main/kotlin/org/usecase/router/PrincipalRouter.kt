package org.usecase.router

import org.usecase.context.Pipe
import org.usecase.process.PrincipalProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.PrincipalListParam
import org.usecase.model.resource.Principal
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import org.usecase.model.resource.Permission
import org.usecase.policy.PrincipalPolicy
import javax.ws.rs.BeanParam
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType

/**
 * Routing the API address into Principal Process
 * @author Simpli CLI generator
 */
@Path("/principal")
@Produces(MediaType.APPLICATION_JSON)
class PrincipalRouter : RouterWrapper() {
    @GET
    @Path("/{id}")
    @Operation(tags = ["Principal"], summary = "Gets a instance of a given ID of Principal")
    fun getPrincipal(@BeanParam param: DefaultParam.RequiredPathId): Principal {
        return Pipe().handle(readPipe, param) { context ->
            PrincipalPolicy(context).read()
            PrincipalProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["Principal"], summary = "Lists the instances of Principal")
    fun listPrincipal(@BeanParam param: PrincipalListParam): PageCollection<Principal> {
        return Pipe().handle(readPipe, param) { context ->
            PrincipalPolicy(context).read()
            PrincipalProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["Principal"], summary = "Lists the instances of Principal to export as a file")
    fun listExportPrincipal(@BeanParam param: PrincipalListParam): PageCollection<Principal> {
        return Pipe().handle(readPipe, param) { context ->
            PrincipalPolicy(context).read()
            PrincipalProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["Principal"], summary = "Persists a new instance of Principal. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistPrincipal(@BeanParam param: DefaultParam, model: Principal): Long {
        return Pipe().handle(transactionPipe, param) { context ->
            PrincipalPolicy(context).persist(model)
            PrincipalProcess(context).persist(model)
		}
    }

    @DELETE
    @Path("/{id}")
    @Operation(tags = ["Principal"], summary = "Deletes a instance of a given ID of Principal")
    fun removePrincipal(@BeanParam param: DefaultParam.RequiredPathId): Long {
        val permission = Permission.groupOf(Permission.PRINCIPAL_UPDATE_ATIVO)

        return Pipe(permission).handle(transactionPipe, param) { context ->
            PrincipalPolicy(context).delete()
            PrincipalProcess(context).remove(param.id)
		}
    }
}

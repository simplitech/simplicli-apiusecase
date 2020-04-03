package org.usecase.user.router

import org.usecase.user.context.AuthPipe
import org.usecase.user.process.PrincipalProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.resource.Principal
import org.usecase.param.DefaultParam
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import org.usecase.param.AuthPrincipalListFilter
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
@Path("/user/principal")
@Produces(MediaType.APPLICATION_JSON)
class PrincipalRouter : RouterWrapper() {

    @GET
    @Path("/{id}")
    @Operation(tags = ["Principal"], summary = "Gets a instance of a given ID of Principal")
    fun getPrincipal(@BeanParam param: DefaultParam.RequiredPathId): Principal {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            PrincipalProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["Principal"], summary = "Lists the instances of Principal")
    fun listPrincipal(@BeanParam param: AuthPrincipalListFilter): PageCollection<Principal> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            PrincipalProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["Principal"], summary = "Lists the instances of Principal to export as a file")
    fun listExportPrincipal(@BeanParam param: AuthPrincipalListFilter): PageCollection<Principal> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            PrincipalProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["Principal"], summary = "Persists a new instance of Principal. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistPrincipal(@BeanParam param: DefaultParam.Auth, model: Principal): Long {
        // TODO: review generated method
        return AuthPipe.handle(transactionPipe, param) { context, _ ->
            PrincipalProcess(context).persist(model)
		}
    }

    @DELETE
    @Path("/{id}")
    @Operation(tags = ["Principal"], summary = "Deletes a instance of a given ID of Principal")
    fun removePrincipal(@BeanParam param: DefaultParam.RequiredPathId): Long {
        // TODO: review generated method
        return AuthPipe.handle(transactionPipe, param) { context, _ ->
            PrincipalProcess(context).remove(param.id)
		}
    }

}

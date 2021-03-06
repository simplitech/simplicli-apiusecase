package org.usecase.user.router

import org.usecase.user.context.AuthPipe
import org.usecase.user.process.ExtensaoDoPrincipalProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.AuthExtensaoDoPrincipalListParam
import org.usecase.model.resource.ExtensaoDoPrincipal
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import javax.ws.rs.BeanParam
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.core.MediaType

/**
 * Routing the API address into ExtensaoDoPrincipal Process
 * @author Simpli CLI generator
 */
@Path("/user/extensao-do-principal")
@Produces(MediaType.APPLICATION_JSON)
class ExtensaoDoPrincipalRouter : RouterWrapper() {
    @GET
    @Path("/{id}")
    @Operation(tags = ["ExtensaoDoPrincipal"], summary = "Gets a instance of a given ID of ExtensaoDoPrincipal")
    fun getExtensaoDoPrincipal(@BeanParam param: DefaultParam.RequiredPathId): ExtensaoDoPrincipal {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            ExtensaoDoPrincipalProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["ExtensaoDoPrincipal"], summary = "Lists the instances of ExtensaoDoPrincipal")
    fun listExtensaoDoPrincipal(@BeanParam param: AuthExtensaoDoPrincipalListParam): PageCollection<ExtensaoDoPrincipal> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            ExtensaoDoPrincipalProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["ExtensaoDoPrincipal"], summary = "Lists the instances of ExtensaoDoPrincipal to export as a file")
    fun listExportExtensaoDoPrincipal(@BeanParam param: AuthExtensaoDoPrincipalListParam): PageCollection<ExtensaoDoPrincipal> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            ExtensaoDoPrincipalProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["ExtensaoDoPrincipal"], summary = "Persists a new instance of ExtensaoDoPrincipal. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistExtensaoDoPrincipal(@BeanParam param: DefaultParam.Auth, model: ExtensaoDoPrincipal): Long {
        // TODO: review generated method
        return AuthPipe.handle(transactionPipe, param) { context, _ ->
            ExtensaoDoPrincipalProcess(context).persist(model)
		}
    }
}

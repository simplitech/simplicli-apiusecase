package org.usecase.router

import org.usecase.context.Pipe
import org.usecase.process.ExtensaoDoPrincipalProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.ExtensaoDoPrincipalListParam
import org.usecase.model.resource.ExtensaoDoPrincipal
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import org.usecase.policy.ExtensaoDoPrincipalPolicy
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType

/**
 * Routing the API address into ExtensaoDoPrincipal Process
 * @author Simpli CLI generator
 */
@Path("/extensao-do-principal")
@Produces(MediaType.APPLICATION_JSON)
class ExtensaoDoPrincipalRouter : RouterWrapper() {
    @GET
    @Path("/{id}")
    @Operation(tags = ["ExtensaoDoPrincipal"], summary = "Gets a instance of a given ID of ExtensaoDoPrincipal")
    fun getExtensaoDoPrincipal(@BeanParam param: DefaultParam.RequiredPathId): ExtensaoDoPrincipal {
        return Pipe().handle(readPipe, param) { context ->
            ExtensaoDoPrincipalPolicy(context).read()
            ExtensaoDoPrincipalProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["ExtensaoDoPrincipal"], summary = "Lists the instances of ExtensaoDoPrincipal")
    fun listExtensaoDoPrincipal(@BeanParam param: ExtensaoDoPrincipalListParam): PageCollection<ExtensaoDoPrincipal> {
        return Pipe().handle(readPipe, param) { context ->
            ExtensaoDoPrincipalPolicy(context).read()
            ExtensaoDoPrincipalProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["ExtensaoDoPrincipal"], summary = "Lists the instances of ExtensaoDoPrincipal to export as a file")
    fun listExportExtensaoDoPrincipal(@BeanParam param: ExtensaoDoPrincipalListParam): PageCollection<ExtensaoDoPrincipal> {
        return Pipe().handle(readPipe, param) { context ->
            ExtensaoDoPrincipalPolicy(context).read()
            ExtensaoDoPrincipalProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["ExtensaoDoPrincipal"], summary = "Persists a new instance of ExtensaoDoPrincipal. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistExtensaoDoPrincipal(@BeanParam param: DefaultParam, model: ExtensaoDoPrincipal): Long {
        return Pipe().handle(transactionPipe, param) { context ->
            ExtensaoDoPrincipalPolicy(context).persist(model)
            ExtensaoDoPrincipalProcess(context).persist(model)
		}
    }
}

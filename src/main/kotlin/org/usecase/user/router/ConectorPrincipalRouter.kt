package org.usecase.user.router

import org.usecase.user.context.AuthPipe
import org.usecase.user.process.ConectorPrincipalProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.ConectorPrincipalListParam
import org.usecase.model.resource.ConectorPrincipal
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType

/**
 * Routing the API address into ConectorPrincipal Process
 * @author Simpli CLI generator
 */
@Path("/user/conector-principal")
@Produces(MediaType.APPLICATION_JSON)
class ConectorPrincipalRouter : RouterWrapper() {
    @GET
    @Path("/{idPrincipalFk}/{idConectadoFk}")
    @Operation(tags = ["ConectorPrincipal"], summary = "Gets a instance of a given ID of ConectorPrincipal")
    fun getConectorPrincipal(@BeanParam param: ConectorPrincipal.RequiredPathId): ConectorPrincipal {
        return AuthPipe().handle(readPipe, param) { context ->
            ConectorPrincipalProcess(context).get(param.idPrincipalFk, param.idConectadoFk)
		}
    }

    @GET
    @Operation(tags = ["ConectorPrincipal"], summary = "Lists the instances of ConectorPrincipal")
    fun listConectorPrincipal(@BeanParam param: ConectorPrincipalListParam): PageCollection<ConectorPrincipal> {
        return AuthPipe().handle(readPipe, param) { context ->
            ConectorPrincipalProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["ConectorPrincipal"], summary = "Lists the instances of ConectorPrincipal to export as a file")
    fun listExportConectorPrincipal(@BeanParam param: ConectorPrincipalListParam): PageCollection<ConectorPrincipal> {
        return AuthPipe().handle(readPipe, param) { context ->
            ConectorPrincipalProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["ConectorPrincipal"], summary = "Persists a new instance of ConectorPrincipal. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistConectorPrincipal(@BeanParam param: DefaultParam, model: ConectorPrincipal): Long {
        return AuthPipe().handle(transactionPipe, param) { context ->
            ConectorPrincipalProcess(context).persist(model)
		}
    }
}

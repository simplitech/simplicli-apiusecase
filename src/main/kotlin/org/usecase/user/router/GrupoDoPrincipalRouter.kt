package org.usecase.user.router

import org.usecase.user.context.AuthPipe
import org.usecase.user.process.GrupoDoPrincipalProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.GrupoDoPrincipalListParam
import org.usecase.model.resource.GrupoDoPrincipal
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType

/**
 * Routing the API address into GrupoDoPrincipal Process
 * @author Simpli CLI generator
 */
@Path("/user/grupo-do-principal")
@Produces(MediaType.APPLICATION_JSON)
class GrupoDoPrincipalRouter : RouterWrapper() {
    @GET
    @Path("/{id}")
    @Operation(tags = ["GrupoDoPrincipal"], summary = "Gets a instance of a given ID of GrupoDoPrincipal")
    fun getGrupoDoPrincipal(@BeanParam param: DefaultParam.RequiredPathId): GrupoDoPrincipal {
        return AuthPipe().handle(readPipe, param) { context ->
            GrupoDoPrincipalProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["GrupoDoPrincipal"], summary = "Lists the instances of GrupoDoPrincipal")
    fun listGrupoDoPrincipal(@BeanParam param: GrupoDoPrincipalListParam): PageCollection<GrupoDoPrincipal> {
        return AuthPipe().handle(readPipe, param) { context ->
            GrupoDoPrincipalProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["GrupoDoPrincipal"], summary = "Lists the instances of GrupoDoPrincipal to export as a file")
    fun listExportGrupoDoPrincipal(@BeanParam param: GrupoDoPrincipalListParam): PageCollection<GrupoDoPrincipal> {
        return AuthPipe().handle(readPipe, param) { context ->
            GrupoDoPrincipalProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["GrupoDoPrincipal"], summary = "Persists a new instance of GrupoDoPrincipal. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistGrupoDoPrincipal(@BeanParam param: DefaultParam, model: GrupoDoPrincipal): Long {
        return AuthPipe().handle(transactionPipe, param) { context ->
            GrupoDoPrincipalProcess(context).persist(model)
		}
    }
}

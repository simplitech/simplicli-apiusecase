package org.usecase.router

import org.usecase.context.Pipe
import org.usecase.process.ConectadoProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.ConectadoListParam
import org.usecase.model.resource.Conectado
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import org.usecase.policy.ConectadoPolicy
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType

/**
 * Routing the API address into Conectado Process
 * @author Simpli CLI generator
 */
@Path("/conectado")
@Produces(MediaType.APPLICATION_JSON)
class ConectadoRouter : RouterWrapper() {
    @GET
    @Path("/{id}")
    @Operation(tags = ["Conectado"], summary = "Gets a instance of a given ID of Conectado")
    fun getConectado(@BeanParam param: DefaultParam.RequiredPathId): Conectado {
        return Pipe().handle(readPipe, param) { context ->
            ConectadoPolicy(context).read()
            ConectadoProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["Conectado"], summary = "Lists the instances of Conectado")
    fun listConectado(@BeanParam param: ConectadoListParam): PageCollection<Conectado> {
        return Pipe().handle(readPipe, param) { context ->
            ConectadoPolicy(context).read()
            ConectadoProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["Conectado"], summary = "Lists the instances of Conectado to export as a file")
    fun listExportConectado(@BeanParam param: ConectadoListParam): PageCollection<Conectado> {
        return Pipe().handle(readPipe, param) { context ->
            ConectadoPolicy(context).read()
            ConectadoProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["Conectado"], summary = "Persists a new instance of Conectado. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistConectado(@BeanParam param: DefaultParam, model: Conectado): Long {
        return Pipe().handle(transactionPipe, param) { context ->
            ConectadoPolicy(context).persist(model)
            ConectadoProcess(context).persist(model)
		}
    }
}

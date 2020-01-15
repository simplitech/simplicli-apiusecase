package org.usecase.user.router

import org.usecase.user.context.AuthPipe
import org.usecase.user.process.ConectadoProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.resource.Conectado
import org.usecase.param.DefaultParam
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
 * Routing the API address into Conectado Process
 * @author Simpli CLI generator
 */
@Path("/user/conectado")
@Produces(MediaType.APPLICATION_JSON)
class ConectadoRouter : RouterWrapper() {

    @GET
    @Path("/{id}")
    @Operation(tags = ["Conectado"], summary = "Gets a instance of a given ID of Conectado")
    fun getConectado(@BeanParam param: DefaultParam.RequiredPathId): Conectado {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            ConectadoProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["Conectado"], summary = "Lists the instances of Conectado")
    fun listConectado(@BeanParam param: DefaultParam.AuthPaged): PageCollection<Conectado> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            ConectadoProcess(context).list(param)
		}
    }

    @GET
    @Path("/csv")
    @Operation(tags = ["Conectado"], summary = "Lists the instances of Conectado to use it in a CSV file")
    fun listCsvConectado(@BeanParam param: DefaultParam.AuthPaged): PageCollection<Conectado> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            ConectadoProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["Conectado"], summary = "Persists a new instance of Conectado. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistConectado(@BeanParam param: DefaultParam.Auth, model: Conectado): Long {
        // TODO: review generated method
        return AuthPipe.handle(transactionPipe, param) { context, _ ->
            ConectadoProcess(context).persist(model)
		}
    }

}

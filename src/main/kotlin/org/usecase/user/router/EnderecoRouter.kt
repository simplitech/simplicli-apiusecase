package org.usecase.user.router

import org.usecase.user.context.AuthPipe
import org.usecase.user.process.EnderecoProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.AuthEnderecoListParam
import org.usecase.model.resource.Endereco
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
 * Routing the API address into Endereco Process
 * @author Simpli CLI generator
 */
@Path("/user/endereco")
@Produces(MediaType.APPLICATION_JSON)
class EnderecoRouter : RouterWrapper() {
    @GET
    @Path("/{id}")
    @Operation(tags = ["Endereco"], summary = "Gets a instance of a given ID of Endereco")
    fun getEndereco(@BeanParam param: DefaultParam.RequiredPathId): Endereco {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            EnderecoProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["Endereco"], summary = "Lists the instances of Endereco")
    fun listEndereco(@BeanParam param: AuthEnderecoListParam): PageCollection<Endereco> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            EnderecoProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["Endereco"], summary = "Lists the instances of Endereco to export as a file")
    fun listExportEndereco(@BeanParam param: AuthEnderecoListParam): PageCollection<Endereco> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            EnderecoProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["Endereco"], summary = "Persists a new instance of Endereco. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistEndereco(@BeanParam param: DefaultParam.Auth, model: Endereco): Long {
        // TODO: review generated method
        return AuthPipe.handle(transactionPipe, param) { context, _ ->
            EnderecoProcess(context).persist(model)
		}
    }
}

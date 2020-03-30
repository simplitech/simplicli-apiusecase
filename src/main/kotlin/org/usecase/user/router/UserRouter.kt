package org.usecase.user.router

import org.usecase.user.context.AuthPipe
import org.usecase.user.process.UserProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.resource.User
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
 * Routing the API address into User Process
 * @author Simpli CLI generator
 */
@Path("/user/user")
@Produces(MediaType.APPLICATION_JSON)
class UserRouter : RouterWrapper() {

    @GET
    @Path("/{id}")
    @Operation(tags = ["User"], summary = "Gets a instance of a given ID of User")
    fun getUser(@BeanParam param: DefaultParam.RequiredPathId): User {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            UserProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["User"], summary = "Lists the instances of User")
    fun listUser(@BeanParam param: DefaultParam.AuthPaged): PageCollection<User> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            UserProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["User"], summary = "Lists the instances of User to export as a file")
    fun listExportUser(@BeanParam param: DefaultParam.AuthPaged): PageCollection<User> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            UserProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["User"], summary = "Persists a new instance of User. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistUser(@BeanParam param: DefaultParam.Auth, model: User): Long {
        // TODO: review generated method
        return AuthPipe.handle(transactionPipe, param) { context, _ ->
            UserProcess(context).persist(model)
		}
    }

}

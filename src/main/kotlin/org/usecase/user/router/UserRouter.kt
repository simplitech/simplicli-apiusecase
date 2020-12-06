package org.usecase.user.router

import org.usecase.user.context.AuthPipe
import org.usecase.user.process.UserProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.UserListParam
import org.usecase.model.resource.User
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.POST
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
        return AuthPipe().handle(readPipe, param) { context ->
            UserProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["User"], summary = "Lists the instances of User")
    fun listUser(@BeanParam param: UserListParam): PageCollection<User> {
        return AuthPipe().handle(readPipe, param) { context ->
            UserProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["User"], summary = "Lists the instances of User to export as a file")
    fun listExportUser(@BeanParam param: UserListParam): PageCollection<User> {
        return AuthPipe().handle(readPipe, param) { context ->
            UserProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["User"], summary = "Persists a new instance of User. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistUser(@BeanParam param: DefaultParam, model: User): Long {
        return AuthPipe().handle(transactionPipe, param) { context ->
            UserProcess(context).persist(model)
		}
    }
}

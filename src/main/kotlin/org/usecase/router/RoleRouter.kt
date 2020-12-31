package org.usecase.router

import org.usecase.context.Pipe
import org.usecase.process.RoleProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.RoleListParam
import org.usecase.model.resource.Role
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import org.usecase.policy.RolePolicy
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType

/**
 * Routing the API address into Role Process
 * @author Simpli CLI generator
 */
@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
class RoleRouter : RouterWrapper() {
    @GET
    @Path("/{id}")
    @Operation(tags = ["Role"], summary = "Gets a instance of a given ID of Role")
    fun getRole(@BeanParam param: DefaultParam.RequiredPathId): Role {
        return Pipe().handle(readPipe, param) { context ->
            RolePolicy(context).read()
            RoleProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["Role"], summary = "Lists the instances of Role")
    fun listRole(@BeanParam param: RoleListParam): PageCollection<Role> {
        return Pipe().handle(readPipe, param) { context ->
            RolePolicy(context).read()
            RoleProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["Role"], summary = "Lists the instances of Role to export as a file")
    fun listExportRole(@BeanParam param: RoleListParam): PageCollection<Role> {
        return Pipe().handle(readPipe, param) { context ->
            RolePolicy(context).read()
            RoleProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["Role"], summary = "Persists a new instance of Role. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistRole(@BeanParam param: DefaultParam, model: Role): Long {
        return Pipe().handle(transactionPipe, param) { context ->
            RolePolicy(context).persist(model)
            RoleProcess(context).persist(model)
		}
    }
}

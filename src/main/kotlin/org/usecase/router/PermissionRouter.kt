package org.usecase.router

import org.usecase.context.Pipe
import org.usecase.process.PermissionProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.PermissionListParam
import org.usecase.model.resource.Permission
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import org.usecase.policy.PermissionPolicy
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType

/**
 * Routing the API address into Permission Process
 * @author Simpli CLI generator
 */
@Path("/permission")
@Produces(MediaType.APPLICATION_JSON)
class PermissionRouter : RouterWrapper() {
    @GET
    @Path("/{id}")
    @Operation(tags = ["Permission"], summary = "Gets a instance of a given ID of Permission")
    fun getPermission(@BeanParam param: DefaultParam.RequiredPathId): Permission {
        return Pipe().handle(readPipe, param) { context ->
            PermissionPolicy(context).read()
            PermissionProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["Permission"], summary = "Lists the instances of Permission")
    fun listPermission(@BeanParam param: PermissionListParam): PageCollection<Permission> {
        return Pipe().handle(readPipe, param) { context ->
            PermissionPolicy(context).read()
            PermissionProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["Permission"], summary = "Lists the instances of Permission to export as a file")
    fun listExportPermission(@BeanParam param: PermissionListParam): PageCollection<Permission> {
        return Pipe().handle(readPipe, param) { context ->
            PermissionPolicy(context).read()
            PermissionProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["Permission"], summary = "Persists a new instance of Permission. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistPermission(@BeanParam param: DefaultParam, model: Permission): Long {
        return Pipe().handle(transactionPipe, param) { context ->
            PermissionPolicy(context).persist(model)
            PermissionProcess(context).persist(model)
		}
    }
}

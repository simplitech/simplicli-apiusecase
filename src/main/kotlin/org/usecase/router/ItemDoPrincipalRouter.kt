package org.usecase.router

import org.usecase.context.Pipe
import org.usecase.process.ItemDoPrincipalProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.ItemDoPrincipalListParam
import org.usecase.model.resource.ItemDoPrincipal
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import org.usecase.policy.ItemDoPrincipalPolicy
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType

/**
 * Routing the API address into ItemDoPrincipal Process
 * @author Simpli CLI generator
 */
@Path("/item-do-principal")
@Produces(MediaType.APPLICATION_JSON)
class ItemDoPrincipalRouter : RouterWrapper() {
    @GET
    @Path("/{id}")
    @Operation(tags = ["ItemDoPrincipal"], summary = "Gets a instance of a given ID of ItemDoPrincipal")
    fun getItemDoPrincipal(@BeanParam param: DefaultParam.RequiredPathId): ItemDoPrincipal {
        return Pipe().handle(readPipe, param) { context ->
            ItemDoPrincipalPolicy(context).read()
            ItemDoPrincipalProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["ItemDoPrincipal"], summary = "Lists the instances of ItemDoPrincipal")
    fun listItemDoPrincipal(@BeanParam param: ItemDoPrincipalListParam): PageCollection<ItemDoPrincipal> {
        return Pipe().handle(readPipe, param) { context ->
            ItemDoPrincipalPolicy(context).read()
            ItemDoPrincipalProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["ItemDoPrincipal"], summary = "Lists the instances of ItemDoPrincipal to export as a file")
    fun listExportItemDoPrincipal(@BeanParam param: ItemDoPrincipalListParam): PageCollection<ItemDoPrincipal> {
        return Pipe().handle(readPipe, param) { context ->
            ItemDoPrincipalPolicy(context).read()
            ItemDoPrincipalProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["ItemDoPrincipal"], summary = "Persists a new instance of ItemDoPrincipal. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistItemDoPrincipal(@BeanParam param: DefaultParam, model: ItemDoPrincipal): Long {
        return Pipe().handle(transactionPipe, param) { context ->
            ItemDoPrincipalPolicy(context).persist(model)
            ItemDoPrincipalProcess(context).persist(model)
		}
    }
}

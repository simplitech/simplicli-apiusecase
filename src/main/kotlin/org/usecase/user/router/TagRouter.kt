package org.usecase.user.router

import org.usecase.user.context.AuthPipe
import org.usecase.user.process.TagProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.resource.Tag
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
 * Routing the API address into Tag Process
 * @author Simpli CLI generator
 */
@Path("/user/tag")
@Produces(MediaType.APPLICATION_JSON)
class TagRouter : RouterWrapper() {

    @GET
    @Path("/{id}")
    @Operation(tags = ["Tag"], summary = "Gets a instance of a given ID of Tag")
    fun getTag(@BeanParam param: DefaultParam.RequiredPathId): Tag {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            TagProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["Tag"], summary = "Lists the instances of Tag")
    fun listTag(@BeanParam param: DefaultParam.AuthPaged): PageCollection<Tag> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            TagProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["Tag"], summary = "Lists the instances of Tag to export as a file")
    fun listExportTag(@BeanParam param: DefaultParam.AuthPaged): PageCollection<Tag> {
        // TODO: review generated method
        return AuthPipe.handle(connectionPipe, param) { context, _ ->
            TagProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["Tag"], summary = "Persists a new instance of Tag. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistTag(@BeanParam param: DefaultParam.Auth, model: Tag): Long {
        // TODO: review generated method
        return AuthPipe.handle(transactionPipe, param) { context, _ ->
            TagProcess(context).persist(model)
		}
    }

}

package org.usecase.router

import org.usecase.context.Pipe
import org.usecase.process.TagProcess
import org.usecase.wrapper.RouterWrapper
import org.usecase.model.param.DefaultParam
import org.usecase.model.param.TagListParam
import org.usecase.model.resource.Tag
import br.com.simpli.model.PageCollection
import io.swagger.v3.oas.annotations.Operation
import org.usecase.policy.TagPolicy
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType

/**
 * Routing the API address into Tag Process
 * @author Simpli CLI generator
 */
@Path("/tag")
@Produces(MediaType.APPLICATION_JSON)
class TagRouter : RouterWrapper() {
    @GET
    @Path("/{id}")
    @Operation(tags = ["Tag"], summary = "Gets a instance of a given ID of Tag")
    fun getTag(@BeanParam param: DefaultParam.RequiredPathId): Tag {
        return Pipe().handle(readPipe, param) { context ->
            TagPolicy(context).read()
            TagProcess(context).get(param.id)
		}
    }

    @GET
    @Operation(tags = ["Tag"], summary = "Lists the instances of Tag")
    fun listTag(@BeanParam param: TagListParam): PageCollection<Tag> {
        return Pipe().handle(readPipe, param) { context ->
            TagPolicy(context).read()
            TagProcess(context).list(param)
		}
    }

    @GET
    @Path("/export")
    @Operation(tags = ["Tag"], summary = "Lists the instances of Tag to export as a file")
    fun listExportTag(@BeanParam param: TagListParam): PageCollection<Tag> {
        return Pipe().handle(readPipe, param) { context ->
            TagPolicy(context).read()
            TagProcess(context).list(param)
		}
    }

    @POST
    @Operation(tags = ["Tag"], summary = "Persists a new instance of Tag. Use ID = 0 to create a new one, or ID > 0 to update a current one")
    fun persistTag(@BeanParam param: DefaultParam, model: Tag): Long {
        return Pipe().handle(transactionPipe, param) { context ->
            TagPolicy(context).persist(model)
            TagProcess(context).persist(model)
		}
    }
}

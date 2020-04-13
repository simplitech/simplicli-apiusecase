package org.usecase.app.healthcheck

import io.swagger.v3.oas.annotations.Operation
import org.usecase.wrapper.RouterWrapper
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path(HealthCheckRouter.PATH)
class HealthCheckRouter : RouterWrapper() {

    companion object {
       const val PATH = "/healthcheck"
    }

    @GET
    @Operation(tags = ["HealthCheck"], summary = "Checks status of DB connection and coroutines")
    fun check(): Boolean {
        HealthCheckPipe.apply {
            return handle(connectionPipe) { con ->
                HealthCheckProcess(con).check()
            } &&
            handle(transactionPipe) { con ->
                HealthCheckProcess(con).check()
            }
        }
    }
}
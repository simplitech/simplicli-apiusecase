package org.usecase.auth

import org.usecase.model.request.AuthRequest
import org.usecase.model.request.ChangePasswordRequest
import org.usecase.model.request.ResetPasswordRequest
import org.usecase.model.request.RecoverPasswordByMailRequest
import org.usecase.model.response.AuthResponse
import org.usecase.model.param.DefaultParam
import org.usecase.wrapper.RouterWrapper
import io.swagger.v3.oas.annotations.Operation
import org.usecase.context.*
import org.usecase.model.resource.Permission
import org.usecase.exception.response.InternalServerErrorException
import org.usecase.model.resource.Permission.Companion.PERMISSION_READ_ALL
import org.usecase.model.resource.Permission.Companion.ROLE_READ_ALL
import org.usecase.model.resource.Permission.Companion.USER_READ_ALL
import org.usecase.model.resource.Permission.Companion.USER_READ_ID_USER_PK
import org.usecase.model.resource.Permission.Companion.USER_UPDATE_SENHA
import org.usecase.model.resource.Role
import javax.ws.rs.BeanParam
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.core.MediaType

/**
 * Routing the API address into Auth Process
 * @author Simpli CLI generator
 */
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
class AuthRouter : RouterWrapper() {
    @GET
    @Operation(tags = ["AuthRequest"], summary = "Gets the user authentication")
    fun authenticate(@BeanParam param: DefaultParam): AuthResponse {
        return Pipe.Public().handle(readPipe, param) { context ->
            context.auth ?: throw InternalServerErrorException()
        }
    }

    @POST
    @Operation(tags = ["AuthRequest"], summary = "Submits the user authentication")
    fun signIn(@BeanParam param: DefaultParam, request: AuthRequest): AuthResponse {
        val permission = Permission.groupOf(USER_READ_ALL, ROLE_READ_ALL, PERMISSION_READ_ALL)

        return Pipe.Public(permission).handle(readPipe, param) { context ->
            AuthProcess(context).signIn(request)
        }
    }

    @PUT
    @Path("/password")
    @Operation(tags = ["RecoverPasswordByMailRequest"], summary = "Sends an email requesting to change the password")
    fun recoverPasswordByMail(@BeanParam param: DefaultParam, request: RecoverPasswordByMailRequest): Long {
        val permission = Permission.groupOf(USER_READ_ID_USER_PK)

        return Pipe.Public(permission).handle(readPipe, param) { context ->
            AuthProcess(context).recoverPasswordByMail(request)
        }
    }

    @POST
    @Path("/password")
    @Operation(tags = ["ResetPasswordRequest"], summary = "Recovers the password with a given hash")
    fun resetPassword(@BeanParam param: DefaultParam, request: ResetPasswordRequest): Int {
        val permission = Permission.groupOf(USER_READ_ID_USER_PK, USER_UPDATE_SENHA)

        return Pipe.Public(permission).handle(transactionPipe, param) { context ->
            AuthProcess(context).resetPassword(request)
        }
    }

    @POST
    @Path("/me/password")
    @Operation(tags = ["ChangePasswordRequest"], summary = "Changes the password with a given new password")
    fun changePassword(@BeanParam param: DefaultParam, request: ChangePasswordRequest): Int {
        val permission = Permission.groupOf(USER_READ_ID_USER_PK, USER_UPDATE_SENHA, ROLE_READ_ALL, PERMISSION_READ_ALL)

        return Pipe(permission).handle(transactionPipe, param) { context ->
            Policy(context).requireAny {
                addRole(Role.ADMIN)
                addRole(Role.MANAGER)
                addRole(Role.VIEWER)
                addPermission(Permission.USER_UPDATE_ALL)
            }

            AuthProcess(context).changePassword(request)
        }
    }
}

package org.usecase.exception.response

import org.usecase.exception.HttpException
import javax.ws.rs.core.Response

/**
 * UnauthorizedException
 * @author Simpli CLI generator
 */
open class UnauthorizedException(message: String? = null)
    : HttpException(message ?: "Unauthorized", Response.Status.UNAUTHORIZED)

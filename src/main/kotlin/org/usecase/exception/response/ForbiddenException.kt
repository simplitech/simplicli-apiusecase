package org.usecase.exception.response

import org.usecase.exception.HttpException
import javax.ws.rs.core.Response

/**
 * ForbiddenException
 * @author Simpli CLI generator
 */
open class ForbiddenException(message: String? = null)
    : HttpException(message ?: "Forbidden", Response.Status.FORBIDDEN)

package org.usecase.exception.response

import org.usecase.exception.HttpException
import javax.ws.rs.core.Response

/**
 * BadRequestException
 * @author Simpli CLI generator
 */
open class BadRequestException(message: String? = null)
    : HttpException(message ?: "Bad Request", Response.Status.BAD_REQUEST)

package org.usecase.exception.response

import org.usecase.exception.HttpException
import javax.ws.rs.core.Response

/**
 * InternalServerErrorException
 * @author Simpli CLI generator
 */
open class InternalServerErrorException(message: String? = null)
    : HttpException(message ?: "Internal Server Error", Response.Status.INTERNAL_SERVER_ERROR)

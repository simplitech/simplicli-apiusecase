package org.usecase.exception.response

import org.usecase.exception.HttpException
import javax.ws.rs.core.Response

/**
 * NotAcceptableException
 * @author Simpli CLI generator
 */
open class NotAcceptableException(message: String? = null)
    : HttpException(message ?: "Not Acceptable", Response.Status.NOT_ACCEPTABLE)

package org.usecase.exception.response

import org.usecase.exception.HttpException
import javax.ws.rs.core.Response

/**
 * RequestUriToLongException
 * @author Simpli CLI generator
 */
open class RequestUriToLongException(message: String? = null)
    : HttpException(message ?: "Request Uri To Long", Response.Status.REQUEST_URI_TOO_LONG)

package org.usecase.exception.response

import org.usecase.exception.HttpException
import javax.ws.rs.core.Response

/**
 * GoneException
 * @author Simpli CLI generator
 */
open class GoneException(message: String? = null)
    : HttpException(message ?: "Gone", Response.Status.GONE)

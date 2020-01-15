package org.usecase.exception.response

import org.usecase.exception.HttpException
import javax.ws.rs.core.Response

/**
 * LengthRequiredException
 * @author Simpli CLI generator
 */
open class LengthRequiredException(message: String? = null)
    : HttpException(message ?: "Length Required", Response.Status.LENGTH_REQUIRED)

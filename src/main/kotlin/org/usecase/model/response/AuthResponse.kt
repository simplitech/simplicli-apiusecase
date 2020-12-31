package org.usecase.model.response

import org.usecase.model.resource.User
import org.usecase.model.request.AuthRequest

/**
 * Authentication Response Model
 * @author Simpli CLI generator
 */
class AuthResponse(var token: String, var user: User, var requestOptions: AuthRequest.Options)

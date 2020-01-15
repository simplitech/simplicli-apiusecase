package org.usecase.user

import org.usecase.AppTest
import org.usecase.user.context.RequestContext
import org.usecase.user.request.AuthRequest
import org.usecase.user.response.AuthResponse
import org.usecase.app.Env
import org.usecase.model.resource.User
import br.com.simpli.tools.SecurityUtils.sha256

/**
 * Extended class of handle tests
 * @author Simpli CLI generator
 */
open class ProcessTest: AppTest() {
    protected val context = RequestContext(transacConnector, param)

    protected val user = User().apply {
        idUserPk = Env.props.testerId
        email = Env.props.testerLogin
        senha = sha256(Env.props.testerPassword)
    }

    protected val authRequest: AuthRequest
    protected val token: String
    protected val auth: AuthResponse

    init {
        authRequest = AuthRequest(user.email, user.senha)
        token = authRequest.toToken()
        auth = AuthResponse(token, user)
    }
}

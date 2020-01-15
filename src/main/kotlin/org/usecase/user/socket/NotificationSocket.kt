package org.usecase.user.socket

import org.usecase.user.context.AuthPipe
import org.usecase.app.Env
import org.usecase.app.RequestLogger
import org.usecase.param.DefaultParam
import org.usecase.wrapper.RouterWrapper
import org.usecase.wrapper.SocketWrapper
import java.util.logging.Level
import java.util.logging.Logger
import javax.websocket.OnClose
import javax.websocket.OnError
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint

/**
 * Notification Web Socket
 * The generic two-way (bi-directional) ongoing conversation between the client and the server
 * @author Simpli CLI generator
 */
@ServerEndpoint("/ws/user/notification/{token}")
class NotificationSocket: RouterWrapper() {

    companion object {
        val socket = SocketWrapper<String>()
    }

    @OnOpen
    fun onConnect(session: Session, @PathParam("token") token: String) {
        val param = DefaultParam.Auth()

        param.lang = "en-US"
        param.clientVersion = "ws.auth"
        param.authorization = """Bearer $token"""

        AuthPipe.handle(connectionPipe, param) { _, auth ->
            session.userProperties["token"] = auth.token
            session.userProperties["email"] = auth.email

            socket.attachSession(session, auth.id)
        }

        if (Env.props.detailedLog) {
            Logger.getLogger(RequestLogger::class.java.name).log(Level.INFO, """
            ===== CLIENT SOCKET CONNECTION ESTABLISHED =====
            CLIENT ID: ${session.userProperties["id"]}
            CLIENT LOGIN: ${session.userProperties["email"]}
            =========================================
            """)
        }
    }

    @OnClose
    fun onDisconnect(session: Session) {
        socket.detachSession(session)

        if (Env.props.detailedLog) {
            Logger.getLogger(RequestLogger::class.java.name).log(Level.INFO, """
            ===== CLIENT SOCKET CONNECTION LOST =====
            CLIENT ID: ${session.userProperties["id"]}
            CLIENT LOGIN: ${session.userProperties["email"]}
            =========================================
            """)
        }
    }

    @OnError
    fun onError(e: Throwable) {
        toResponse(e)
    }

}

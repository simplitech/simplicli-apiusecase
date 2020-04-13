package org.usecase.user.socket

import org.apache.logging.log4j.LogManager
import org.usecase.user.context.AuthPipe
import org.usecase.app.Facade.Env
import org.usecase.app.RequestLogger
import org.usecase.enums.ConnectionStatus
import org.usecase.extension.jsonProperties
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

        private val logger = LogManager.getLogger(NotificationSocket::class.java)
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

        logger.debug(session.jsonProperties(ConnectionStatus.ESTABLISHED))
    }

    @OnClose
    fun onDisconnect(session: Session) {
        socket.detachSession(session)

        logger.debug(session.jsonProperties(ConnectionStatus.LOST))
    }

    @OnError
    fun onError(e: Throwable) {
        toResponse(e)
    }

}

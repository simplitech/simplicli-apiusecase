package org.usecase.user.socket

import org.usecase.user.context.AuthPipe
import org.usecase.enums.ConnectionStatus
import org.usecase.extension.jsonProperties
import org.usecase.model.param.DefaultParam
import org.usecase.wrapper.RouterWrapper
import org.usecase.wrapper.SocketWrapper
import javax.websocket.OnClose
import javax.websocket.OnError
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint
import org.apache.logging.log4j.LogManager

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
        val param = DefaultParam()

        param.lang = "en-US"
        param.clientVersion = "ws.auth"
        param.authorization = """Bearer $token"""

        AuthPipe().handle(readPipe, param) { context ->
            session.userProperties["token"] = context.auth?.token
            session.userProperties["email"] = context.auth?.email

            socket.attachSession(session, context.auth?.id)
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

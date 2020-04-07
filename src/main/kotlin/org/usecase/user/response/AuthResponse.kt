package org.usecase.user.response

import org.usecase.model.resource.User
import org.usecase.user.socket.NotificationSocket
import io.swagger.v3.oas.annotations.media.Schema
import javax.xml.bind.annotation.XmlRootElement

/**
 * Authentication Response Model
 * @author Simpli CLI generator
 */
class AuthResponse(var token: String, var user: User) {
    val id @Schema(hidden = true) get() = user.id
    val email @Schema(hidden = true) get() = user.email

    @Schema(hidden = true)
    fun sendNotification(content: String) {
        NotificationSocket.socket.send(content, id)
    }
}

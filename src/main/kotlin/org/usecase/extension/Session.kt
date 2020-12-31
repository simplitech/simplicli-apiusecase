package org.usecase.extension

import org.usecase.app.Cast
import org.usecase.model.enum.ConnectionStatus
import com.google.gson.JsonObject
import javax.websocket.Session

fun Session.jsonProperties(status: ConnectionStatus): String {
    return Cast.pretty.toJson(
            JsonObject().apply {
                addProperty("connectionStatus", status.toString())
                addProperty("id", userProperties["id"]?.toString())
                addProperty("fullName", userProperties["fullName"]?.toString())
                addProperty("email", userProperties["email"]?.toString())
            }
    )
}

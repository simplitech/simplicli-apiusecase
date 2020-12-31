package org.usecase.extension

import org.usecase.app.Facade.Env
import org.usecase.model.enum.Lang
import org.usecase.locale.LangDefinition
import javax.servlet.http.HttpServletRequest

fun HttpServletRequest.ip(): String {
    return getHeader("x-forwarded-for") ?: remoteAddr
}

fun HttpServletRequest.forwarded(): Boolean {
    return !getHeader("x-forwarded-for").isNullOrEmpty()
}

fun HttpServletRequest.getAppUrl(): String {
    val port = getHeader("x-forwarded-port")?.toIntOrNull() ?: serverPort
    val scheme = getHeader("x-forwarded-proto") ?: scheme

    return scheme + "://" + serverName + (if (port > 0) ":$port" else "") + contextPath
}

fun HttpServletRequest.getLang(): LangDefinition {
    return Env.AVAILABLE_LANGUAGES[Lang.from(getHeader("Accept-Language"))] ?: LangDefinition("en_US")
}

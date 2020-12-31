package org.usecase.user.mail.manager

import br.com.simpli.sql.AbstractConnector
import org.usecase.app.Cast
import org.usecase.app.Facade
import org.usecase.model.enum.MailType
import org.usecase.model.enum.Mode
import org.usecase.wrapper.MailWrapper
import com.google.gson.JsonIOException
import org.apache.logging.log4j.LogManager
import java.io.File

class MailManager private constructor(con: AbstractConnector) : IMailManager {
    private val blacklist: Map<MailType, List<String>> = emptyMap() // TODO: applies blacklist
    private val logger = LogManager.getLogger(MailManager::class.java)

    companion object {
        fun use(con: AbstractConnector, block: MailManager.() -> Unit) {
            block(MailManager(con))
        }
    }

    override fun send(mail: MailWrapper) {
        val blacklistedEmails = blacklist[mail.mailType] ?: emptyList()

        if (!blacklistedEmails.contains(mail.destination)) {
            // Tries to send the email
            runCatching {
                if (Facade.Env.MODE != Mode.TEST) {
                    mail.send()
                } else {
                    val dirPath = "src/test/mail"
                    val dir = File(dirPath)
                    if (!dir.exists()) dir.mkdir()

                    val file = File("$dirPath/${mail.javaClass.simpleName}.html")
                    file.writeText(mail.content)
                }
            }.onFailure {
                // In case of exception, only logs the attempt
                val message = try {
                    Cast.pretty.toJson(mail, MailWrapper::class.java)
                } catch (_: JsonIOException) {
                    "Error sending email ${mail.mailType} to ${mail.destination}"
                }

                logger.warn(message, it)
            }
        } else {
            logger.debug(
                    "Email ${mail.mailType} to ${mail.destination} wasn't sent either because that type of mail was " +
                            "blacklisted by the user or that email address was blacklisted by the system"
            )
        }
    }
}

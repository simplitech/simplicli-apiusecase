package org.usecase.wrapper

import org.usecase.app.Facade.Env
import org.usecase.locale.LangDefinition
import br.com.simpli.ws.AwsSendEmailRequest
import org.usecase.model.enum.MailType
import org.usecase.user.mail.manager.IMailManager
import java.util.HashMap

/**
 * Mail Wrapper
 * Extended class of e-mail processes
 * @author Simpli CLI generator
 */
abstract class MailWrapper(protected val lang: LangDefinition) : AwsSendEmailRequest(Env.EMAIL_AWS_REGION) {
    companion object {
        @Transient const val TYPE_TAG = "type"
    }

    protected var data = HashMap<String, String>()
    protected val appUrl = Env.APP_DEFAULT_ORIGIN

    abstract val mailType: MailType

    val content get() = body
    var destination get() = to
        set(value) {
            to = value
        }

    init {
        from = Env.EMAIL_SENDER_PROVIDER
        name = formatLang("mailDefault.sender_name")

        data["appUrl"] = appUrl

        data["logoUrl"] = """$appUrl/img/logo.png"""
        data["logoWidth"] = "300"
        data["bgColor"] = "#F5F5F5"

        data["signature"] = name
        data["emailContact"] = formatLang("mailDefault.contact")

        data["linkFacebook"] = formatLang("mailDefault.link_facebook")
        data["linkInstagram"] = formatLang("mailDefault.link_instagram")
        data["linkTwitter"] = formatLang("mailDefault.link_twitter")
        data["linkYoutube"] = formatLang("mailDefault.link_youtube")
        data["linkLinkedin"] = formatLang("mailDefault.link_linkedin")
    }

    fun sendWithManager(manager: IMailManager) {
        manager.send(this)
    }

    fun formatLang(key: String, vararg args: Any?): String {
        return try {
            String.format(lang[key], *args)
        } catch (e: Exception) {
            key
        }
    }
}

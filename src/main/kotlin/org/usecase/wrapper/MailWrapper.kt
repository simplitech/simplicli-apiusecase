package org.usecase.wrapper

import org.usecase.app.Facade.Env
import org.usecase.locale.LangDefinition
import br.com.simpli.ws.AwsSendEmailRequest
import java.util.HashMap

/**
 * Mail Wrapper
 * Extended class of e-mail processes
 * @author Simpli CLI generator
 */
abstract class MailWrapper(protected val lang: LangDefinition) : AwsSendEmailRequest(Env.EMAIL_AWS_REGION) {
    protected var data = HashMap<String, String>()
    protected val appUrl = Env.APP_DEFAULT_ORIGIN

    init {
        from = Env.EMAIL_SENDER_PROVIDER
        name = lang["mailDefault.sender_name"]

        data["appUrl"] = appUrl

        data["logoUrl"] = """$appUrl/img/logo.png"""
        data["logoWidth"] = "300"
        data["bgColor"] = "#F5F5F5"

        data["signature"] = name
        data["emailContact"] = lang["mailDefault.contact"]

        data["linkFacebook"] = lang["mailDefault.link_facebook"]
        data["linkInstagram"] = lang["mailDefault.link_instagram"]
        data["linkTwitter"] = lang["mailDefault.link_twitter"]
        data["linkYoutube"] = lang["mailDefault.link_youtube"]
        data["linkLinkedin"] = lang["mailDefault.link_linkedin"]
    }
}

package org.usecase.user.mail

import org.usecase.wrapper.MailWrapper
import org.usecase.model.resource.User
import org.usecase.locale.LangDefinition

/**
 * Recover Password E-Mail handler
 * @author Simpli CLI generator
 */
class RecoverPasswordMail(lang: LangDefinition, user: User, hash: String) : MailWrapper(lang) {
    init {
        to = "${user.email}"
        subject = lang["mailResetPassword.subject"]

        data["title"] = lang["mailResetPassword.title"]
        data["subtitle"] = lang["mailResetPassword.subtitle"]
        data["body"] = lang["mailResetPassword.body"]
        data["textButton"] = lang["mailResetPassword.text_button"]
        data["labelButton"] = lang["mailResetPassword.label_button"]
        data["linkButton"] = """$appUrl/#/password/reset/$hash"""

        setBodyFromTemplate(this::class.java, data, "template.html")
    }
}

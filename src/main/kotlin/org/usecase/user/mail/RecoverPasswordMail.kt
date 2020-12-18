package org.usecase.user.mail

import org.usecase.model.enum.MailType
import org.usecase.wrapper.MailWrapper
import org.usecase.model.resource.User
import org.usecase.locale.LangDefinition

/**
 * Recover Password E-Mail handler
 * @author Simpli CLI generator
 */
class RecoverPasswordMail(lang: LangDefinition, user: User, hash: String) : MailWrapper(lang) {
    override val mailType: MailType = MailType.SYSTEM

    init {
        to = "${user.email}"
        subject = formatLang("mailResetPassword.subject")

        data["title"] = formatLang("mailResetPassword.title")
        data["subtitle"] = formatLang("mailResetPassword.subtitle")
        data["body"] = formatLang("mailResetPassword.body")
        data["textButton"] = formatLang("mailResetPassword.text_button")
        data["labelButton"] = formatLang("mailResetPassword.label_button")
        data["linkButton"] = """$appUrl/password/reset/$hash"""

        setBodyFromTemplate(this::class.java, data, "default.html")
    }
}

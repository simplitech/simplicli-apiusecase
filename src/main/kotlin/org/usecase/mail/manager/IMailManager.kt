package org.usecase.mail.manager

import org.usecase.wrapper.MailWrapper

interface IMailManager {
    fun send(mail: MailWrapper)
}
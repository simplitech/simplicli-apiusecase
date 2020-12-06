package org.usecase.user

import org.usecase.user.mail.RecoverPasswordMail
import org.junit.Test

/**
 * Test Mail services
 * @author Simpli CLI generator
 */
class MailTest: ProcessTest() {
    @Test
    fun testRecoverPasswordMail() {
        // Step1: Go to AwsCredentials.properties and set your AWS credentials
        // Step2: Go to Env and set the EMAIL_SENDER_PROVIDER correctly

        // Step 3: Uncomment bellow and set the receiver email
        // user.email = "your@email.here"
        // RecoverPasswordMail(lang, user, "hash").send()
    }
}

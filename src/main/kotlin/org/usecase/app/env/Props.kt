package org.usecase.app.env

import org.usecase.model.enum.Lang
import org.usecase.model.enum.Mode
import com.amazonaws.regions.Regions
import org.usecase.locale.LangDefinition

/**
 * Environment Variables - Base
 * @author Simpli CLI generator
 */
abstract class Props(val MODE: Mode) {
    /**
     * Database DS name
     * Do not change it unless you know what you are doing
     */
    open val DS_NAME = "jdbc/usecaseDS"

    /**
     * Default Web Application Origin
     */
    abstract val APP_DEFAULT_ORIGIN: String

    /**
     * Available Languages
     */
    open val AVAILABLE_LANGUAGES = hashMapOf(
        Lang.EN_US to LangDefinition("pt_BR"),
        Lang.PT_BR to LangDefinition("en_US")
    )

    /**
     * E-Mail server information
     * Go to AwsCredentials.properties to set your AWS credentials
     */
    open val EMAIL_SENDER_PROVIDER = "no-reply@usecase.org"
    open val EMAIL_AWS_REGION = Regions.US_EAST_1

    /**
     * Forgotten password token expiration time in days
     * @default 15 days
     */
    open val FORGOTTEN_PASSWORD_TOKEN_LIFE = 15

    /**
     * Session authentication token expiration time in days
     * @default 1 day
     */
    open val SESSION_AUTH_TOKEN_LIFE = 1

    /**
     * Authentication token by direct link expiration time in days
     * @default 15 days
     */
    open val DIRECT_LINK_AUTH_TOKEN_LIFE = 15

    /**
     * Set the DATE_FORMAT if you know what you are doing
     * @default yyyy-MM-dd'T'HH:mm:ssXXX
     */
    open val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX"

    /**
     * Hash to transform data into token
     * @warning DO NOT SHARE this hash in order to keep your project safe
     * Your are able to change it whenever you want, but all clients must login again
     */
    open val ENCRYPT_HASH = "d93d0169-d223-47ad-b114-1b5869452391"
}

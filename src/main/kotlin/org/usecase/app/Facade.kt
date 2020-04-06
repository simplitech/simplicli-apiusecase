package org.usecase.app

import org.usecase.app.env.*
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Environment Variables
 * @author Simpli CLI generator
 */
object Facade {

    /**
     * Enviroment mode: BETA, STAGING or PRODUCTION
     */
    @JvmStatic val Env: Props

    init {
        val mode = Mode.fromString(System.getProperty("ENVIRONMENT")) ?: Mode.BETA.also {
            Logger.getLogger(Facade::class.simpleName).log(Level.WARNING, "System property not found for ENVIRONMENT. Using Mode.BETA as default")
        }

        Env = when (mode) {
            Mode.PRODUCTION -> PropsProduction()
            Mode.STAGING -> PropsStaging()
            Mode.BETA -> PropsBeta()
        }
    }
}

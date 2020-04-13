package org.usecase.app

import org.apache.logging.log4j.LogManager
import org.usecase.app.env.*
import org.usecase.enums.Mode
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Environment Variables
 * @author Simpli CLI generator
 */
object Facade {

    private val logger = LogManager.getLogger(Facade::class.java)

    /**
     * Enviroment mode: BETA, STAGING or PRODUCTION
     */
    @JvmStatic val Env: Props

    init {
        val mode = Mode.fromString(System.getProperty("ENVIRONMENT")) ?: Mode.BETA.also {
            logger.warn("System property not found for ENVIRONMENT. Using Mode.BETA as default")
        }

        Env = when (mode) {
            Mode.PRODUCTION -> PropsProduction()
            Mode.STAGING -> PropsStaging()
            Mode.BETA -> PropsBeta()
        }
    }
}

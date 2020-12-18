package org.usecase.app

import org.usecase.model.enum.Mode
import org.apache.logging.log4j.LogManager
import org.usecase.app.env.*

/**
 * Facade
 * @author Simpli CLI generator
 */
object Facade {
    private val logger = LogManager.getLogger(Facade::class.java)

    /**
     * Environment mode: TEST, BETA, STAGING or PRODUCTION
     */
    @JvmStatic val Env: Props

    init {
        val mode = Mode.fromString(System.getProperty("ENVIRONMENT")) ?: Mode.TEST.also {
            logger.warn("System property not found for ENVIRONMENT. Using Mode.TEST as default")
        }

        Env = when (mode) {
            Mode.PRODUCTION -> PropsProduction()
            Mode.STAGING -> PropsStaging()
            Mode.BETA -> PropsBeta()
            Mode.TEST -> PropsTest()
        }
    }
}

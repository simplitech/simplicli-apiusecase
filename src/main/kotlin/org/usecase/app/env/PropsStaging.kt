package org.usecase.app.env

import org.usecase.model.enum.Mode

/**
 * Environment Variables - Staging
 * @author Simpli CLI generator
 */
class PropsStaging : Props(Mode.STAGING) {
    override val APP_DEFAULT_ORIGIN = "http://localhost:8181"
}

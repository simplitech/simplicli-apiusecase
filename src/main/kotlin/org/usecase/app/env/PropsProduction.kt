package org.usecase.app.env

import org.usecase.model.enum.Mode

/**
 * Environment Variables - Production
 * @author Simpli CLI generator
 */
class PropsProduction : Props(Mode.PRODUCTION) {
    override val APP_DEFAULT_ORIGIN = "http://localhost:8181"
}

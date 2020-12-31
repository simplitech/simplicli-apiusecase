package org.usecase.app.env

import org.usecase.model.enum.Mode

/**
 * Environment Variables - Test
 * @author Simpli CLI generator
 */
class PropsTest : Props(Mode.TEST) {
    override val APP_DEFAULT_ORIGIN = "http://localhost:8181"
    override val ENCRYPT_HASH = "dm3pnbfg-6voj-ehtt-x429-w1g9eqh5t1l2"
}

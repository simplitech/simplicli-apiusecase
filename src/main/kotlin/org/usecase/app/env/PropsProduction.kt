package org.usecase.app.env

import org.usecase.enums.Mode

class PropsProduction : Props(Mode.PRODUCTION) {
    override val APP_DEFAULT_ORIGIN = "http://localhost:8181"
}

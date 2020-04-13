package org.usecase.app.env

import org.usecase.enums.Mode

class PropsBeta : Props(Mode.BETA) {
    override val APP_DEFAULT_ORIGIN = "http://localhost:8181"
}

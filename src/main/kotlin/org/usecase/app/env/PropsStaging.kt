package org.usecase.app.env

import org.usecase.enums.Mode

class PropsStaging : Props(Mode.STAGING) {
    override val APP_DEFAULT_ORIGIN = "http://localhost:8181"
}


package org.usecase.app.env

class PropsStaging : Props(Mode.STAGING) {
    override val DETAILED_LOG = true
    override val APP_DEFAULT_ORIGIN = "http://localhost:8181"
}


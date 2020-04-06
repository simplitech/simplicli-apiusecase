package org.usecase.app.env

class PropsProduction : Props(Mode.PRODUCTION) {
    override val DETAILED_LOG = false
    override val APP_DEFAULT_ORIGIN = "http://localhost:8181"
}

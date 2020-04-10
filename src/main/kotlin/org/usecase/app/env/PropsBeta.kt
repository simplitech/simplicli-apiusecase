package org.usecase.app.env

class PropsBeta : Props(Mode.BETA) {
    override val DETAILED_LOG = true
    override val APP_DEFAULT_ORIGIN = "http://localhost:8181"
}

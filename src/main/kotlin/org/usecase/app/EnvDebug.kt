package org.usecase.app

/**
 * Environment Variables - Debug
 * @author Simpli CLI generator
 */
class EnvDebug : EnvProduction() {
    override val isProduction = false
    override val detailedLog = true
    override val appDefaultOrigin = "http://localhost:8181"
}

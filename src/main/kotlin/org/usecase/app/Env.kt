package org.usecase.app

/**
 * Environment Variables
 * @author Simpli CLI generator
 */
object Env {
    val props = if (PropertyHelper("/maven.properties")["buildType"] != "debug") {
        EnvProduction()
    } else {
        EnvDebug()
    }
}

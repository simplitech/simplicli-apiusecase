package org.usecase.model.enum

/**
 * Mode enum
 * Represents the environment of application
 * @author Simpli CLI generator
 */
enum class Mode {
    TEST, BETA, STAGING, PRODUCTION;

    companion object {
        fun fromString(string: String?): Mode? {
            return string?.run { values().firstOrNull { it.name.equals(this, true) } }
        }
    }
}

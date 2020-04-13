package org.usecase.enums

enum class Mode {
    BETA, STAGING, PRODUCTION;

    companion object {
        fun fromString(string: String?): Mode? {
            return string?.run { values().firstOrNull { it.name.equals(this, true) } }
        }
    }
}
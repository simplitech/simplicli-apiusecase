package org.usecase.enums

enum class ConnectionStatus {
    ESTABLISHED, LOST;

    override fun toString(): String {
        return super.name.toLowerCase()
    }
}
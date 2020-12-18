package org.usecase.model.enum

enum class MailType(val id: Long) {
    SYSTEM(1),
    OTHERS(2); // TODO

    companion object {
        fun fromId(id: Long?): MailType? {
            return id?.run { values().firstOrNull { it.id == this } }
        }
    }
}

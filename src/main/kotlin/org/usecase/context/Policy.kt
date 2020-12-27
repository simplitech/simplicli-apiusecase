package org.usecase.context

import org.usecase.exception.response.ForbiddenException

open class Policy(val context: RequestContext) {
    protected open val depth = 0
    protected val rules = mutableListOf<Rule>()

    open fun requireLevel(value: Int) {
        if (context.level < value) {
            throw ForbiddenException(context.lang["error.accessDenied"])
        }
    }

    fun requireAny(block: Rule.() -> Unit) {
        val rule = Rule(Rule.Type.ANY, toDeny = false, parent = this).apply { block() }

        if (depth == 0 && !rule.isAdmitted) {
            throw ForbiddenException(context.lang["error.accessDenied"])
        }
    }

    fun refuseAny(block: Rule.() -> Unit) {
        val rule = Rule(Rule.Type.ANY, toDeny = true, parent = this).apply { block() }

        if (depth == 0 && rule.isAdmitted) {
            throw ForbiddenException(context.lang["error.accessDenied"])
        }
    }

    fun requireAll(block: Rule.() -> Unit) {
        val rule = Rule(Rule.Type.ALL, toDeny = false, parent = this).apply { block() }

        if (depth == 0 && !rule.isAdmitted) {
            throw ForbiddenException(context.lang["error.accessDenied"])
        }
    }

    fun refuseAll(block: Rule.() -> Unit) {
        val rule = Rule(Rule.Type.ALL, toDeny = true, parent = this).apply { block() }

        if (depth == 0 && rule.isAdmitted) {
            throw ForbiddenException(context.lang["error.accessDenied"])
        }
    }

    class Rule(val type: Type, val toDeny: Boolean = false, val parent: Policy) : Policy(parent.context) {
        enum class Type { ANY, ALL }

        init {
            parent.rules.add(this)
        }

        override val depth = parent.depth + 1

        private var requiredLevel: Int? = null
        private val roles = mutableListOf<String>()
        private val permissions = mutableListOf<String>()

        val isAdmitted: Boolean get() {
            return when(type) {
                Type.ALL -> {
                    val conditions = mutableListOf(
                            roles.all { context.roles.contains(it) },
                            permissions.all { context.permission.isAllowed(it) },
                            rules.all { it.isAdmitted }
                    )

                    requiredLevel?.also {
                        conditions.add(context.level >= it)
                    }

                    conditions.all { it }.let { if (toDeny) !it else it }
                }
                Type.ANY -> {
                    val conditions = mutableListOf(
                            roles.any { context.roles.contains(it) },
                            permissions.any { context.permission.isAllowed(it) },
                            rules.any { it.isAdmitted }
                    )

                    requiredLevel?.also {
                        conditions.add(context.level >= it)
                    }

                    conditions.any { it }.let { if (toDeny) !it else it }
                }
            }
        }

        override fun requireLevel(value: Int) {
            requiredLevel = value
        }

        fun addRole(item: String) = roles.add(item)
        fun addRoleIf(item: String, condition: () -> Boolean) {
            if (condition()) roles.add(item)
        }

        fun addPermission(item: String) = permissions.add(item)
        fun addPermissionIf(item: String, condition: () -> Boolean) {
            if (condition()) permissions.add(item)
        }
    }
}

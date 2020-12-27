package org.usecase.context

open class PermissionGroup(open val scopes: MutableList<String> = mutableListOf()) {
    fun isAllowed(scope: String): Boolean = scopes.contains(scope)
    fun isDenied(scope: String): Boolean = !isAllowed(scope)

    fun isAllowedAll(vararg scopes: String) = this.scopes.all { scopes.contains(it) }
    fun isDeniedAll(vararg scopes: String) = !this.scopes.all { scopes.contains(it) }
    fun isAllowedNothing(vararg scopes: String) = this.scopes.all { !scopes.contains(it) }
    fun isDeniedNothing(vararg scopes: String) = !this.scopes.all { !scopes.contains(it) }

    fun isAllowedEither(vararg scopes: String) = this.scopes.any { scopes.contains(it) }
    fun isDeniedEither(vararg scopes: String) = !this.scopes.any { scopes.contains(it) }
    fun isAllowedNeither(vararg scopes: String) = this.scopes.any { !scopes.contains(it) }
    fun isDeniedNeither(vararg scopes: String) = !this.scopes.any { !scopes.contains(it) }

    fun isAllowedAll(group: PermissionGroup) = isAllowedAll(*group.scopes.toTypedArray())
    fun isDeniedAll(group: PermissionGroup) = isDeniedAll(*group.scopes.toTypedArray())
    fun isAllowedNothing(group: PermissionGroup) = isAllowedNothing(*group.scopes.toTypedArray())
    fun isDeniedNothing(group: PermissionGroup) = isDeniedNothing(*group.scopes.toTypedArray())

    fun isAllowedEither(group: PermissionGroup) = isAllowedEither(*group.scopes.toTypedArray())
    fun isDeniedEither(group: PermissionGroup) = isDeniedEither(*group.scopes.toTypedArray())
    fun isAllowedNeither(group: PermissionGroup) = isAllowedNeither(*group.scopes.toTypedArray())
    fun isDeniedNeither(group: PermissionGroup) = isDeniedNeither(*group.scopes.toTypedArray())

    fun union(group: PermissionGroup): PermissionGroup = union(*group.scopes.toTypedArray())
    fun union(vararg scopes: String): PermissionGroup {
        scopes.forEach {
            if (!this.scopes.contains(it)) this.scopes.add(it)
        }

        return this
    }

    fun intersect(group: PermissionGroup): PermissionGroup = intersect(*group.scopes.toTypedArray())
    fun intersect(vararg scopes: String): PermissionGroup {
        this.scopes.filter { scopes.contains(it) }
                .also {
                    this.scopes.clear()
                    this.scopes.addAll(it)
                }

        return this
    }

    fun diference(group: PermissionGroup): PermissionGroup = diference(*group.scopes.toTypedArray())
    fun diference(vararg scopes: String): PermissionGroup {
        this.scopes.filterNot { scopes.contains(it) }
                .also {
                    this.scopes.clear()
                    this.scopes.addAll(it)
                }

        return this
    }

    inline fun <reified T> buildArray(callback: WhiteList<T>.() -> Unit): Array<T> {
        return WhiteList<T>(this).run {
            callback(this)
            list.toTypedArray()
        }
    }

    inline fun <K, reified T> buildMap(callback: WhiteList<Pair<K, T>>.() -> Unit): Map<K, T> {
        return WhiteList<Pair<K, T>>(this).run {
            callback(this)
            list.toMap()
        }
    }

    class WhiteList<T>(val group: PermissionGroup) {
        val list = ArrayList<T>()

        fun add(item: T, vararg scopes: String) {
            if (group.isAllowedEither(*scopes)) list.add(item)
        }
    }
}

package org.usecase.user.context

import kotlin.collections.toTypedArray

class WhiteList<T>(val permission: Permission) {
    val list = ArrayList<T>()

    fun add(tag: Int, item: T) {
        if (permission.tags.contains(tag)) {
            this.list.add(item)
        }
    }
}

class Permission(vararg tags: Int) {
    val tags: Array<Int> = tags.toTypedArray()

    inline fun <reified T> buildArray(callback: WhiteList<T>.() -> Unit): Array<T> {
        val permissionedList = WhiteList<T>(this)
        callback(permissionedList)
        return permissionedList.list.toTypedArray()
    }

    inline fun <reified T> buildMap(callback: WhiteList<Pair<String, T>>.() -> Unit): Map<String, T> {
        val permissionedList = WhiteList<Pair<String, T>>(this)
        callback(permissionedList)
        return permissionedList.list.toMap()
    }

    fun can(vararg tags: Int) = tags.all { this.tags.contains(it) }

    companion object {
        const val CONECTADO_READ_ALL = 101
        const val CONECTADO_INSERT_ALL = 102
        const val CONECTADO_UPDATE_ALL = 103

        const val CONECTOR_PRINCIPAL_READ_ALL = 201
        const val CONECTOR_PRINCIPAL_INSERT_ALL = 202
        const val CONECTOR_PRINCIPAL_UPDATE_ALL = 203

        const val ENDERECO_READ_ALL = 301
        const val ENDERECO_INSERT_ALL = 302
        const val ENDERECO_UPDATE_ALL = 303

        const val EXTENSAO_DO_PRINCIPAL_READ_ALL = 401
        const val EXTENSAO_DO_PRINCIPAL_INSERT_ALL = 402
        const val EXTENSAO_DO_PRINCIPAL_UPDATE_ALL = 403

        const val GRUPO_DO_PRINCIPAL_READ_ALL = 501
        const val GRUPO_DO_PRINCIPAL_INSERT_ALL = 502
        const val GRUPO_DO_PRINCIPAL_UPDATE_ALL = 503

        const val ITEM_DO_PRINCIPAL_READ_ALL = 601
        const val ITEM_DO_PRINCIPAL_INSERT_ALL = 602
        const val ITEM_DO_PRINCIPAL_UPDATE_ALL = 603

        const val PRINCIPAL_READ_ALL = 701
        const val PRINCIPAL_INSERT_ALL = 702
        const val PRINCIPAL_UPDATE_ALL = 703

        const val TAG_PRINCIPAL_READ_ALL = 801
        const val TAG_PRINCIPAL_INSERT_ALL = 802
        const val TAG_PRINCIPAL_UPDATE_ALL = 803

        const val TAG_READ_ALL = 901
        const val TAG_INSERT_ALL = 902
        const val TAG_UPDATE_ALL = 903

        const val USER_READ_ALL = 1001
        const val USER_INSERT_ALL = 1002
        const val USER_UPDATE_ALL = 1003
    }
}
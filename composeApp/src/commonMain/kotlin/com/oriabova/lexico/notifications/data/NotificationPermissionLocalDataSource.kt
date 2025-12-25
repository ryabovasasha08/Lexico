package com.oriabova.lexico.notifications.data

import com.oriabova.lexico.localstorage.LocalStorage

private const val PermissionRequestedKey = "notifications_permission_requested"

class NotificationPermissionLocalDataSource(
    private val localStorage: LocalStorage
) {
    suspend fun wasPermissionRequested(): Boolean =
        localStorage.getBoolean(PermissionRequestedKey, false)

    suspend fun setPermissionRequested() {
        localStorage.putBoolean(PermissionRequestedKey, true)
    }
}

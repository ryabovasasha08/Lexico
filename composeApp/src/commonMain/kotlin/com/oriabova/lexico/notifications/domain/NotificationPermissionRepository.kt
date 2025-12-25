package com.oriabova.lexico.notifications.domain

interface NotificationPermissionRepository {
    suspend fun wasPermissionRequested(): Boolean
    suspend fun setPermissionRequested()
}

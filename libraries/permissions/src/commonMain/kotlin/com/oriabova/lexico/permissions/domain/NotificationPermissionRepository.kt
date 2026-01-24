package com.oriabova.lexico.permissions.domain

internal interface NotificationPermissionRepository {
    suspend fun wasPermissionRequested(): Boolean
    suspend fun setPermissionRequested()
}

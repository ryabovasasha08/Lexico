package com.oriabova.lexico.permissions.domain

interface NotificationPermissionRepository {
    suspend fun wasPermissionRequested(): Boolean
    suspend fun setPermissionRequested()
}

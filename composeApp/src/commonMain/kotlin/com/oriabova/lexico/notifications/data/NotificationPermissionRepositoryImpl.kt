package com.oriabova.lexico.notifications.data

import com.oriabova.lexico.notifications.domain.NotificationPermissionRepository

class NotificationPermissionRepositoryImpl(
    private val localDataSource: NotificationPermissionLocalDataSource
) : NotificationPermissionRepository {
    override suspend fun wasPermissionRequested(): Boolean = localDataSource.wasPermissionRequested()

    override suspend fun setPermissionRequested() {
        localDataSource.setPermissionRequested()
    }
}

package com.oriabova.lexico.permissions.data

import com.oriabova.lexico.permissions.domain.NotificationPermissionRepository

class NotificationPermissionRepositoryImpl(
    private val localDataSource: NotificationPermissionLocalDataSource
) : NotificationPermissionRepository {
    override suspend fun wasPermissionRequested(): Boolean = localDataSource.wasPermissionRequested()

    override suspend fun setPermissionRequested() {
        localDataSource.setPermissionRequested()
    }
}

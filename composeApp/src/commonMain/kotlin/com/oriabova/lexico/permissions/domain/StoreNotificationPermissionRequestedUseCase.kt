package com.oriabova.lexico.permissions.domain

class StoreNotificationPermissionRequestedUseCase(
    private val repository: NotificationPermissionRepository
) {
    suspend operator fun invoke() {
        repository.setPermissionRequested()
    }
}

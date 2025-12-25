package com.oriabova.lexico.notifications.domain

class StoreNotificationPermissionRequestedUseCase(
    private val repository: NotificationPermissionRepository
) {
    suspend operator fun invoke() {
        repository.setPermissionRequested()
    }
}

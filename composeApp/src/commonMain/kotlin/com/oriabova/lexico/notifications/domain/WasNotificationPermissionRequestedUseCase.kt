package com.oriabova.lexico.notifications.domain

class WasNotificationPermissionRequestedUseCase(
    private val repository: NotificationPermissionRepository
) {
    suspend operator fun invoke(): Boolean = repository.wasPermissionRequested()
}

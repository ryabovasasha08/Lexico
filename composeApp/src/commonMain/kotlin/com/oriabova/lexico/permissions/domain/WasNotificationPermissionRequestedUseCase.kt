package com.oriabova.lexico.permissions.domain

class WasNotificationPermissionRequestedUseCase(
    private val repository: NotificationPermissionRepository
) {
    suspend operator fun invoke(): Boolean = repository.wasPermissionRequested()
}

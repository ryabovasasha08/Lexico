package com.oriabova.lexico.permissions.domain

internal class WasNotificationPermissionRequestedUseCaseImpl(
    private val repository: NotificationPermissionRepository
) : WasNotificationPermissionRequestedUseCase {
    override suspend operator fun invoke(): Boolean = repository.wasPermissionRequested()
}

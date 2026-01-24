package com.oriabova.lexico.permissions.domain

internal class StoreNotificationPermissionRequestedUseCaseImpl(
    private val repository: NotificationPermissionRepository
) : StoreNotificationPermissionRequestedUseCase {
    override suspend operator fun invoke() {
        repository.setPermissionRequested()
    }
}
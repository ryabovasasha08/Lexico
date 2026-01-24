package com.oriabova.lexico.permissions.domain

interface WasNotificationPermissionRequestedUseCase {
    suspend operator fun invoke(): Boolean
}
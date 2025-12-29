package com.oriabova.lexico.permissions

import androidx.compose.runtime.Composable

@Composable
expect fun rememberNotificationPermissionRequester(
    onResult: (Boolean) -> Unit
): () -> Unit

@Composable
expect fun rememberNotificationSettingsOpener(): () -> Unit

@Composable
expect fun rememberNotificationPermissionChecker(
    onResult: (NotificationPermissionState) -> Unit
): () -> Unit

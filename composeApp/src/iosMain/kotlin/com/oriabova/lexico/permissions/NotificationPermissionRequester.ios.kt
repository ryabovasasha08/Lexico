package com.oriabova.lexico.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.oriabova.lexico.permissions.domain.StoreNotificationPermissionRequestedUseCase
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNAuthorizationStatusAuthorized
import platform.UserNotifications.UNAuthorizationStatusDenied
import platform.UserNotifications.UNAuthorizationStatusEphemeral
import platform.UserNotifications.UNAuthorizationStatusProvisional
import platform.UserNotifications.UNUserNotificationCenter
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

@Composable
actual fun rememberNotificationPermissionRequester(
    onResult: (Boolean) -> Unit
): () -> Unit {
    val scope = rememberCoroutineScope()
    val markRequested: StoreNotificationPermissionRequestedUseCase = koinInject()
    return {
        val center = UNUserNotificationCenter.currentNotificationCenter()
        val options = UNAuthorizationOptionAlert or
            UNAuthorizationOptionSound or
            UNAuthorizationOptionBadge
        scope.launch {
            markRequested()
        }
        center.requestAuthorizationWithOptions(options) { granted, _ ->
            dispatch_async(dispatch_get_main_queue()) {
                onResult(granted)
            }
        }
    }
}

@Composable
actual fun rememberNotificationSettingsOpener(): () -> Unit {
    return {
        val settingsUrl = NSURL.URLWithString(UIApplicationOpenSettingsURLString)
        if (settingsUrl != null) {
            UIApplication.sharedApplication.openURL(settingsUrl)
        }
    }
}

@Composable
actual fun rememberNotificationPermissionChecker(
    onResult: (NotificationPermissionState) -> Unit
): () -> Unit {
    return {
        val center = UNUserNotificationCenter.currentNotificationCenter()
        center.getNotificationSettingsWithCompletionHandler { settings ->
            val status = settings?.authorizationStatus
            val state = when (status) {
                UNAuthorizationStatusAuthorized,
                UNAuthorizationStatusProvisional,
                UNAuthorizationStatusEphemeral -> NotificationPermissionState.GRANTED
                UNAuthorizationStatusDenied -> NotificationPermissionState.DENIED
                else -> NotificationPermissionState.NOT_DETERMINED
            }
            dispatch_async(dispatch_get_main_queue()) {
                onResult(state)
            }
        }
    }
}

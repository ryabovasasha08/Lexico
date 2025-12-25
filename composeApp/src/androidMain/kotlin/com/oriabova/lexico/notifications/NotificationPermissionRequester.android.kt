package com.oriabova.lexico.notifications

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.oriabova.lexico.notifications.domain.StoreNotificationPermissionRequestedUseCase
import com.oriabova.lexico.notifications.domain.WasNotificationPermissionRequestedUseCase
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
actual fun rememberNotificationPermissionRequester(
    onResult: (Boolean) -> Unit
): () -> Unit {
    val scope = rememberCoroutineScope()
    val markRequested: StoreNotificationPermissionRequestedUseCase = koinInject()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            onResult(granted)
        }
    )

    return {
        if (permissionIsRequired()) {
            scope.launch { markRequested() }
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            onResult(true)
        }
    }
}

@Composable
actual fun rememberNotificationSettingsOpener(): () -> Unit {
    val context = LocalContext.current
    return remember(context) {
        {
            val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                    putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                }
            } else {
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}

@Composable
actual fun rememberNotificationPermissionChecker(
    onResult: (NotificationPermissionState) -> Unit
): () -> Unit {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val wasRequested: WasNotificationPermissionRequestedUseCase = koinInject()
    return remember(context) {
        {
            if (permissionIsRequired()) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    onResult(NotificationPermissionState.GRANTED)
                } else {
                    scope.launch {
                        if (wasRequested()) {
                            onResult(NotificationPermissionState.DENIED)
                        } else {
                            onResult(NotificationPermissionState.NOT_DETERMINED)
                        }
                    }
                }
            } else {
                onResult(NotificationPermissionState.GRANTED)
            }
        }
    }
}

private fun permissionIsRequired(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
}

package com.oriabova.lexico.setup.view.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.oriabova.lexico.permissions.NotificationPermissionState
import com.oriabova.lexico.permissions.rememberNotificationPermissionChecker
import com.oriabova.lexico.permissions.rememberNotificationPermissionRequester
import com.oriabova.lexico.permissions.rememberNotificationSettingsOpener
import com.oriabova.lexico.theme.Colors
import com.oriabova.lexico.theme.LexicoFont
import com.oriabova.lexico.theme.LexicoTheme
import lexico.composeapp.generated.resources.Res
import lexico.composeapp.generated.resources.setup_notifications_body
import lexico.composeapp.generated.resources.setup_notifications_cta_text
import lexico.composeapp.generated.resources.setup_notifications_denied
import lexico.composeapp.generated.resources.setup_notifications_note
import lexico.composeapp.generated.resources.setup_notifications_settings_cta_text
import lexico.composeapp.generated.resources.setup_notifications_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val BodySpacing = 24.dp

@Composable
fun SetupNotificationPermissionScreen(onPermissionGranted: () -> Unit) {
    var permissionDenied by remember { mutableStateOf(false) }

    CheckPermissionOnResume(
        onPermissionGranted = onPermissionGranted,
        onPermissionDenied = { permissionDenied = true }
    )

    SetupScreenWrapper {
        Column(
            modifier = Modifier
                .padding(
                    top = SetupUiDefaults.TopPadding,
                    bottom = SetupUiDefaults.BottomPadding
                )
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(SetupUiDefaults.VerticalSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(SetupUiDefaults.VerticalSpacing)
            ) {
                Title()
                Body()
                if (permissionDenied) {
                    DeniedNote()
                }
            }

            if (permissionDenied) {
                DeniedFooter()
            } else {
                InitialFooter(
                    onPermissionGranted = onPermissionGranted,
                    onPermissionDenied = { permissionDenied = true }
                )
            }
        }
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(resource = Res.string.setup_notifications_title),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.d100(color = Colors.primary030),
    )
}

@Composable
private fun Body() {
    Column(
        verticalArrangement = Arrangement.spacedBy(BodySpacing),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(resource = Res.string.setup_notifications_body),
            style = LexicoFont.f100Default(color = Colors.primary030),
        )
        Text(
            text = stringResource(resource = Res.string.setup_notifications_note),
            style = LexicoFont.f075Default(color = Colors.primary030.copy(alpha = 0.8f)),
        )
    }
}

@Composable
private fun DeniedNote() {
    Text(
        text = stringResource(resource = Res.string.setup_notifications_denied),
        style = LexicoFont.f075Default(color = Colors.error500),
    )
}

@Composable
private fun InitialFooter(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val requestPermission = rememberNotificationPermissionRequester { granted ->
        if (granted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }

    TextButton(
        text = stringResource(resource = Res.string.setup_notifications_cta_text),
        onClick = { requestPermission() }
    )
}

@Composable
private fun DeniedFooter() {
    val openSettings = rememberNotificationSettingsOpener()

    TextButton(
        text = stringResource(resource = Res.string.setup_notifications_settings_cta_text),
        onClick = { openSettings() }
    )
}

@Composable
private fun CheckPermissionOnResume(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val checkPermission = rememberNotificationPermissionChecker { granted ->
        when (granted) {
            NotificationPermissionState.GRANTED -> onPermissionGranted()
            NotificationPermissionState.DENIED -> onPermissionDenied()
            NotificationPermissionState.NOT_DETERMINED -> Unit
        }
    }

    DisposableEffect(lifecycleOwner, checkPermission) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                checkPermission()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
}

@Preview
@Composable
private fun SetupNotificationPermissionScreenPreview() {
    LexicoTheme { SetupNotificationPermissionScreen { } }
}

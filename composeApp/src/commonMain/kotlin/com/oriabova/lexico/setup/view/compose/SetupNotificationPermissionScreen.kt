package com.oriabova.lexico.setup.view.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.theme.Colors
import com.oriabova.lexico.theme.LexicoFont
import com.oriabova.lexico.theme.LexicoTheme
import lexico.composeapp.generated.resources.Res
import lexico.composeapp.generated.resources.setup_notifications_body
import lexico.composeapp.generated.resources.setup_notifications_cta_text
import lexico.composeapp.generated.resources.setup_notifications_note
import lexico.composeapp.generated.resources.setup_notifications_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val BodySpacing = 24.dp

@Composable
fun SetupNotificationPermissionScreen(onEnableNotifications: () -> Unit) {
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
            }

            TextButton(
                text = stringResource(resource = Res.string.setup_notifications_cta_text),
                onClick = onEnableNotifications
            )
        }
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(resource = Res.string.setup_notifications_title),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.d100(color = Colors.primary030),
        textAlign = TextAlign.Left
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
            textAlign = TextAlign.Left
        )
        Text(
            text = stringResource(resource = Res.string.setup_notifications_note),
            style = LexicoFont.f075Default(color = Colors.primary030.copy(alpha = 0.8f)),
            textAlign = TextAlign.Left
        )
    }
}

@Preview
@Composable
private fun SetupNotificationPermissionScreenPreview() {
    LexicoTheme { SetupNotificationPermissionScreen { } }
}

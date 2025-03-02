package com.oriabova.lexico.root.view.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.oriabova.lexico.root.view.theme.Colors.primary030
import com.oriabova.lexico.root.view.theme.Colors.primary050
import com.oriabova.lexico.root.view.theme.Colors.primary500
import com.oriabova.lexico.root.view.theme.Colors.primary600

private val LightColorScheme = lightColorScheme(
    primary = primary500,
    onPrimary = primary050,
    primaryContainer = primary030,
    onPrimaryContainer = primary600,
    error = Colors.error500,
    onError = Colors.error100,
    errorContainer = Colors.error200,
    onErrorContainer = Colors.error700,
    background = Colors.primary020,
    onBackground = Colors.supportDark,
    surface = Colors.primary020,
    onSurface = Colors.supportDark,
    surfaceVariant = Colors.support300,
    onSurfaceVariant = Colors.support900,
)

@Composable
fun LexicoTheme(
// Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            dynamicLightColorScheme(context)
        }

        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = material3Typography(),
        content = content
    )
}
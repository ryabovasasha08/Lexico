package com.oriabova.lexico.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun LexicoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = LightColorScheme
    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography(),
        shapes = Shapes,
        content = content,
    )
}
package com.oriabova.lexico.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light Theme Colors
internal val DarkPrimary = Color(0xFF0D7398)
internal val LightPrimary = Color(0xFFDAF9FC)
internal val DarkSupport = Color(0xFF291F1E)
internal val Accent = Color(0xFFEEC643)

internal val LightColorScheme = lightColorScheme(
    primary = DarkPrimary,
    background = LightPrimary,
    onBackground = LightPrimary,
    surface = LightPrimary,
    onSurface = DarkPrimary,
    secondary = DarkSupport,
    onSecondary = LightPrimary
)
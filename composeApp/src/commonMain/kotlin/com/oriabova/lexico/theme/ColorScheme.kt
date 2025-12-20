package com.oriabova.lexico.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light Theme Colors
val DarkPrimary = Color(0xFF0D7398)
val LightPrimary = Color(0xFFDAF9FC)
val DarkSupport = Color(0xFF291F1E)
val Accent = Color(0xFFEEC643)

internal val LightColorScheme = lightColorScheme(
    primary = DarkPrimary,
    background = LightPrimary,
    onBackground = LightPrimary,
    surface = LightPrimary,
    onSurface = DarkPrimary,
    secondary = DarkSupport,
    onSecondary = LightPrimary
)
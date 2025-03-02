package com.oriabova.lexico.root.view.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color

internal fun material3Typography(): Typography = Typography(
    displayLarge = LexicoFont.brand400(color = Color.Unspecified),
    displayMedium = LexicoFont.brand300(color = Color.Unspecified),
    displaySmall = LexicoFont.brand200(color = Color.Unspecified),

    headlineLarge = LexicoFont.f400Highlight(color = Color.Unspecified),
    headlineMedium = LexicoFont.f300Highlight(color = Color.Unspecified),
    headlineSmall = LexicoFont.f200Highlight(color = Color.Unspecified),

    titleLarge = LexicoFont.f200Highlight(color = Color.Unspecified),
    titleMedium = LexicoFont.f100Highlight(color = Color.Unspecified),
    titleSmall = LexicoFont.f075Highlight(color = Color.Unspecified),

    bodyLarge = LexicoFont.f100Default(color = Color.Unspecified),
    bodyMedium = LexicoFont.f075Default(color = Color.Unspecified),
    bodySmall = LexicoFont.f050Default(color = Color.Unspecified),

    labelLarge = LexicoFont.f100Default(color = Color.Unspecified),
    labelMedium = LexicoFont.f075Default(color = Color.Unspecified),
    labelSmall = LexicoFont.f050Default(color = Color.Unspecified),
)
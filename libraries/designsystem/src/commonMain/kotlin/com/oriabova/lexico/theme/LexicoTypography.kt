package com.oriabova.lexico.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.oriabova.lexico.theme.LexicoFont.brand200
import com.oriabova.lexico.theme.LexicoFont.brand300
import com.oriabova.lexico.theme.LexicoFont.brand400
import com.oriabova.lexico.theme.LexicoFont.f050Default
import com.oriabova.lexico.theme.LexicoFont.f075Default
import com.oriabova.lexico.theme.LexicoFont.f075Highlight
import com.oriabova.lexico.theme.LexicoFont.f100Default
import com.oriabova.lexico.theme.LexicoFont.f100Highlight
import com.oriabova.lexico.theme.LexicoFont.f200Highlight
import com.oriabova.lexico.theme.LexicoFont.f300Highlight
import com.oriabova.lexico.theme.LexicoFont.f400Highlight

@Composable
internal fun AppTypography() = Typography(
    displayLarge = brand400(color = Color.Unspecified),
    displayMedium = brand300(color = Color.Unspecified),
    displaySmall = brand200(color = Color.Unspecified),

    headlineLarge = f400Highlight(color = Color.Unspecified),
    headlineMedium = f300Highlight(color = Color.Unspecified),
    headlineSmall = f200Highlight(color = Color.Unspecified),

    titleLarge = f200Highlight(color = Color.Unspecified),
    titleMedium = f100Highlight(color = Color.Unspecified),
    titleSmall = f075Highlight(color = Color.Unspecified),

    bodyLarge = f100Default(color = Color.Unspecified),
    bodyMedium = f075Default(color = Color.Unspecified),
    bodySmall = f050Default(color = Color.Unspecified),

    labelLarge = f100Default(color = Color.Unspecified),
    labelMedium = f075Default(color = Color.Unspecified),
    labelSmall = f050Default(color = Color.Unspecified),
)
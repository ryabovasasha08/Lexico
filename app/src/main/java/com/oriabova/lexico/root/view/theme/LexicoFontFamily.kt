package com.oriabova.lexico.root.view.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.oriabova.app.R

object LexicoFontFamily {

    private val kreonFontFamily = FontFamily(
        Font(R.font.kreon_light, FontWeight.Light),
        Font(R.font.kreon_regular, FontWeight.Normal),
        Font(R.font.kreon_medium, FontWeight.Medium),
        Font(R.font.kreon_semibold, FontWeight.SemiBold),
        Font(R.font.kreon_bold, FontWeight.Bold),
    )

    private val nunitoFontFamily = FontFamily(
        Font(R.font.nunito_extralight, FontWeight.ExtraLight),
        Font(R.font.nunito_light, FontWeight.Light),
        Font(R.font.nunito_regular, FontWeight.Normal),
        Font(R.font.nunito_medium, FontWeight.Medium),
        Font(R.font.nunito_semibold, FontWeight.SemiBold),
        Font(R.font.nunito_bold, FontWeight.Bold),
        Font(R.font.nunito_extrabold, FontWeight.ExtraBold),
    )

    val brandFontFamily = nunitoFontFamily
    val decorationFontFamily = kreonFontFamily
}
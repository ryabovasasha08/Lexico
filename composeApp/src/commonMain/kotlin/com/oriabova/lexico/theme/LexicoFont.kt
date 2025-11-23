package com.oriabova.lexico.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.oriabova.lexico.root.view.theme.Colors
import lexico.composeapp.generated.resources.Res
import lexico.composeapp.generated.resources.kreon_bold
import lexico.composeapp.generated.resources.kreon_light
import lexico.composeapp.generated.resources.kreon_medium
import lexico.composeapp.generated.resources.kreon_regular
import lexico.composeapp.generated.resources.kreon_semibold
import lexico.composeapp.generated.resources.nunito_bold
import lexico.composeapp.generated.resources.nunito_extrabold
import lexico.composeapp.generated.resources.nunito_extralight
import lexico.composeapp.generated.resources.nunito_light
import lexico.composeapp.generated.resources.nunito_medium
import lexico.composeapp.generated.resources.nunito_regular
import lexico.composeapp.generated.resources.nunito_semibold
import org.jetbrains.compose.resources.Font

object LexicoFont {
    @Composable
    private fun kreonFontFamily() = FontFamily(
        Font(Res.font.kreon_light, FontWeight.Light),
        Font(Res.font.kreon_regular, FontWeight.Normal),
        Font(Res.font.kreon_medium, FontWeight.Medium),
        Font(Res.font.kreon_semibold, FontWeight.SemiBold),
        Font(Res.font.kreon_bold, FontWeight.Bold),
    )

    @Composable
    private fun nunitoFontFamily() = FontFamily(
        Font(Res.font.nunito_extralight, FontWeight.ExtraLight),
        Font(Res.font.nunito_light, FontWeight.Light),
        Font(Res.font.nunito_regular, FontWeight.Normal),
        Font(Res.font.nunito_medium, FontWeight.Medium),
        Font(Res.font.nunito_semibold, FontWeight.SemiBold),
        Font(Res.font.nunito_bold, FontWeight.Bold),
        Font(Res.font.nunito_extrabold, FontWeight.ExtraBold),
    )

    @Composable
    private fun brandFontFamily() = nunitoFontFamily()

    @Composable
    private fun decorationFontFamily() = kreonFontFamily()

    @Composable
    private fun font050LineHeight() = 14.sp

    @Composable
    private fun font075LineHeight() = 17.sp

    @Composable
    private fun font100LineHeight() = 22.sp

    @Composable
    private fun font200LineHeight() = 28.sp

    @Composable
    private fun font300LineHeight() = 34.sp

    @Composable
    private fun font050Size() = 11.sp

    @Composable
    private fun font050Weight() = FontWeight.Normal

    @Composable
    private fun font075Size() = 14.sp

    @Composable
    private fun font075Weight() = FontWeight.Normal

    @Composable
    private fun font100Size() = 18.sp

    @Composable
    private fun font100Weight() = FontWeight.Normal

    @Composable
    private fun font200Size() = 22.sp

    @Composable
    private fun font200Weight() = FontWeight.Light

    @Composable
    private fun font300Size() = 28.sp

    @Composable
    private fun font300Weight() = FontWeight.Light

    @Composable
    private fun font400Size() = 36.sp

    @Composable
    private fun font400LineHeight() = 42.sp

    @Composable
    private fun font400Weight() = FontWeight.Light

    @Composable
    private fun highlightFontWeight() = FontWeight.SemiBold

    @Composable
    private fun boldFontWeight() = FontWeight.Bold

    @Composable
    private fun decoFontStyle() = FontStyle.Normal

    @Composable
    private fun deco100Size() = 24.sp

    @Composable
    private fun deco100LineHeight() = 24.sp

    @Composable
    private fun deco100FontWeight() = FontWeight.Normal

    @Composable
    private fun deco200Size() = 34.sp

    @Composable
    private fun deco200LineHeight() = 34.sp

    @Composable
    private fun deco200FontWeight() = FontWeight.Normal

    @Composable
    private fun deco300Size() = 40.sp

    @Composable
    private fun deco300LineHeight() = 40.sp

    @Composable
    private fun deco300FontWeight() = FontWeight.SemiBold

    @Composable
    private fun brandFontStyle() = FontStyle.Normal

    @Composable
    private fun brandFontWeight() = FontWeight.Medium

    @Composable
    private fun brand100Size() = 40.sp

    @Composable
    private fun brand100LineHeight() = 40.sp

    @Composable
    private fun brand200Size() = 44.sp

    @Composable
    private fun brand200LineHeight() = 44.sp

    @Composable
    private fun brand300Size() = 48.sp

    @Composable
    private fun brand300LineHeight() = 48.sp

    @Composable
    private fun brand400Size() = 52.sp

    @Composable
    private fun brand400LineHeight() = 52.sp

    //region functions
    @Composable
    private fun createTextStyle(
        color: Color,
        fontFamily: FontFamily = brandFontFamily(),
        fontSize: TextUnit,
        fontStyle: FontStyle = FontStyle.Normal,
        fontWeight: FontWeight,
        lineHeight: TextUnit,
        textAlignment: TextAlign,
    ): TextStyle = TextStyle(
        color = color,
        fontSize = fontSize,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        lineHeight = lineHeight,
        fontStyle = fontStyle,
        textAlign = textAlignment,
    )
    //endregion

    //region Font DEFAULT
    @Composable
    fun f400Default(
        color: Color = Colors.support500,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font400Size(),
        fontWeight = font400Weight(),
        lineHeight = font400LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun f300Default(
        color: Color = Colors.support500,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font300Size(),
        fontWeight = font300Weight(),
        lineHeight = font300LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun f200Default(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font200Size(),
        fontWeight = font200Weight(),
        lineHeight = font200LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun f100Default(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font100Size(),
        fontWeight = font100Weight(),
        lineHeight = font100LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun f075Default(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font075Size(),
        fontWeight = font075Weight(),
        lineHeight = font075LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun f050Default(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font050Size(),
        fontWeight = font050Weight(),
        lineHeight = font050LineHeight(),
        textAlignment = textAlignment,
    )
    //endregion

    //region Font BOLD
    @Composable
    fun b400Default(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font400Size(),
        fontWeight = boldFontWeight(),
        lineHeight = font400LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun b300Default(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font300Size(),
        fontWeight = boldFontWeight(),
        lineHeight = font300LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun b200Default(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font200Size(),
        fontWeight = boldFontWeight(),
        lineHeight = font200LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun b100Default(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font100Size(),
        fontWeight = boldFontWeight(),
        lineHeight = font100LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun b075Default(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font075Size(),
        fontWeight = boldFontWeight(),
        lineHeight = font075LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun b050Default(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font050Size(),
        fontWeight = boldFontWeight(),
        lineHeight = font050LineHeight(),
        textAlignment = textAlignment,
    )
    //endregion

    //region Font HIGHLIGHT
    @Composable
    fun f400Highlight(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font400Size(),
        fontWeight = highlightFontWeight(),
        lineHeight = font400LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun f300Highlight(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font300Size(),
        fontWeight = highlightFontWeight(),
        lineHeight = font300LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun f200Highlight(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font200Size(),
        fontWeight = highlightFontWeight(),
        lineHeight = font200LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun f100Highlight(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font100Size(),
        fontWeight = highlightFontWeight(),
        lineHeight = font100LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun f075Highlight(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font075Size(),
        fontWeight = highlightFontWeight(),
        lineHeight = font075LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun f050Highlight(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font050Size(),
        fontWeight = highlightFontWeight(),
        lineHeight = font050LineHeight(),
        textAlignment = textAlignment,
    )
    //endregion

    //region Font Decoration
    @Composable
    fun d300(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontFamily = decorationFontFamily(),
        fontSize = deco300Size(),
        fontWeight = deco300FontWeight(),
        fontStyle = decoFontStyle(),
        lineHeight = deco300LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun d200(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontFamily = decorationFontFamily(),
        fontSize = deco200Size(),
        fontWeight = deco200FontWeight(),
        fontStyle = decoFontStyle(),
        lineHeight = deco200LineHeight(),
        textAlignment = textAlignment,
    )

    @Composable
    fun d100(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontFamily = decorationFontFamily(),
        fontSize = deco100Size(),
        fontWeight = deco100FontWeight(),
        fontStyle = decoFontStyle(),
        lineHeight = deco100LineHeight(),
        textAlignment = textAlignment,
    )
    //endregion

    //region Font brand
    @Composable
    fun brand400(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontFamily = brandFontFamily(),
        fontSize = brand400Size(),
        fontWeight = brandFontWeight(),
        lineHeight = brand400LineHeight(),
        fontStyle = brandFontStyle(),
        textAlignment = textAlignment,
    )

    @Composable
    fun brand300(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontFamily = brandFontFamily(),
        fontSize = brand300Size(),
        fontWeight = brandFontWeight(),
        lineHeight = brand300LineHeight(),
        fontStyle = brandFontStyle(),
        textAlignment = textAlignment,
    )

    @Composable
    fun brand200(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontFamily = brandFontFamily(),
        fontSize = brand200Size(),
        fontWeight = brandFontWeight(),
        lineHeight = brand200LineHeight(),
        fontStyle = brandFontStyle(),
        textAlignment = textAlignment,
    )

    @Composable
    fun brand100(
        color: Color = Colors.supportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontFamily = brandFontFamily(),
        fontSize = brand100Size(),
        fontWeight = brandFontWeight(),
        lineHeight = brand100LineHeight(),
        fontStyle = brandFontStyle(),
        textAlignment = textAlignment,
    )
    //endregion
}
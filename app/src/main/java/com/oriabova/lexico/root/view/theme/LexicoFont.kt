package com.oriabova.lexico.root.view.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.oriabova.lexico.root.view.theme.LexicoFontFamily.brandFontFamily
import com.oriabova.lexico.root.view.theme.LexicoFontFamily.decorationFontFamily

object LexicoFont {
    private val font050LineHeight: TextUnit = 14.sp

    private val font075LineHeight: TextUnit = 17.sp

    private val font100LineHeight: TextUnit = 22.sp

    private val font200LineHeight: TextUnit = 28.sp

    private val font300LineHeight = 34.sp

    private val font050Size: TextUnit = 11.sp
    private val font050Weight = FontWeight.Normal

    private val font075Size = 14.sp
    private val font075Weight = FontWeight.Normal

    private val font100Size = 18.sp
    private val font100Weight = FontWeight.Normal

    private val font200Size: TextUnit = 22.sp
    private val font200Weight = FontWeight.Light

    private val font300Size: TextUnit = 28.sp
    private val font300Weight = FontWeight.Light

    private val font400Size: TextUnit = 36.sp
    private val font400LineHeight: TextUnit = 42.sp
    private val font400Weight = FontWeight.Light

    private val highlightFontWeight: FontWeight = FontWeight.SemiBold

    private val decoFontStyle = FontStyle.Normal

    private val deco100Size = 30.sp
    private val deco100LineHeight = 30.sp
    private val deco100FontWeight = FontWeight.Normal

    private val deco200Size = 34.sp
    private val deco200LineHeight = 34.sp
    private val deco200FontWeight = FontWeight.Normal

    private val deco300Size = 40.sp
    private val deco300LineHeight = 40.sp
    private val deco300FontWeight = FontWeight.SemiBold

    private val brandFontStyle = FontStyle.Normal
    private val brandFontWeight = FontWeight.Medium

    private val brand100Size = 40.sp
    private val brand100LineHeight = 40.sp

    private val brand200Size = 44.sp
    private val brand200LineHeight = 44.sp

    private val brand300Size = 48.sp
    private val brand300LineHeight = 48.sp

    private val brand400Size = 52.sp
    private val brand400LineHeight = 52.sp

    //region private functions
    private fun createTextStyle(
        color: Color,
        fontFamily: FontFamily = brandFontFamily,
        fontSize: TextUnit,
        fontStyle: FontStyle = FontStyle.Normal,
        fontWeight: FontWeight,
        lineHeight: TextUnit,
        textAlignment: TextAlign,
    ) = TextStyle(
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
    fun f400Default(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font400Size,
        fontWeight = font400Weight,
        lineHeight = font400LineHeight,
        textAlignment = textAlignment,
    )

    fun f300Default(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font300Size,
        fontWeight = font300Weight,
        lineHeight = font300LineHeight,
        textAlignment = textAlignment,
    )

    fun f200Default(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font200Size,
        fontWeight = font200Weight,
        lineHeight = font200LineHeight,
        textAlignment = textAlignment,
    )

    fun f100Default(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font100Size,
        fontWeight = font100Weight,
        lineHeight = font100LineHeight,
        textAlignment = textAlignment,
    )

    fun f075Default(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font075Size,
        fontWeight = font075Weight,
        lineHeight = font075LineHeight,
        textAlignment = textAlignment,
    )

    fun f050Default(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font050Size,
        fontWeight = font050Weight,
        lineHeight = font050LineHeight,
        textAlignment = textAlignment,
    )

    //endregion

    //region Font HIGHLIGHT
    fun f400Highlight(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font400Size,
        fontWeight = highlightFontWeight,
        lineHeight = font400LineHeight,
        textAlignment = textAlignment,
    )

    fun f300Highlight(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font300Size,
        fontWeight = highlightFontWeight,
        lineHeight = font300LineHeight,
        textAlignment = textAlignment,
    )

    fun f200Highlight(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font200Size,
        fontWeight = highlightFontWeight,
        lineHeight = font200LineHeight,
        textAlignment = textAlignment,
    )

    fun f100Highlight(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font100Size,
        fontWeight = highlightFontWeight,
        lineHeight = font100LineHeight,
        textAlignment = textAlignment,
    )

    fun f075Highlight(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle =
        createTextStyle(
            color = color,
            fontSize = font075Size,
            fontWeight = highlightFontWeight,
            lineHeight = font075LineHeight,
            textAlignment = textAlignment,
        )

    fun f050Highlight(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ): TextStyle = createTextStyle(
        color = color,
        fontSize = font050Size,
        fontWeight = highlightFontWeight,
        lineHeight = font050LineHeight,
        textAlignment = textAlignment,
    )

    //region Font Decoration

    fun d300(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ) = createTextStyle(
        color = color,
        fontFamily = decorationFontFamily,
        fontSize = deco300Size,
        fontWeight = deco300FontWeight,
        fontStyle = decoFontStyle,
        lineHeight = deco300LineHeight,
        textAlignment = textAlignment,
    )

    fun d200(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ) = createTextStyle(
        color = color,
        fontFamily = decorationFontFamily,
        fontSize = deco200Size,
        fontWeight = deco200FontWeight,
        fontStyle = decoFontStyle,
        lineHeight = deco200LineHeight,
        textAlignment = textAlignment,
    )

    fun d100(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ) = createTextStyle(
        color = color,
        fontFamily = decorationFontFamily,
        fontSize = deco100Size,
        fontWeight = deco100FontWeight,
        fontStyle = decoFontStyle,
        lineHeight = deco100LineHeight,
        textAlignment = textAlignment,
    )

    //endregion

    //region Font brand

    fun b400(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ) = createTextStyle(
        color = color,
        fontFamily = brandFontFamily,
        fontSize = brand400Size,
        fontWeight = brandFontWeight,
        lineHeight = brand400LineHeight,
        fontStyle = brandFontStyle,
        textAlignment = textAlignment,
    )

    fun b300(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ) = createTextStyle(
        color = color,
        fontFamily = brandFontFamily,
        fontSize = brand300Size,
        fontWeight = brandFontWeight,
        lineHeight = brand300LineHeight,
        fontStyle = brandFontStyle,
        textAlignment = textAlignment,
    )

    fun b200(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ) = createTextStyle(
        color = color,
        fontFamily = brandFontFamily,
        fontSize = brand200Size,
        fontWeight = brandFontWeight,
        lineHeight = brand200LineHeight,
        fontStyle = brandFontStyle,
        textAlignment = textAlignment,
    )

    fun b100(
        color: Color = Colors.ColorSupportDark,
        textAlignment: TextAlign = TextAlign.Start,
    ) = createTextStyle(
        color = color,
        fontFamily = brandFontFamily,
        fontSize = brand100Size,
        fontWeight = brandFontWeight,
        lineHeight = brand100LineHeight,
        fontStyle = brandFontStyle,
        textAlignment = textAlignment,
    )

    //endregion

    //endregion
}
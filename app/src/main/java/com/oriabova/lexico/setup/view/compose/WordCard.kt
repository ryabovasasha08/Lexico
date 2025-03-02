package com.oriabova.lexico.setup.view.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.root.view.theme.Colors
import com.oriabova.lexico.root.view.theme.LexicoFont

private const val CardAspectRatio = 0.7f

private val CardCornerRadius = 15.dp
private val BorderStrokeWidth = 2.dp
private val CardOuterPadding = 8.dp
private val CardInnerPadding = 32.dp

@Composable
fun WordCard(text: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Colors.primary030,
            disabledContainerColor = Colors.primary030,
            contentColor = Colors.support500,
            disabledContentColor = Colors.support500,
        ),
        shape = RoundedCornerShape(CardCornerRadius),
        border = BorderStroke(BorderStrokeWidth, Colors.primary500),
        modifier = modifier
            .aspectRatio(CardAspectRatio)
            .padding(CardOuterPadding)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                modifier = Modifier.padding(CardInnerPadding),
                text = text,
                style = LexicoFont.d200(color = Colors.primary500),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordCardPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        WordCard(text = "Lexico")
    }
}
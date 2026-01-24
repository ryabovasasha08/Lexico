package com.oriabova.lexico.home.view.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.theme.Colors
import com.oriabova.lexico.theme.LexicoFont
import lexico.libraries.designsystem.generated.resources.Res
import lexico.libraries.designsystem.generated.resources.home_streak_badge
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val CornerRadius = 14.dp
private val Elevation = 6.dp
private val HorizontalPadding = 14.dp
private val VerticalPadding = 10.dp
private val Spacing = 6.dp

@Composable
internal fun StreakBanner(streakDays: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(CornerRadius),
        colors = CardDefaults.cardColors(containerColor = Colors.supportLight),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = HorizontalPadding,
                vertical = VerticalPadding
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Colors.accent500
            )
            Text(
                text = stringResource(Res.string.home_streak_badge, streakDays),
                style = LexicoFont.f075Highlight(color = Colors.support800)
            )
        }
    }
}

@Preview
@Composable
private fun StreakBannerPreview() {
    StreakBanner(streakDays = 5)
}

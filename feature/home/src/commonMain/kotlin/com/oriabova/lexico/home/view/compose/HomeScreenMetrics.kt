package com.oriabova.lexico.home.view.compose

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.theme.Spacing

private val HeightThreshold = 700.dp

private val ScreenPaddingRegular = Spacing.M
private val ScreenPaddingCompact = Spacing.M
private val CardPaddingRegular = Spacing.XL
private val CardPaddingCompact = Spacing.L
private val CardSpacingRegular = 16.dp
private val CardSpacingCompact = 12.dp
private val ProgressSpacingRegular = 10.dp
private val ProgressSpacingCompact = 8.dp

internal data class HomeScreenMetrics(
    val screenPadding: Dp,
    val cardPadding: Dp,
    val cardSpacing: Dp,
    val progressSpacing: Dp,
)

private val RegularMetrics = HomeScreenMetrics(
    screenPadding = ScreenPaddingRegular,
    cardPadding = CardPaddingRegular,
    cardSpacing = CardSpacingRegular,
    progressSpacing = ProgressSpacingRegular,
)

private val CompactMetrics = HomeScreenMetrics(
    screenPadding = ScreenPaddingCompact,
    cardPadding = CardPaddingCompact,
    cardSpacing = CardSpacingCompact,
    progressSpacing = ProgressSpacingCompact,
)

internal fun metricsForHeight(height: Dp): HomeScreenMetrics {
    return if (height < HeightThreshold) CompactMetrics else RegularMetrics
}

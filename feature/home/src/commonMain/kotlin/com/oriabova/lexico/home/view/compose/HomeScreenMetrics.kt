package com.oriabova.lexico.home.view.compose

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val HeightThreshold = 700.dp

internal data class HomeScreenMetrics(
    val screenPadding: Dp,
    val cardPadding: Dp,
    val cardSpacing: Dp,
    val imageHeight: Dp,
    val badgeBottomPadding: Dp,
    val sectionSpacing: Dp,
    val progressSpacing: Dp,
)

private val RegularMetrics = HomeScreenMetrics(
    screenPadding = 20.dp,
    cardPadding = 28.dp,
    cardSpacing = 22.dp,
    imageHeight = 280.dp,
    badgeBottomPadding = 16.dp,
    sectionSpacing = 14.dp,
    progressSpacing = 10.dp
)

private val CompactMetrics = HomeScreenMetrics(
    screenPadding = 16.dp,
    cardPadding = 20.dp,
    cardSpacing = 14.dp,
    imageHeight = 220.dp,
    badgeBottomPadding = 10.dp,
    sectionSpacing = 10.dp,
    progressSpacing = 8.dp
)

internal fun metricsForHeight(height: Dp): HomeScreenMetrics {
    return if (height < HeightThreshold) CompactMetrics else RegularMetrics
}

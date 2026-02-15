package com.oriabova.lexico.home.view.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableDefaults
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.snapTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oriabova.lexico.home.view.HomeViewModel
import com.oriabova.lexico.home.view.model.HomeUiEvent
import com.oriabova.lexico.home.view.model.HomeUiEvent.OnSaveWord
import com.oriabova.lexico.home.view.model.HomeUiEvent.OnSkipWord
import com.oriabova.lexico.home.view.model.HomeUiState
import com.oriabova.lexico.home.view.model.VocabularyCard
import com.oriabova.lexico.theme.Colors
import com.oriabova.lexico.theme.LexicoFont
import com.oriabova.lexico.theme.LexicoTheme
import com.oriabova.lexico.theme.Spacing
import com.oriabova.lexico.tts.rememberTtsSpeaker
import com.oriabova.lexico.utils.Language
import kotlinx.coroutines.flow.collectLatest
import lexico.feature.home.generated.resources.Res
import lexico.feature.home.generated.resources.home_no_word_ready
import lexico.feature.home.generated.resources.home_saved_for_practice
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.abs
import kotlin.math.sqrt

private val ProgressHeight = 8.dp
private val ActionButtonSize = 58.dp
private val CardBorderWidth = 1.dp
private val CardElevation = 10.dp
private val ProgressCornerRadius = 50.dp
private val ActionButtonBorderWidth = 2.dp
private val ActionButtonsHorizontalPadding = Spacing.XL
private val ActionButtonsBottomPadding = Spacing.L
private val CardContentBottomInset = ActionButtonSize / 2 + ActionButtonsBottomPadding + Spacing.M
private val BackCardScale = 0.96f
private val BackCardVerticalOffset = 16.dp
private val FabScaleBoost = 0.2f
private const val SwipeThresholdFraction = 0.3f
private const val MaxRotationDegrees = 8f

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenInternal(
        uiState = uiState,
        handleUiEvent = viewModel::handleUiEvent
    )
}

@Composable
private fun HomeScreenInternal(
    uiState: HomeUiState,
    handleUiEvent: (HomeUiEvent) -> Unit,
) {
    val progress = progressFraction(uiState.savedCount, uiState.maxSavedCount)
    val ttsSpeaker = rememberTtsSpeaker()

    Scaffold(
        containerColor = Colors.primary020,
        contentWindowInsets = WindowInsets.systemBars,
    ) { padding ->
        HomeScreenContent(
            uiState = uiState,
            progress = progress,
            padding = padding,
            onAudioPlay = { card -> ttsSpeaker.speak(card.word, card.language.code) },
            onSave = { handleUiEvent(OnSaveWord) },
            onSkip = { handleUiEvent(OnSkipWord) }
        )
    }
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState,
    progress: Float,
    padding: PaddingValues,
    onAudioPlay: (VocabularyCard) -> Unit,
    onSave: () -> Unit,
    onSkip: () -> Unit,
) {
    var swipeProgress by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(uiState) {
        if (uiState !is HomeUiState.Content) swipeProgress = 0f
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        val metrics = metricsForHeight(maxHeight)
        val maxWidthPx = with(LocalDensity.current) { maxWidth.toPx() }
        val backCardOffsetPx = with(LocalDensity.current) { BackCardVerticalOffset.toPx() }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(metrics.screenPadding),
            verticalArrangement = Arrangement.spacedBy(metrics.cardSpacing)
        ) {
            ProgressSection(
                progress = progress,
                savedCount = uiState.savedCount,
                maxSavedCount = uiState.maxSavedCount,
                spacing = metrics.progressSpacing
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = ActionButtonSize / 2)
                ) {
                    when (uiState) {
                        is HomeUiState.Loading -> WordCard(modifier = Modifier.fillMaxSize()) {
                            LoadingWordCard(
                                metrics = metrics,
                                bottomContentInset = CardContentBottomInset
                            )
                        }

                        is HomeUiState.Empty -> WordCard(modifier = Modifier.fillMaxSize()) {
                            EmptyWordCard()
                        }

                        is HomeUiState.Content -> {
                            uiState.nextCard?.let { nextCard ->
                                WordCard(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .graphicsLayer {
                                            scaleX = BackCardScale
                                            scaleY = BackCardScale
                                            translationY = backCardOffsetPx
                                        },
                                    borderColor = Colors.primary200
                                ) {
                                    WordCardContent(
                                        card = nextCard,
                                        metrics = metrics,
                                        scrollState = rememberScrollState(),
                                        bottomContentInset = CardContentBottomInset,
                                        onAudioPlay = {}
                                    )
                                }
                            }

                            val scrollState = rememberScrollState()
                            WordCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .cardSwipe(
                                        maxWidthPx = maxWidthPx,
                                        scrollState = scrollState,
                                        enabled = true,
                                        onSave = onSave,
                                        onSkip = onSkip,
                                        onProgressChanged = { swipeProgress = it }
                                    ),
                                borderColor = swipeBorderColor(swipeProgress)
                            ) {
                                WordCardContent(
                                    card = uiState.currentCard,
                                    metrics = metrics,
                                    scrollState = scrollState,
                                    bottomContentInset = CardContentBottomInset,
                                    onAudioPlay = { onAudioPlay(uiState.currentCard) }
                                )
                            }
                        }
                    }
                }

                ActionBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(
                            start = ActionButtonsHorizontalPadding,
                            end = ActionButtonsHorizontalPadding,
                            bottom = ActionButtonsBottomPadding
                        ),
                    swipeProgress = swipeProgress,
                    enabled = uiState is HomeUiState.Content,
                    onSkip = onSkip,
                    onSave = onSave
                )
            }
        }
    }
}

private enum class SwipeAnchor {
    Center,
    Skip,
    Save,
}

@Composable
private fun Modifier.cardSwipe(
    maxWidthPx: Float,
    scrollState: ScrollState,
    enabled: Boolean,
    onSave: () -> Unit,
    onSkip: () -> Unit,
    onProgressChanged: (Float) -> Unit,
): Modifier = composed {
    val haptic = LocalHapticFeedback.current
    val onSaveUpdated by rememberUpdatedState(onSave)
    val onSkipUpdated by rememberUpdatedState(onSkip)
    val onProgressChangedUpdated by rememberUpdatedState(onProgressChanged)

    val swipeState = remember<AnchoredDraggableState<SwipeAnchor>> {
        AnchoredDraggableState(initialValue = SwipeAnchor.Center)
    }

    LaunchedEffect(maxWidthPx) {
        swipeState.updateAnchors(
            DraggableAnchors {
                SwipeAnchor.Skip at -maxWidthPx
                SwipeAnchor.Center at 0f
                SwipeAnchor.Save at maxWidthPx
            }
        )
    }

    val flingBehavior = AnchoredDraggableDefaults.flingBehavior(
        state = swipeState,
        positionalThreshold = { distance -> distance * SwipeThresholdFraction }
    )

    LaunchedEffect(swipeState) {
        snapshotFlow { swipeState.settledValue }
            .collectLatest { value ->
                when (value) {
                    SwipeAnchor.Save -> {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onSaveUpdated()
                        swipeState.snapTo(SwipeAnchor.Center)
                    }

                    SwipeAnchor.Skip -> {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onSkipUpdated()
                        swipeState.snapTo(SwipeAnchor.Center)
                    }

                    SwipeAnchor.Center -> Unit
                }
            }
    }

    LaunchedEffect(swipeState, maxWidthPx) {
        snapshotFlow { runCatching { swipeState.requireOffset() }.getOrDefault(0f) }
            .collectLatest { offset ->
                val progress = if (maxWidthPx == 0f) 0f else (offset / maxWidthPx).coerceIn(-1f, 1f)
                onProgressChangedUpdated(progress)
            }
    }

    LaunchedEffect(enabled) {
        if (!enabled) onProgressChangedUpdated(0f)
    }

    val offset = runCatching { swipeState.requireOffset() }.getOrDefault(0f)
    val progressOffset = if (maxWidthPx == 0f) 0f else (offset / maxWidthPx).coerceIn(-1f, 1f)
    val rotation = progressOffset * MaxRotationDegrees

    graphicsLayer {
        translationX = offset
        rotationZ = rotation
        transformOrigin = TransformOrigin(0.5f, 1f)
    }.anchoredDraggable(
        state = swipeState,
        orientation = Orientation.Horizontal,
        enabled = enabled && !scrollState.isScrollInProgress,
        flingBehavior = flingBehavior
    )
}

@Composable
private fun ProgressSection(
    progress: Float,
    savedCount: Int,
    maxSavedCount: Int,
    spacing: Dp,
) {
    Column(verticalArrangement = Arrangement.spacedBy(spacing)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.home_saved_for_practice),
                style = LexicoFont.f100Default(color = Colors.support900)
            )
            Text(
                text = "$savedCount/$maxSavedCount",
                style = LexicoFont.f075Default(color = Colors.support700)
            )
        }
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(ProgressHeight)
                .clip(RoundedCornerShape(ProgressCornerRadius)),
            color = Colors.primary500,
            trackColor = Colors.primary050
        )
    }
}

@Composable
private fun WordCard(
    modifier: Modifier = Modifier,
    borderColor: Color = Colors.primary300,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(CardCornerRadius),
        colors = CardDefaults.elevatedCardColors(containerColor = Colors.supportLight),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = CardElevation),
        border = BorderStroke(CardBorderWidth, borderColor)
    ) { content() }
}

@Composable
private fun EmptyWordCard() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(Res.string.home_no_word_ready),
            style = LexicoFont.f100Default(color = Colors.support700),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ActionBar(
    modifier: Modifier = Modifier,
    swipeProgress: Float,
    enabled: Boolean,
    onSkip: () -> Unit,
    onSave: () -> Unit,
) {
    val skipScale = 1f + FabScaleBoost * (-swipeProgress).coerceIn(0f, 1f)
    val saveScale = 1f + FabScaleBoost * swipeProgress.coerceIn(0f, 1f)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ActionButton(
            background = Colors.error500,
            enabled = enabled,
            scale = skipScale,
            onClick = onSkip
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null
            )
        }
        ActionButton(
            background = Colors.success500,
            enabled = enabled,
            scale = saveScale,
            onClick = onSave
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun ActionButton(
    background: Color,
    enabled: Boolean,
    scale: Float,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
) {
    FloatingActionButton(
        onClick = {
            if (enabled) onClick()
        },
        modifier = Modifier
            .size(ActionButtonSize)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .border(ActionButtonBorderWidth, Colors.supportLight, CircleShape),
        shape = CircleShape,
        containerColor = if (enabled) background else background.copy(alpha = 0.5f),
        contentColor = Colors.supportLight,
    ) {
        Box(contentAlignment = Alignment.Center) {
            icon()
        }
    }
}

private fun swipeBorderColor(progress: Float): Color {
    val normalizedProgress = abs(progress).coerceIn(0f, 1f)
    val emphasizedProgress = (sqrt(normalizedProgress) * 1.15f).coerceIn(0f, 1f)
    val directionColor = when {
        progress > 0f -> Colors.success500
        progress < 0f -> Colors.error500
        else -> Colors.primary300
    }
    return lerp(Colors.primary300, directionColor, emphasizedProgress)
}

private fun progressFraction(savedCount: Int, maxSavedCount: Int): Float {
    if (maxSavedCount <= 0) return 0f
    val rawProgress = savedCount.coerceAtLeast(0).toFloat() / maxSavedCount
    return rawProgress.coerceIn(0f, 1f)
}

@Composable
@Preview(name = "Small Phone", widthDp = 320, heightDp = 640)
@Preview(name = "Foldable", widthDp = 673, heightDp = 841)
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    LexicoTheme {
        HomeScreenInternal(
            uiState = HomeUiState.Content(
                savedCount = 1,
                maxSavedCount = 3,
                currentCard = VocabularyCard(
                    word = "Serendipity",
                    translation = "A happy accident",
                    insteadOf = "Lucky",
                    example = "Finding that tiny cafe was pure serendipity on the trip.",
                    nuance = "Serendipity implies a fortunate discovery by chance.",
                    imageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee",
                    visualPrompt = "Warm sunlit alley with a cozy hidden cafe, cinematic, inviting.",
                    language = Language(code = "en-GB", name = "English")
                ),
                nextCard = VocabularyCard(
                    word = "Radiant",
                    translation = "Shining brightly",
                    insteadOf = "Bright",
                    example = "Her smile was radiant.",
                    nuance = "Radiant implies warm, glowing brightness.",
                    imageUrl = "https://images.unsplash.com/photo-1497436072909-60f360e1d4b1",
                    visualPrompt = "Golden hour portrait, warm light, joyful mood.",
                    language = Language(code = "en-GB", name = "English")
                )
            ),
            handleUiEvent = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeScreenEmptyPreview() {
    LexicoTheme {
        HomeScreenInternal(
            uiState = HomeUiState.Empty(
                savedCount = 1,
                maxSavedCount = 3,
            ),
            handleUiEvent = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeScreenLoadingPreview() {
    LexicoTheme {
        HomeScreenInternal(
            uiState = HomeUiState.Loading(
                savedCount = 1,
                maxSavedCount = 3,
            ),
            handleUiEvent = {}
        )
    }
}

package com.oriabova.lexico.home.view.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import lexico.feature.home.generated.resources.Res
import lexico.feature.home.generated.resources.home_no_word_ready
import lexico.feature.home.generated.resources.home_save
import lexico.feature.home.generated.resources.home_saved_for_practice
import lexico.feature.home.generated.resources.home_skip
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

private val ProgressHeight = 8.dp
private val ActionButtonSize = 64.dp
private val ActionLabelSpacing = 8.dp
private val CardBorderWidth = 1.dp
private val CardElevation = 10.dp
private val ProgressCornerRadius = 50.dp
private val ActionButtonBorderWidth = 2.dp

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
        bottomBar = {
            ActionBar(
                onSkip = { handleUiEvent(OnSkipWord) },
                onSave = { handleUiEvent(OnSaveWord) }
            )
        }
    ) { padding ->
        HomeScreenContent(
            uiState = uiState,
            progress = progress,
            padding = padding,
            onAudioPlay = { card -> ttsSpeaker.speak(card.word, card.language.code) }
        )
    }
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState,
    progress: Float,
    padding: PaddingValues,
    onAudioPlay: (VocabularyCard) -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        val metrics = metricsForHeight(maxHeight)

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
            WordCard {
                uiState.currentCard?.let {
                    WordCardContent(
                        card = it,
                        metrics = metrics,
                        onAudioPlay = { onAudioPlay(it) }
                    )
                } ?: EmptyWordCard()
            }
        }
    }
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
private fun WordCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(CardCornerRadius),
        colors = CardDefaults.elevatedCardColors(containerColor = Colors.supportLight),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = CardElevation),
        border = BorderStroke(CardBorderWidth, Colors.primary300)
    ) { content() }

}

@Composable
private fun EmptyWordCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
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
    onSkip: () -> Unit,
    onSave: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = Spacing.XL, end = Spacing.XL, bottom = Spacing.XL),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ActionButton(
            label = stringResource(Res.string.home_skip),
            background = Colors.error500,
            onClick = onSkip
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.size(Spacing.X3L))
        ActionButton(
            label = stringResource(Res.string.home_save),
            background = Colors.success500,
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
    label: String,
    background: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(ActionLabelSpacing)
    ) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .size(ActionButtonSize)
                .border(ActionButtonBorderWidth, Colors.supportLight, CircleShape),
            shape = CircleShape,
            containerColor = background,
            contentColor = Colors.supportLight
        ) {
            icon()
        }
        Text(
            text = label,
            style = LexicoFont.f075Highlight(color = Colors.support900)
        )
    }
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
            uiState = HomeUiState(
                isLoading = false,
                savedCount = 1,
                maxSavedCount = 3,
                currentCard = VocabularyCard(
                    word = "Serendipity",
                    insteadOf = "Lucky",
                    example = "Finding that tiny cafe was pure serendipity on the trip.",
                    nuance = "Serendipity implies a fortunate discovery by chance.",
                    imageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee",
                    visualPrompt = "Warm sunlit alley with a cozy hidden cafe, cinematic, inviting.",
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
            uiState = HomeUiState(
                isLoading = false,
                savedCount = 1,
                maxSavedCount = 3,
                currentCard = null
            ),
            handleUiEvent = {}
        )
    }
}

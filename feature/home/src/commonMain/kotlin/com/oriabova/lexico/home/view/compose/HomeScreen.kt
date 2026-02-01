package com.oriabova.lexico.home.view.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.oriabova.lexico.home.view.HomeViewModel
import com.oriabova.lexico.home.view.model.HomeUiEvent
import com.oriabova.lexico.home.view.model.HomeUiEvent.OnSaveWord
import com.oriabova.lexico.home.view.model.HomeUiEvent.OnSkipWord
import com.oriabova.lexico.home.view.model.HomeUiState
import com.oriabova.lexico.home.view.model.VocabularyCard
import com.oriabova.lexico.theme.Colors
import com.oriabova.lexico.theme.LexicoFont
import com.oriabova.lexico.theme.LexicoTheme
import com.oriabova.lexico.tts.rememberTtsSpeaker
import com.oriabova.lexico.utils.Language
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

private val ScreenPadding = 20.dp
private val CardPadding = 28.dp
private val CardCornerRadius = 24.dp
private val CardSpacing = 22.dp
private val ProgressHeight = 8.dp
private val ImageHeight = 280.dp
private val ActionButtonSize = 64.dp
private val ActionBarPadding = 24.dp
private val ActionBarSpacing = 36.dp
private val NuanceSpacing = 6.dp
private val ProgressSpacing = 10.dp
private val WordBadgePaddingHorizontal = 22.dp
private val WordBadgePaddingVertical = 10.dp
private val AudioChipPaddingHorizontal = 14.dp
private val AudioChipPaddingVertical = 6.dp
private val SectionSpacing = 14.dp
private val DividerHeight = 1.dp
private val DividerAlpha = 0.4f
private val ActionLabelSpacing = 8.dp
private val CardBorderWidth = 1.dp
private val CardElevation = 10.dp
private val BadgeBottomPadding = 16.dp
private val BadgeCornerRadius = 14.dp
private val ChipCornerRadius = 50.dp
private val ChipBorderWidth = 1.dp
private val ChipSpacing = 8.dp
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = ScreenPadding, vertical = ScreenPadding),
            verticalArrangement = Arrangement.spacedBy(CardSpacing)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(ProgressSpacing)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Saved for practice",
                        style = LexicoFont.f100Default(color = Colors.support900)
                    )
                    Text(
                        text = "${uiState.savedCount}/${uiState.maxSavedCount}",
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
            LexicoCard(
                card = uiState.currentCard,
                onAudioPlay = { card -> ttsSpeaker.speak(card.word, card.language.code) }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun LexicoCard(
    card: VocabularyCard?,
    onAudioPlay: (VocabularyCard) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(CardCornerRadius),
        colors = CardDefaults.elevatedCardColors(containerColor = Colors.supportLight),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = CardElevation),
        border = BorderStroke(CardBorderWidth, Colors.primary300)
    ) {
        Column(
            modifier = Modifier.padding(CardPadding),
            verticalArrangement = Arrangement.spacedBy(CardSpacing)
        ) {
            if (card == null) {
                EmptyCardState()
                return@Column
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ImageHeight)
                    .clip(RoundedCornerShape(CardCornerRadius))
            ) {
                AsyncImage(
                    model = card.imageUrl,
                    contentDescription = card.visualPrompt,
                    contentScale = ContentScale.Crop,
                    placeholder = ColorPainter(Colors.primary050),
                    error = ColorPainter(Colors.primary050),
                    modifier = Modifier.matchParentSize()
                )
                Text(
                    text = card.word,
                    style = LexicoFont.f300Highlight(color = Colors.supportLight),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = BadgeBottomPadding)
                        .background(
                            color = Colors.primary500,
                            shape = RoundedCornerShape(BadgeCornerRadius)
                        )
                        .padding(
                            horizontal = WordBadgePaddingHorizontal,
                            vertical = WordBadgePaddingVertical
                        )
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(ChipCornerRadius))
                        .background(Colors.primary050)
                        .border(ChipBorderWidth, Colors.primary200, RoundedCornerShape(ChipCornerRadius))
                        .clickable { onAudioPlay(card) }
                        .padding(
                            horizontal = AudioChipPaddingHorizontal,
                            vertical = AudioChipPaddingVertical
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(ChipSpacing)
                ) {
                    Icon(
                        imageVector = Icons.Filled.VolumeUp,
                        contentDescription = "Play pronunciation",
                        tint = Colors.primary500
                    )
                    Text(
                        text = "Pronunciation",
                        style = LexicoFont.f075Highlight(color = Colors.primary500)
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(SectionSpacing)) {
                Text(
                    text = "Example",
                    style = LexicoFont.f075Highlight(color = Colors.primary500)
                )
                Text(
                    text = card.example,
                    style = LexicoFont.f100Default(color = Colors.support700)
                )
            }

            CardDivider()

            NuanceSection(
                insteadOf = card.insteadOf,
                targetWord = card.word,
                nuance = card.nuance
            )
        }
    }
}

@Composable
private fun EmptyCardState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(ImageHeight),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No word ready yet",
            style = LexicoFont.f100Default(color = Colors.support700),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NuanceSection(
    insteadOf: String,
    targetWord: String,
    nuance: String,
) {
    val isTooltipVisible = remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(NuanceSpacing)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Nuance",
                style = LexicoFont.f075Highlight(color = Colors.primary500)
            )
            if (nuance.isNotBlank()) {
                Box {
                    IconButton(
                        onClick = { isTooltipVisible.value = true },
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Show nuance",
                            tint = Colors.primary500,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = isTooltipVisible.value,
                        onDismissRequest = { isTooltipVisible.value = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = nuance,
                                    style = LexicoFont.f100Default(color = Colors.support700)
                                )
                            },
                            onClick = { isTooltipVisible.value = false }
                        )
                    }
                }
            }
        }
        Text(
            text = "$insteadOf -> $targetWord",
            style = LexicoFont.f100Default(color = Colors.support900)
        )
    }
}

@Composable
private fun CardDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(DividerHeight)
            .background(Colors.primary200.copy(alpha = DividerAlpha))
    )
}

@Composable
private fun ActionBar(
    onSkip: () -> Unit,
    onSave: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ActionBarPadding, vertical = ActionBarPadding),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ActionButton(
            label = "Skip",
            background = Colors.error500,
            onClick = onSkip
        ) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = "Skip word")
        }
        Spacer(modifier = Modifier.size(ActionBarSpacing))
        ActionButton(
            label = "Save",
            background = Colors.success500,
            onClick = onSave
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Save word")
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

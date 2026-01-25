package com.oriabova.lexico.home.view.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oriabova.lexico.home.view.HomeViewModel
import com.oriabova.lexico.home.view.model.HomeUiEvent
import com.oriabova.lexico.home.view.model.HomeUiState
import com.oriabova.lexico.home.view.model.WordCardUiState
import com.oriabova.lexico.theme.Colors
import com.oriabova.lexico.theme.LexicoFont
import com.oriabova.lexico.theme.LexicoTheme
import com.oriabova.lexico.tts.rememberTtsSpeaker
import lexico.feature.home.generated.resources.Res
import lexico.feature.home.generated.resources.home_adjust_drops
import lexico.feature.home.generated.resources.home_listen
import lexico.feature.home.generated.resources.home_new_badge
import lexico.feature.home.generated.resources.home_practice_now
import lexico.feature.home.generated.resources.home_review_recent
import lexico.feature.home.generated.resources.home_today_drops
import lexico.feature.home.generated.resources.home_use_now
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

private val ScreenPadding = 16.dp
private val ExtraTopPadding = 12.dp
private val SectionSpacing = 18.dp
private val ItemSpacing = 12.dp
private val BannerOffset = 8.dp
private val HeaderSpacing = 4.dp
private val HeaderProgressSpacing = 10.dp
private val ProgressHeight = 8.dp
private val CardCornerRadius = 18.dp
private val CardElevation = 6.dp
private val CardPadding = 20.dp
private val CardContentSpacing = 14.dp
private val WordRowSpacing = 10.dp
private val WordCardMinHeight = 340.dp
private val NewBadgePaddingHorizontal = 12.dp
private val NewBadgePaddingVertical = 6.dp
private val ActionRowSpacing = 12.dp
private val ListenSpacer = 10.dp
private val ExampleCornerRadius = 14.dp
private val ExamplePadding = 14.dp
private val ExampleSpacing = 12.dp
private val IconSize = 20.dp
private val IconSizeLarge = 22.dp
private val QuickActionPaddingVertical = 14.dp
private val QuickActionPaddingHorizontal = 12.dp
private val QuickActionSpacing = 8.dp
private val ProgressCardPadding = 16.dp
private val TipRowSpacing = 12.dp

private val CardShape = RoundedCornerShape(CardCornerRadius)

@Composable
internal fun HomeScreen(
   homeViewModel: HomeViewModel = koinViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenInternal(
        uiState,
        homeViewModel::handleUiEvent
    )
}

@Composable
private fun HomeScreenInternal(
    uiState: HomeUiState,
    handleUiEvent: (HomeUiEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.primary020),
        containerColor = Colors.primary020
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(ScreenPadding)
                .padding(top = ExtraTopPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(SectionSpacing)
            ) {
                Header(uiState)
                Spacer(modifier = Modifier.weight(1f))
                WordOfTheDayCard(uiState.currentWord, handleUiEvent)
                Spacer(modifier = Modifier.weight(1f))
                QuickActionsRow(handleUiEvent)
                TipCard(uiState.tip)
            }

            if (uiState.showStreakBanner) {
                StreakBanner(
                    streakDays = uiState.streakDays,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(
                            top = BannerOffset,
                            end = BannerOffset
                        )
                )
            }
        }
    }
}

@Composable
private fun Header(uiState: HomeUiState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(HeaderSpacing)
        ) {
            Text(
                text = stringResource(Res.string.home_today_drops),
                style = LexicoFont.f200Highlight(color = Colors.support900)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(HeaderProgressSpacing)
            ) {
                LinearProgressIndicator(
                    progress = { uiState.progressFraction },
                    modifier = Modifier
                        .weight(1f)
                        .height(ProgressHeight)
                        .clip(CircleShape),
                    color = Colors.primary500,
                    trackColor = Colors.primary050
                )
                Text(
                    text = "${uiState.deliveredToday}/${uiState.dailyGoal}",
                    style = LexicoFont.f075Default(color = Colors.support700)
                )
            }
        }
    }
}

@Composable
private fun WordOfTheDayCard(currentWord: WordCardUiState, handleUiEvent: (HomeUiEvent) -> Unit) {
    val ttsSpeaker = rememberTtsSpeaker()
    Card(
        shape = CardShape,
        colors = CardDefaults.cardColors(
            containerColor = Colors.primary500,
            contentColor = Colors.supportLight
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = CardElevation),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = WordCardMinHeight)
    ) {
        Column(
            modifier = Modifier.padding(CardPadding),
            verticalArrangement = Arrangement.spacedBy(CardContentSpacing)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(WordRowSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentWord.word,
                    style = LexicoFont.f300Highlight(color = Colors.supportLight)
                )
                if (currentWord.isNew) {
                    Text(
                        text = stringResource(Res.string.home_new_badge),
                        style = LexicoFont.f075Highlight(color = Colors.primary700),
                        modifier = Modifier
                            .background(
                                color = Colors.supportLight.copy(alpha = 0.8f),
                                shape = CircleShape
                            )
                            .padding(
                                horizontal = NewBadgePaddingHorizontal,
                                vertical = NewBadgePaddingVertical
                            )
                    )
                }
            }
            Row(
                modifier = Modifier.clickable {
                    ttsSpeaker.speak(currentWord.word, currentWord.languageCode)
                },
                horizontalArrangement = Arrangement.spacedBy(ExampleSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Campaign,
                    contentDescription = null,
                    tint = Colors.supportLight.copy(alpha = 0.85f),
                    modifier = Modifier.size(IconSize)
                )
                Text(
                    text = "[${currentWord.pronunciation}]",
                    style = LexicoFont.f075Default(color = Colors.supportLight.copy(alpha = 0.85f))
                )
            }
            Text(
                text = currentWord.partOfSpeech,
                style = LexicoFont.b075Default(color = Colors.supportLight)
            )
            Text(
                text = currentWord.definition,
                style = LexicoFont.f100Default(color = Colors.supportLight)
            )
            ExampleBubble(currentWord.example) {
                ttsSpeaker.speak(currentWord.example, currentWord.languageCode)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(ActionRowSpacing)) {
                Button(
                    onClick = { handleUiEvent(HomeUiEvent.UseWordClick(currentWord)) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Colors.accent500,
                        contentColor = Colors.supportDark
                    )
                ) {
                    Text(
                        text = stringResource(Res.string.home_use_now),
                        style = LexicoFont.f100Highlight(color = Colors.supportDark)
                    )
                }
                OutlinedButton(
                    onClick = { /* Play audio */ },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Colors.supportLight)
                ) {
                    Icon(
                        imageVector = Icons.Default.Hearing,
                        contentDescription = null,
                        tint = Colors.supportLight
                    )
                    Spacer(modifier = Modifier.size(ListenSpacer))
                    Text(
                        text = stringResource(Res.string.home_listen),
                        style = LexicoFont.f100Highlight(color = Colors.supportLight)
                    )
                }
            }
        }
    }
}

@Composable
private fun ExampleBubble(text: String, onReadExample: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Colors.supportLight.copy(alpha = 0.3f),
                shape = RoundedCornerShape(ExampleCornerRadius)
            )
            .clickable { onReadExample() }
            .padding(ExamplePadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(ExampleSpacing)
    ) {
        Icon(
            imageVector = Icons.Default.Campaign,
            contentDescription = null,
            tint = Colors.support800,
            modifier = Modifier.size(IconSize)
        )
        Text(
            text = text,
            style = LexicoFont.d100(color = Colors.support800),
            maxLines = 2
        )
    }
}

@Composable
private fun QuickActionsRow(handleUiEvent: (HomeUiEvent) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = ItemSpacing),
        horizontalArrangement = Arrangement.spacedBy(ItemSpacing)
    ) {
        QuickActionCard(
            title = stringResource(Res.string.home_practice_now),
            icon = Icons.Default.Hearing,
            onClick = { handleUiEvent(HomeUiEvent.PracticeNowClick) }
        )
        QuickActionCard(
            title = stringResource(Res.string.home_review_recent),
            icon = Icons.Default.Star,
            onClick = { handleUiEvent(HomeUiEvent.ReviewRecentClick) }
        )
        QuickActionCard(
            title = stringResource(Res.string.home_adjust_drops),
            icon = Icons.Default.AccessTime,
            onClick = { handleUiEvent(HomeUiEvent.AdjustScheduleClick) }
        )
    }
}

@Composable
private fun RowScope.QuickActionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = CardShape,
        colors = CardDefaults.cardColors(containerColor = Colors.supportLight),
        border = BorderStroke(1.dp, Colors.primary200),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.weight(1f),
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = QuickActionPaddingVertical,
                    horizontal = QuickActionPaddingHorizontal
                )
                .align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(QuickActionSpacing)
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Colors.primary500)
            Text(
                text = title,
                style = LexicoFont.f100Default(
                    color = Colors.support900,
                    textAlignment = androidx.compose.ui.text.style.TextAlign.Center
                )
            )
        }
    }
}

@Composable
private fun TipCard(tip: String) {
    Card(
        shape = CardShape,
        colors = CardDefaults.cardColors(containerColor = Colors.primary050),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(ProgressCardPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(TipRowSpacing)
        ) {
            Icon(
                imageVector = Icons.Default.Campaign,
                contentDescription = null,
                tint = Colors.primary500,
                modifier = Modifier.size(IconSizeLarge)
            )
            Text(
                text = tip,
                style = LexicoFont.f100Default(color = Colors.support900)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    LexicoTheme {
        HomeScreenInternal(
            uiState = HomeUiState(
                isLoading = false,
                currentWord = WordCardUiState(
                    word = "Serendipity",
                    pronunciation = "seh-ren-DIP-ih-tee",
                    partOfSpeech = "noun",
                    definition = "A pleasant surprise found by chance.",
                    example = "Meeting an old friend in the city was pure serendipity.",
                    isNew = true,
                    languageCode = "en-GB"
                ),
                streakDays = 4,
                deliveredToday = 2,
                dailyGoal = 5,
                nextDropIn = "32 min",
                tip = "Use today's word in a short voice note.",
                showStreakBanner = true
            ),
            handleUiEvent = {}
        )
    }
}

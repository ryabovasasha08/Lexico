package com.oriabova.lexico.home.view.compose

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.oriabova.lexico.home.view.model.VocabularyCard
import com.oriabova.lexico.theme.Colors
import com.oriabova.lexico.theme.LexicoFont
import com.oriabova.lexico.theme.Spacing
import com.oriabova.lexico.utils.Language
import lexico.feature.home.generated.resources.Res
import lexico.feature.home.generated.resources.home_example
import lexico.feature.home.generated.resources.home_instead_of_format
import lexico.feature.home.generated.resources.home_nuance
import lexico.feature.home.generated.resources.home_play_pronunciation
import lexico.feature.home.generated.resources.home_pronunciation
import lexico.feature.home.generated.resources.home_show_nuance
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal val CardCornerRadius = 24.dp
private val BadgeCornerRadius = 14.dp
private val ChipCornerRadius = 50.dp
private val ChipBorderWidth = 1.dp
private val DividerHeight = 1.dp
private val DividerAlpha = 0.4f
private val NuanceInfoButtonSize = 20.dp
private val NuanceInfoIconSize = 14.dp
private val SectionSpacing = 12.dp
private val PlaceholderCornerRadius = 12.dp
private val PlaceholderLineHeight = 12.dp
private val PlaceholderSmallLineWidth = 120.dp
private val PlaceholderMediumLineWidth = 180.dp
private val PlaceholderChipWidth = 150.dp
private val PlaceholderChipHeight = 36.dp
private val PlaceholderBadgeWidth = 160.dp
private val PlaceholderBadgeHeight = 40.dp

@Composable
internal fun WordCardContent(
    card: VocabularyCard,
    metrics: HomeScreenMetrics,
    onAudioPlay: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(metrics.cardPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(metrics.cardSpacing)
    ) {
        CardHeader(card.word, card.imageUrl, card.visualPrompt)

        PronunciationButton(onAudioPlay)

        ExampleSection(card.example)

        CardDivider()

        NuanceSection(
            insteadOf = card.insteadOf,
            targetWord = card.word,
            nuance = card.nuance
        )
    }
}

@Composable
internal fun LoadingWordCard(
    metrics: HomeScreenMetrics,
) {
    val transition = rememberInfiniteTransition(label = "word_card_loading")
    val alpha by transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.75f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900),
            repeatMode = RepeatMode.Reverse
        ),
        label = "word_card_loading_alpha"
    )

    val placeholderColor = Colors.primary200.copy(alpha = alpha)

    Column(
        modifier = Modifier.padding(metrics.cardPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(metrics.cardSpacing)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 5f)
                .clip(RoundedCornerShape(CardCornerRadius))
                .background(placeholderColor)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = Spacing.L)
                    .width(PlaceholderBadgeWidth)
                    .height(PlaceholderBadgeHeight)
                    .clip(RoundedCornerShape(BadgeCornerRadius))
                    .background(Colors.primary400.copy(alpha = alpha))
            )
        }

        Box(
            modifier = Modifier
                .width(PlaceholderChipWidth)
                .height(PlaceholderChipHeight)
                .clip(RoundedCornerShape(ChipCornerRadius))
                .background(Colors.primary050.copy(alpha = alpha))
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SectionSpacing)
        ) {
            Box(
                modifier = Modifier
                    .width(PlaceholderSmallLineWidth)
                    .height(PlaceholderLineHeight)
                    .clip(RoundedCornerShape(PlaceholderCornerRadius))
                    .background(placeholderColor)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(PlaceholderLineHeight)
                    .clip(RoundedCornerShape(PlaceholderCornerRadius))
                    .background(placeholderColor)
            )
            Box(
                modifier = Modifier
                    .width(PlaceholderMediumLineWidth)
                    .height(PlaceholderLineHeight)
                    .clip(RoundedCornerShape(PlaceholderCornerRadius))
                    .background(placeholderColor)
            )
        }

        CardDivider()

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SectionSpacing)
        ) {
            Box(
                modifier = Modifier
                    .width(PlaceholderSmallLineWidth)
                    .height(PlaceholderLineHeight)
                    .clip(RoundedCornerShape(PlaceholderCornerRadius))
                    .background(placeholderColor)
            )
            Box(
                modifier = Modifier
                    .width(PlaceholderMediumLineWidth)
                    .height(PlaceholderLineHeight)
                    .clip(RoundedCornerShape(PlaceholderCornerRadius))
                    .background(placeholderColor)
            )
        }
    }
}

@Composable
private fun CardHeader(
    word: String,
    imageUrl: String,
    imageDescription: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4f / 5f)
            .clip(RoundedCornerShape(CardCornerRadius))
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = imageDescription,
            contentScale = ContentScale.Crop,
            placeholder = ColorPainter(Colors.primary050),
            error = ColorPainter(Colors.primary050),
            modifier = Modifier.matchParentSize()
        )
        Text(
            text = word,
            style = LexicoFont.f300Highlight(color = Colors.supportLight),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Spacing.L)
                .background(
                    color = Colors.primary500,
                    shape = RoundedCornerShape(BadgeCornerRadius)
                )
                .padding(horizontal = Spacing.XL, vertical = Spacing.M)
        )
    }
}

@Composable
private fun PronunciationButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(ChipCornerRadius))
            .background(Colors.primary050)
            .border(
                ChipBorderWidth,
                Colors.primary200,
                RoundedCornerShape(ChipCornerRadius)
            )
            .clickable { onClick() }
            .padding(horizontal = Spacing.L, vertical = Spacing.M),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.M)
    ) {
        Icon(
            imageVector = Icons.Filled.VolumeUp,
            contentDescription = stringResource(Res.string.home_play_pronunciation),
            tint = Colors.primary500
        )
        Text(
            text = stringResource(Res.string.home_pronunciation),
            style = LexicoFont.f075Highlight(color = Colors.primary500),
        )
    }
}

@Composable
private fun ExampleSection(example: String) {
    Column(verticalArrangement = Arrangement.spacedBy(SectionSpacing)) {
        Text(
            text = stringResource(Res.string.home_example),
            style = LexicoFont.f075Highlight(color = Colors.primary500)
        )
        Text(
            text = example,
            style = LexicoFont.f100Default(color = Colors.support700)
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

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SectionSpacing)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(Res.string.home_nuance),
                style = LexicoFont.f075Highlight(color = Colors.primary500)
            )
            if (nuance.isNotBlank()) {
                Box {
                    NuanceButton {  isTooltipVisible.value = true }
                    NuanceTooltip(
                        isVisible = isTooltipVisible.value,
                        onDismissRequest = { isTooltipVisible.value = false },
                        nuanceText = nuance
                    )
                }
            }
        }

        Text(
            text = stringResource(Res.string.home_instead_of_format, insteadOf, targetWord),
            style = LexicoFont.f100Default(color = Colors.support900)
        )
    }
}

@Composable
private fun NuanceButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(NuanceInfoButtonSize)
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = stringResource(Res.string.home_show_nuance),
            tint = Colors.primary500,
            modifier = Modifier.size(NuanceInfoIconSize)
        )
    }
}

@Composable
private fun NuanceTooltip(
    isVisible: Boolean,
    onDismissRequest: () -> Unit,
    nuanceText: String
) {
    DropdownMenu(
        expanded = isVisible,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .background(Colors.supportLight)
            .padding(Spacing.M)
    ) {
        DropdownMenuItem(
            text = {
                Text(
                    text = nuanceText,
                    style = LexicoFont.f100Default(color = Colors.support900)
                )
            },
            onClick = onDismissRequest
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
@Preview(showBackground = true)
private fun WordCardContentShortScreenPreview() {
    WordCardContent(
        card = VocabularyCard(
            word = "Serendipity",
            example = "Finding that old book in the attic was pure serendipity.",
            insteadOf = "Chance",
            nuance = "Serendipity implies a fortunate discovery made by accident, often while looking for something else.",
            visualPrompt = "A person discovering a treasure chest in a forest.",
            imageUrl = "https://example.com/image.jpg",
            language = Language(code = "en", name = "English")
        ),
        metrics = metricsForHeight(600.dp),
        onAudioPlay = {}
    )
}


@Composable
@Preview(showBackground = true)
private fun WordCardContentTallScreenPreview() {
    WordCardContent(
        card = VocabularyCard(
            word = "Serendipity",
            example = "Finding that old book in the attic was pure serendipity.",
            insteadOf = "Chance",
            nuance = "Serendipity implies a fortunate discovery made by accident, often while looking for something else.",
            visualPrompt = "A person discovering a treasure chest in a forest.",
            imageUrl = "https://example.com/image.jpg",
            language = Language(code = "en", name = "English")
        ),
        metrics = metricsForHeight(800.dp),
        onAudioPlay = {}
    )
}

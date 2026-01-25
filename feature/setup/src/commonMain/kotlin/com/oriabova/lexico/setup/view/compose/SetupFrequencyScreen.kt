package com.oriabova.lexico.setup.view.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.setup.domain.model.SetupFrequency
import com.oriabova.lexico.theme.Colors
import com.oriabova.lexico.theme.LexicoFont
import com.oriabova.lexico.theme.LexicoTheme
import kotlinx.coroutines.delay
import lexico.libraries.setup.generated.resources.Res
import lexico.libraries.setup.generated.resources.setup_frequency_subtitle
import lexico.libraries.setup.generated.resources.setup_frequency_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val TitleToDescriptionSpacing = 6.dp
private val PaceToTitleSpacing = 8.dp

@Composable
internal fun SetupFrequencyScreen(onSetupFrequencyComplete: (SetupFrequency) -> Unit) {
    SetupScreenWrapper {
        Column(
            modifier = Modifier.padding(
                top = SetupUiDefaults.TopPadding,
                bottom = SetupUiDefaults.BottomPadding
            ),
            verticalArrangement = Arrangement.spacedBy(SetupUiDefaults.VerticalSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title()
            Subtitle()
            FrequencyPicker(
                modifier = Modifier.weight(1f),
                onFrequencyPicked = { onSetupFrequencyComplete(it) }
            )
        }
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(resource = Res.string.setup_frequency_title),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.d100(color = Colors.primary030),
        textAlign = TextAlign.Left
    )
}

@Composable
private fun Subtitle() {
    Text(
        text = stringResource(resource = Res.string.setup_frequency_subtitle),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.f100Default(color = Colors.primary030.copy(alpha = 0.9f)),
        textAlign = TextAlign.Left
    )
}

@Composable
private fun FrequencyPicker(
    modifier: Modifier = Modifier,
    onFrequencyPicked: (SetupFrequency) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        items(items = SetupFrequency.entries) { frequency ->
            val itemModifier = Modifier
                .fillMaxWidth()
                .padding(vertical = SetupUiDefaults.ItemSpacing)

            FrequencyItem(
                frequency = frequency,
                modifier = itemModifier,
                onSelected = { onFrequencyPicked(frequency) }
            )
        }
    }
}

@Composable
private fun FrequencyItem(
    frequency: SetupFrequency,
    modifier: Modifier = Modifier,
    onSelected: () -> Unit
) {
    var isSelected by remember { mutableStateOf(false) }

    LaunchedEffect(isSelected) {
        if (isSelected) {
            delay(SetupUiDefaults.SelectedItemAnimationDuration)
            onSelected()
        }
    }

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Colors.primary030 else Colors.supportLight,
        animationSpec = tween(durationMillis = 200),
        label = "background color"
    )
    val textColor = if (isSelected) Colors.primary700 else Colors.support700
    val chipBackgroundColor = if (isSelected) Colors.primary050 else Colors.support100

    Card(
        onClick = { isSelected = true },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(SetupUiDefaults.CardCornerRadius),
        border = BorderStroke(SetupUiDefaults.CardBorderWidth, Colors.primary400),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = SetupUiDefaults.CardInnerPaddingHorizontal,
                    vertical = SetupUiDefaults.CardInnerPaddingVertical
                ),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            FrequencyPaceChip(
                text = stringResource(frequency.paceRes),
                textColor = textColor,
                backgroundColor = chipBackgroundColor
            )
            Spacer(modifier = Modifier.height(PaceToTitleSpacing))
            Text(
                text = stringResource(frequency.titleRes),
                style = LexicoFont.f100Highlight(color = textColor),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(TitleToDescriptionSpacing))
            Text(
                text = stringResource(frequency.descriptionRes),
                style = LexicoFont.f075Default(color = textColor),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun FrequencyPaceChip(
    text: String,
    textColor: Color,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(50)
                )
                .padding(
                    horizontal = SetupUiDefaults.ChipPaddingHorizontal,
                    vertical = SetupUiDefaults.ChipPaddingVertical
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = LexicoFont.b075Default(color = textColor),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
private fun SetupFrequencyScreenPreview() {
    LexicoTheme { SetupFrequencyScreen { } }
}

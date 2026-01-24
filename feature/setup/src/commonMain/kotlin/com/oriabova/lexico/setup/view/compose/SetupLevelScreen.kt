package com.oriabova.lexico.setup.view.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.setup.domain.model.SetupLevel
import com.oriabova.lexico.theme.Colors
import com.oriabova.lexico.theme.LexicoFont
import com.oriabova.lexico.theme.LexicoTheme
import kotlinx.coroutines.delay
import lexico.libraries.designsystem.generated.resources.Res
import lexico.libraries.designsystem.generated.resources.setup_level_examples
import lexico.libraries.designsystem.generated.resources.setup_level_subtitle
import lexico.libraries.designsystem.generated.resources.setup_level_title
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val TitleToDescriptionSpacing = 6.dp
private val DescriptionToExampleSpacing = 10.dp
private val ExampleSpacing = 6.dp

@Composable
internal fun SetupLevelScreen(onSetupLevelComplete: (SetupLevel) -> Unit) {
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
            LevelPicker(
                modifier = Modifier.weight(1f),
                onLevelPicked = { onSetupLevelComplete(it) }
            )
        }
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(resource = Res.string.setup_level_title),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.d100(color = Colors.primary030),
        textAlign = TextAlign.Left
    )
}

@Composable
private fun Subtitle() {
    Text(
        text = stringResource(resource = Res.string.setup_level_subtitle),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.f100Default(color = Colors.primary030.copy(alpha = 0.9f)),
        textAlign = TextAlign.Left
    )
}

@Composable
private fun LevelPicker(
    modifier: Modifier = Modifier,
    onLevelPicked: (SetupLevel) -> Unit
) {
    val levelPickerState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = levelPickerState
    ) {
        items(items = SetupLevel.entries) { level ->
            val itemModifier = Modifier
                .fillMaxWidth()
                .padding(vertical = SetupUiDefaults.ItemSpacing)

            LevelItem(level, itemModifier) { onLevelPicked(level) }
        }
    }
}

@Composable
private fun LevelItem(
    level: SetupLevel,
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

    Card(
        onClick = { isSelected = true },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(SetupUiDefaults.CardCornerRadius),
        border = BorderStroke(SetupUiDefaults.CardBorderWidth, Colors.primary400),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
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
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(level.levelNameRes),
                    style = LexicoFont.f100Highlight(color = textColor),
                )
                Spacer(modifier = Modifier.height(TitleToDescriptionSpacing))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(level.descriptionRes),
                    style = LexicoFont.f075Default(color = textColor),
                )
                Spacer(modifier = Modifier.height(DescriptionToExampleSpacing))
                ExamplesRow(
                    examples = stringArrayResource(level.examplesRes).joinToString(", "),
                    textColor = textColor
                )
            }
        }
    }
}

@Composable
private fun ExamplesRow(examples: String, textColor: androidx.compose.ui.graphics.Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(ExampleSpacing)
    ) {
        Text(
            text = stringResource(resource = Res.string.setup_level_examples),
            style = LexicoFont.b075Default(color = textColor),
        )
        Text(
            text = examples,
            style = LexicoFont.f075Default(color = textColor),
        )
    }
}

@Composable
@Preview
private fun SetupLevelScreenPreview() {
    LexicoTheme { SetupLevelScreen {} }
}

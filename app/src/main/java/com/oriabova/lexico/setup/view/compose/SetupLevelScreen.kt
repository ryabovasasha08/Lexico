package com.oriabova.lexico.setup.view.compose

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oriabova.app.R
import com.oriabova.lexico.root.view.theme.Colors
import com.oriabova.lexico.root.view.theme.LexicoFont
import com.oriabova.lexico.root.view.theme.LexicoTheme
import com.oriabova.lexico.setup.data.SetupLevel

private val TopPadding = 30.dp
private val ScreenVerticalSpacing = 16.dp
private val ButtonVerticalPadding = 36.dp
private val CardCornerRadius = 15.dp
private val BorderStrokeWidth = 1.dp
private val CardInnerPaddingHorizontal = 16.dp
private val CardInnerPaddingVertical = 16.dp
private val LevelItemPadding = 8.dp
private val SelectedLevelIconPadding = 16.dp
private val SelectedLevelIconWidth = 24.dp
private val LevelItemTitleToDescriptionSpacing = 4.dp
private val LevelItemDescriptionToExampleSpacing = 8.dp
private val LevelItemExampleHorizontalSpacing = 4.dp
private const val LevelItemExamplesSeparator = ", "

@Composable
fun SetupLevelScreen(onSetupLevelComplete: (SetupLevel?) -> Unit) {
    var selectedLevel by remember { mutableStateOf<SetupLevel?>(null) }

    SetupScreenWrapper {
        Column(
            modifier = Modifier.padding(top = TopPadding, bottom = ButtonVerticalPadding),
            verticalArrangement = Arrangement.spacedBy(ScreenVerticalSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title()
            Subtitle()
            LevelPicker(
                selectedLevel = selectedLevel,
                modifier = Modifier.weight(1f),
                onLevelPicked = { selectedLevel = it }
            )

            CtaButton(
                isEnabled = selectedLevel != null,
                onClick = { onSetupLevelComplete(selectedLevel) }
            )
        }
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(id = R.string.setup_level_title),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.d100(color = Colors.ColorPrimaryLight),
        textAlign = TextAlign.Left
    )
}

@Composable
private fun Subtitle() {
    Text(
        text = stringResource(id = R.string.setup_level_subtitle),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.f100Default(color = Colors.ColorPrimaryLight),
        textAlign = TextAlign.Left
    )
}


@Composable
private fun CtaButton(isEnabled: Boolean, onClick: () -> Unit) {
    TextButton(
        text = stringResource(id = R.string.setup_level_cta_text),
        isEnabled = isEnabled,
        onClick = onClick,
    )
}

@Composable
private fun LevelPicker(
    selectedLevel: SetupLevel?,
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
                .padding(vertical = LevelItemPadding)
            val isSelected = selectedLevel == level

            LevelItem(level, isSelected, itemModifier) { onLevelPicked(level) }
        }
    }
}

@Composable
private fun LevelItem(
    level: SetupLevel,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Colors.ColorPrimaryLight,
            disabledContainerColor = Colors.ColorPrimaryLight,
            contentColor = Colors.ColorSupportDark,
            disabledContentColor = Colors.ColorSupportDark,
        ),
        shape = RoundedCornerShape(CardCornerRadius),
        border = BorderStroke(BorderStrokeWidth, Colors.ColorPrimaryDark),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = CardInnerPaddingHorizontal,
                        vertical = CardInnerPaddingVertical
                    )
                    .padding(end = SelectedLevelIconPadding + SelectedLevelIconWidth), // additional padding for the check mark icon
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(level.levelNameRes),
                    style = LexicoFont.f100Highlight(color = Colors.ColorPrimaryDark),
                )
                Spacer(modifier = Modifier.height(LevelItemTitleToDescriptionSpacing))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(level.descriptionRes),
                    style = LexicoFont.f075Default(color = Colors.ColorPrimaryDark),
                )
                Spacer(modifier = Modifier.height(LevelItemDescriptionToExampleSpacing))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(LevelItemExampleHorizontalSpacing)
                ) {
                    Text(
                        text = "Examples:",
                        style = LexicoFont.b075Default(color = Colors.ColorPrimaryDark),
                    )
                    Text(
                        text = stringArrayResource(level.examplesRes).joinToString(
                            LevelItemExamplesSeparator
                        ),
                        style = LexicoFont.f075Default(color = Colors.ColorPrimaryDark),
                    )
                }
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Colors.ColorPrimaryDark,
                    modifier = Modifier
                        .padding(end = SelectedLevelIconPadding)
                        .align(Alignment.CenterEnd)
                )
            }
        }
    }
}

@Composable
@Preview
private fun SetupLevelScreenPreview() {
    LexicoTheme { SetupLevelScreen {} }
}
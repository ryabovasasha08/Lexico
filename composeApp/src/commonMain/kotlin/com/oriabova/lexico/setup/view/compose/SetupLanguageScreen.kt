package com.oriabova.lexico.setup.view.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.theme.Colors
import com.oriabova.lexico.theme.LexicoFont
import com.oriabova.lexico.theme.LexicoTheme
import com.oriabova.lexico.utils.getAvailableLanguages
import kotlinx.coroutines.delay
import lexico.composeapp.generated.resources.Res
import lexico.composeapp.generated.resources.setup_language_empty_state
import lexico.composeapp.generated.resources.setup_language_search_hint
import lexico.composeapp.generated.resources.setup_language_subtitle
import lexico.composeapp.generated.resources.setup_language_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SetupLanguageScreen(onSetupLanguageComplete: (String) -> Unit) {
    var mask: String by remember { mutableStateOf("") }
    val languages = remember { getAvailableLanguages().sorted() }

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
            SearchField(mask) { mask = it }
            LanguagePicker(
                mask = mask,
                languages = languages,
                modifier = Modifier.weight(1f),
                onLanguagePicked = { onSetupLanguageComplete(it) }
            )
        }
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(resource = Res.string.setup_language_title),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.d100(color = Colors.primary030),
        textAlign = TextAlign.Left
    )
}

@Composable
private fun Subtitle() {
    Text(
        text = stringResource(resource = Res.string.setup_language_subtitle),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.f100Default(color = Colors.primary030.copy(alpha = 0.9f)),
        textAlign = TextAlign.Left
    )
}

@Composable
private fun SearchField(text: String, onTextChange: (String) -> Unit) {
    DecorationBox(textAlignment = Alignment.CenterVertically) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(resource = Res.string.setup_language_search_hint)) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Colors.supportLight,
                unfocusedContainerColor = Colors.supportLight,
                focusedIndicatorColor = Colors.primary400,
                unfocusedIndicatorColor = Colors.support200,
                cursorColor = Colors.primary400
            ),
            shape = RoundedCornerShape(SetupUiDefaults.CardCornerRadius),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            singleLine = true,
            textStyle = LexicoFont.f100Default(Colors.support700)
        )
    }
}

@Composable
private fun DecorationBox(
    textAlignment: Alignment.Vertical,
    textField: @Composable () -> Unit,
) {
    Row {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp)
                .align(textAlignment),
            contentAlignment = Alignment.CenterStart
        ) {
            textField()
        }
    }
}

@Composable
private fun LanguagePicker(
    mask: String,
    languages: List<String>,
    modifier: Modifier = Modifier,
    onLanguagePicked: (String) -> Unit
) {
    val locales = remember(languages, mask) {
        languages
            .filterNot { it.isBlank() }
            .filter { it.contains(mask, ignoreCase = true) }
            .sorted()
    }

    val languagePickerState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = languagePickerState
    ) {
        items(
            items = locales,
        ) { language ->
            val itemModifier = Modifier
                .fillMaxWidth()
                .padding(vertical = SetupUiDefaults.ItemSpacing)

            LanguageItem(
                language = language,
                modifier = itemModifier
            ) { onLanguagePicked(language) }
        }

        if (locales.isEmpty()) {
            item {
                Text(
                    text = stringResource(resource = Res.string.setup_language_empty_state),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    style = LexicoFont.f075Default(color = Colors.primary030.copy(alpha = 0.9f)),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun LanguageItem(
    language: String,
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
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = Colors.support500,
        ),
        shape = RoundedCornerShape(SetupUiDefaults.CardCornerRadius),
        border = BorderStroke(SetupUiDefaults.CardBorderWidth, Colors.primary400),
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = SetupUiDefaults.CardInnerPaddingHorizontal,
                        vertical = SetupUiDefaults.CardInnerPaddingVertical
                    ),
                text = language,
                style = LexicoFont.f100Default(textColor),
                textAlign = TextAlign.Center,
            )
        }

    }
}

@Composable
@Preview
private fun SetupLanguageScreenPreview() {
    LexicoTheme { SetupLanguageScreen {} }
}

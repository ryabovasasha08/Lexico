package com.oriabova.lexico.setup.view.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import lexico.composeapp.generated.resources.setup_language_input_hint
import lexico.composeapp.generated.resources.setup_language_subtitle
import lexico.composeapp.generated.resources.setup_language_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val TopPadding = 30.dp
private val CardCornerRadius = 15.dp
private val BorderStrokeWidth = 1.dp
private val CardInnerPaddingHorizontal = 16.dp
private val CardInnerPaddingVertical = 8.dp
private val ButtonVerticalPadding = 36.dp
private val LanguageItemPadding = 8.dp
private const val SelectedItemAnimationDuration = 300L

@Composable
fun SetupLanguageScreen(onSetupLanguageComplete: (String?) -> Unit) {
    var mask: String by remember { mutableStateOf("") }
    val languages = remember { getAvailableLanguages().sorted() }

    SetupScreenWrapper {
        Column(
            modifier = Modifier.padding(top = TopPadding, bottom = ButtonVerticalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title()
            Spacer(modifier = Modifier.height(4.dp))
            Subtitle()
            Spacer(modifier = Modifier.height(16.dp))
            SearchField(mask) { mask = it }
            Spacer(modifier = Modifier.height(16.dp))
            LanguagePicker(
                mask = mask,
                languages = languages,
                modifier = Modifier.weight(1f),
                onLanguagePicked = { onSetupLanguageComplete(it) }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(resource = Res.string.setup_language_title),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.b200Default(color = Colors.primary020),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Subtitle() {
    Text(
        text = stringResource(resource = Res.string.setup_language_subtitle),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.f100Default(color = Colors.primary020.copy(alpha = 0.7f)),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun SearchField(text: String, onTextChange: (String) -> Unit) {
    DecorationBox(textAlignment = Alignment.CenterVertically) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(resource = Res.string.setup_language_input_hint)) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Colors.supportLight,
                unfocusedContainerColor = Colors.supportLight,
            ),
            shape = RoundedCornerShape(CardCornerRadius),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
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
                .padding(vertical = 12.dp)
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
                .padding(vertical = LanguageItemPadding)

            LanguageItem(
                language,
                itemModifier
            ) { onLanguagePicked(language) }
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
            delay(SelectedItemAnimationDuration)
            onSelected()
        }
    }

    val backgroundColor by animateColorAsState( // Use animateColorAsState
        targetValue = if (isSelected) Colors.primary030 else Colors.primary200, // Slightly darker
        animationSpec = tween(durationMillis = 200), // Short animation
        label = "background color"
    )

    val textColor = if (isSelected) Colors.support700 else Colors.support500

    Card(
        onClick = { isSelected = true },
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = Colors.support500,
        ),
        shape = RoundedCornerShape(CardCornerRadius),
        border = BorderStroke(BorderStrokeWidth, Colors.primary500),
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = CardInnerPaddingHorizontal,
                        vertical = CardInnerPaddingVertical
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
package com.oriabova.lexico.setup.view.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oriabova.app.R
import com.oriabova.lexico.root.view.theme.Colors
import com.oriabova.lexico.root.view.theme.LexicoFont
import com.oriabova.lexico.root.view.theme.LexicoTheme
import java.util.Locale

private val TopPadding = 30.dp
private val ScreenVerticalSpacing = 16.dp
private val CardCornerRadius = 15.dp
private val BorderStrokeWidth = 1.dp
private val CardInnerPaddingHorizontal = 16.dp
private val CardInnerPaddingVertical = 8.dp
private val ButtonVerticalPadding = 36.dp
private val LanguageItemPadding = 8.dp
private val SelectedLanguageIconPadding = 16.dp

@Composable
fun SetupLanguageScreen(onSetupLanguageComplete: (String?) -> Unit) {
    var selectedLanguage by remember { mutableStateOf<String?>(null) }

    SetupScreenWrapper {
        Column(
            modifier = Modifier.padding(top = TopPadding, bottom = ButtonVerticalPadding),
            verticalArrangement = Arrangement.spacedBy(ScreenVerticalSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title()
            Subtitle()
            LanguagePicker(
                selectedLanguage = selectedLanguage,
                modifier = Modifier.weight(1f),
                onLanguagePicked = { selectedLanguage = it }
            )

            CtaButton(
                isEnabled = selectedLanguage.isNullOrBlank().not(),
                onClick = { onSetupLanguageComplete(selectedLanguage) }
            )
        }
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(id = R.string.setup_language_title),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.d100(color = Colors.primary030),
        textAlign = TextAlign.Left
    )
}

@Composable
private fun Subtitle() {
    Text(
        text = stringResource(id = R.string.setup_language_subtitle),
        modifier = Modifier.fillMaxWidth(),
        style = LexicoFont.f100Default(color = Colors.primary030),
        textAlign = TextAlign.Left
    )
}

@Composable
private fun CtaButton(isEnabled: Boolean, onClick: () -> Unit) {
    TextButton(
        text = stringResource(id = R.string.setup_language_cta_text),
        isEnabled = isEnabled,
        onClick = onClick,
    )
}

@Composable
private fun LanguagePicker(
    selectedLanguage: String?,
    modifier: Modifier = Modifier,
    onLanguagePicked: (String) -> Unit
) {
    val locales = Locale.getAvailableLocales()
        .map { it.displayLanguage }
        .distinct()
        .filterNot { it.isBlank() }
        .sorted()

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
            val isSelected = selectedLanguage == language

            LanguageItem(
                language,
                isSelected,
                itemModifier
            ) { onLanguagePicked(language) }
        }
    }
}

@Composable
private fun LanguageItem(
    language: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Colors.primary030,
            disabledContainerColor = Colors.primary030,
            contentColor = Colors.support500,
            disabledContentColor = Colors.support500,
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
                style = LexicoFont.f100Default(color = Colors.primary500),
                textAlign = TextAlign.Center,
            )

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Colors.primary500,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = SelectedLanguageIconPadding)
                )
            }
        }

    }
}

@Composable
@Preview
private fun SetupLanguageScreenPreview() {
    LexicoTheme { SetupLanguageScreen {} }
}
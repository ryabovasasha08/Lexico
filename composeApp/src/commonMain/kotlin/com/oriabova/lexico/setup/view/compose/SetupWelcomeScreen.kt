package com.oriabova.lexico.setup.view.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.theme.Colors
import com.oriabova.lexico.theme.LexicoFont
import com.oriabova.lexico.theme.LexicoTheme
import lexico.composeapp.generated.resources.Res
import lexico.composeapp.generated.resources.setup_welcome_cards
import lexico.composeapp.generated.resources.setup_welcome_cta_text
import lexico.composeapp.generated.resources.setup_welcome_title
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private const val AnimationDuration = 1000
private val OffsetDp = 100.dp
private const val CardsAnimationHeightFraction = 0.6f
private val ButtonVerticalPadding = 36.dp

@Composable
internal fun SetupWelcomeScreen(onButtonClick: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { visible = true }

    SetupScreenWrapper {
        WordCardsAnimation(
            visible = visible,
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(CardsAnimationHeightFraction)
                .align(Alignment.TopCenter)
        )

        TitleAndButtonAnimation(
            visible = visible,
            modifier = Modifier
                .padding(bottom = ButtonVerticalPadding)
                .align(Alignment.BottomCenter),
            onButtonClick = onButtonClick
        )
    }
}

@Composable
private fun TitleAndButtonAnimation(
    visible: Boolean,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = AnimationDuration,
                easing = LinearOutSlowInEasing
            )
        )
    ) {
        TitleAndButton(onButtonClick)
    }
}

@Composable
private fun TitleAndButton(onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(ButtonVerticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(resource = Res.string.setup_welcome_title),
            style = LexicoFont.d200(color = Colors.primary030),
            textAlign = TextAlign.Center,
        )

        TextButton(
            text = stringResource(resource = Res.string.setup_welcome_cta_text),
            onClick = onButtonClick
        )
    }
}

@Composable
private fun WordCardsAnimation(
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    val offsetPx = OffsetDp.value * LocalDensity.current.density
    val words = stringArrayResource(Res.array.setup_welcome_cards)

    Row(modifier = modifier) {
        repeat(words.size) {
            val offsetMultiplier = if (it % 2 == 0) 1 else -1
            val enterTransition = fadeIn(
                animationSpec = tween(
                    durationMillis = AnimationDuration,
                    easing = LinearOutSlowInEasing
                )
            ) + slideInVertically(
                initialOffsetY = { (offsetMultiplier * offsetPx).toInt() },
                animationSpec = tween(AnimationDuration, easing = LinearOutSlowInEasing)
            )

            AnimatedVisibility(
                visible = visible,
                enter = enterTransition,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .align(Alignment.CenterVertically),
            ) {
                WordCard(
                    text = words[it],
                    modifier = Modifier.offset(y = (offsetMultiplier * (-0.5) * OffsetDp.value).dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSetupWelcomeScreen() {
    LexicoTheme {
        SetupWelcomeScreen { }
    }
}
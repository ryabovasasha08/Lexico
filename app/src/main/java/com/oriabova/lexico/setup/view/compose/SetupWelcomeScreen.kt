package com.oriabova.lexico.setup.view.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.root.view.theme.Colors
import com.oriabova.lexico.root.view.theme.LexicoTheme
import kotlinx.coroutines.delay

private const val AnimationDuration = 1000
private val offsetDp = 100.dp
private val WordsList = listOf("Le", "Xi", "Co")

@Composable
internal fun SetupWelcomeScreen(onButtonClick: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(AnimationDuration.toLong())
        visible = true
    }
    Scaffold(
        containerColor = Colors.ColorPrimaryDark,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                WordCardsAnimation(
                    words = WordsList,
                    visible = visible,
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                        .align(Alignment.TopCenter)
                )
            }
        })
}

@Composable
private fun WordCardsAnimation(
    words: List<String>,
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    val offsetPx = offsetDp.value * LocalDensity.current.density

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
                    modifier = Modifier.offset(y = (offsetMultiplier * (-0.5) * offsetDp.value).dp)
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
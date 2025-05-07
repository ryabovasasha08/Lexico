package com.oriabova.lexico.setup.view.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.root.view.theme.Colors

private val ScreenPadding = 16.dp

@Composable
internal fun SetupScreenWrapper(content: @Composable BoxScope.() -> Unit) {
    Scaffold(
        containerColor = Colors.primary500,
        contentWindowInsets = WindowInsets(0),
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(ScreenPadding)
            ) {
                content()
            }
        })
}
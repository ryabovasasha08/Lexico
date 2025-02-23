package com.oriabova.lexico.setup.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oriabova.lexico.root.view.theme.Colors

@Composable
fun SetupFrequencyScreen(onSetupFrequencyComplete: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.ColorPrimaryDark)
    ) {
        Text("Setup Frequency Screen", color = Colors.ColorPrimaryLight)
    }
}
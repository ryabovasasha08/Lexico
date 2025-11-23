package com.oriabova.lexico

import androidx.compose.runtime.Composable
import com.oriabova.lexico.navigation.AppNavHost
import com.oriabova.lexico.theme.LexicoTheme

@Composable
fun App() {
    LexicoTheme {
        AppNavHost()
    }
}
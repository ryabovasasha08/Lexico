package com.oriabova.lexico.core

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oriabova.lexico.navigation.AppNavHost
import com.oriabova.lexico.theme.LexicoTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    appViewModel: AppViewModel = koinViewModel()
) {
    val uiState = appViewModel.uiState.collectAsStateWithLifecycle()

    LexicoTheme {
        AppNavHost(uiState.value.startDestination)
    }
}
package com.oriabova.lexico.core

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.setSingletonImageLoaderFactory
import com.oriabova.lexico.navigation.AppNavHost
import com.oriabova.lexico.theme.LexicoTheme
import com.oriabova.lexico.utils.createImageLoader
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    appViewModel: AppViewModel = koinViewModel()
) {
    val uiState = appViewModel.uiState.collectAsStateWithLifecycle()

    setSingletonImageLoaderFactory(::createImageLoader)
    LexicoTheme {
        AppNavHost(uiState.value.startDestination)
    }
}
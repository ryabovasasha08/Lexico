package com.oriabova.lexico.home.view.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oriabova.lexico.home.view.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val uiState = homeViewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState.value,
        handleUiEvent = homeViewModel::handleUiEvent
    )
}

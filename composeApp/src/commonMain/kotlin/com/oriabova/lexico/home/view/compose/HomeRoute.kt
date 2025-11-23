package com.oriabova.lexico.home.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.oriabova.lexico.home.view.HomeViewModel
import com.oriabova.lexico.setup.domain.SetupState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = koinViewModel(),
    navigateToSetup: () -> Unit
) {
    HomeScreen()
    val setupState by homeViewModel.setupStateFlow.collectAsState(SetupState.UNDEFINED)
    when (setupState) {
        SetupState.COMPLETED -> HomeScreen()
        SetupState.NOT_COMPLETED -> navigateToSetup()
        SetupState.UNDEFINED -> Unit
    }
}
package com.oriabova.lexico.home.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.oriabova.lexico.home.view.HomeViewModel
import com.oriabova.lexico.setup.domain.SetupState

@Composable
fun HomeRoute(homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>(), navigateToSetup: () -> Unit) {
    val setupState by homeViewModel.setupStateFlow.collectAsState(SetupState.UNDEFINED)
    when (setupState) {
        SetupState.COMPLETED -> HomeScreen()
        SetupState.NOT_COMPLETED -> navigateToSetup()
        SetupState.UNDEFINED -> Unit
    }
}
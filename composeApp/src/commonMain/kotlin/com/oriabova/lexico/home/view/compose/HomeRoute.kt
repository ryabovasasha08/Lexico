package com.oriabova.lexico.home.view.compose

import androidx.compose.runtime.Composable

@Composable
fun HomeRoute(
//    homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>(),
    isSplashScreenVisible: Boolean,
    navigateToSetup: () -> Unit
) {
    if (isSplashScreenVisible) return

    HomeScreen()
//    val setupState by homeViewModel.setupStateFlow.collectAsState(SetupState.UNDEFINED)
//    when (setupState) {
//        SetupState.COMPLETED -> HomeScreen()
//        SetupState.NOT_COMPLETED -> navigateToSetup()
//        SetupState.UNDEFINED -> Unit
//    }
}
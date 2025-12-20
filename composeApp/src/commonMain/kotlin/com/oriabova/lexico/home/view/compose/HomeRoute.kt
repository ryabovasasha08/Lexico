package com.oriabova.lexico.home.view.compose

import androidx.compose.runtime.Composable
import com.oriabova.lexico.home.view.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = koinViewModel()
) {
    HomeScreen()
}

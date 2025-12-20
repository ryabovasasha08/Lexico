package com.oriabova.lexico.core

import com.oriabova.lexico.navigation.NavigationItem

data class AppUiState(
    val showSplash: Boolean,
    val startDestination: NavigationItem,
)
package com.oriabova.lexico.navigation

sealed class NavigationItem(val route: String) {
    data object Setup : NavigationItem(Screen.SETUP.name)
    data object Home : NavigationItem(Screen.HOME.name)
}

enum class Screen {
    SETUP,
    HOME,
}
package com.oriabova.lexico.root.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oriabova.lexico.home.view.compose.HomeRoute
import com.oriabova.lexico.root.view.model.NavigationItem
import com.oriabova.lexico.setup.view.compose.SetupScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            HomeRoute(
                navigateToSetup = { navController.navigate(NavigationItem.Setup.route) },
            )
        }
        composable(NavigationItem.Setup.route) {
            SetupScreen(
                popBackStack = { navController.popBackStack() }
            )
        }
    }
}
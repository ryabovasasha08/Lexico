package com.oriabova.lexico.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oriabova.lexico.home.view.compose.HomeRoute
import com.oriabova.lexico.setup.view.compose.SetupScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItem.Home.route
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
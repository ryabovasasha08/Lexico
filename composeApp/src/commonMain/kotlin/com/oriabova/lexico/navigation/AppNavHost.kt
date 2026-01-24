package com.oriabova.lexico.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oriabova.lexico.home.view.compose.HomeRoute
import com.oriabova.lexico.setup.view.compose.SetupRoute

@Composable
fun AppNavHost(
    startDestination: NavigationItem,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(NavigationItem.Home.route) {
            HomeRoute()
        }
        composable(NavigationItem.Setup.route) {
            SetupRoute(
                navigateToHome = {
                    navController.navigate(NavigationItem.Home.route) {
                        popUpTo(NavigationItem.Setup.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

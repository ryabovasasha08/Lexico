package com.oriabova.lexico.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.oriabova.lexico.root.view.compose.AppNavHost
import com.oriabova.lexico.root.view.theme.LexicoTheme
import com.oriabova.lexico.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSplashScreen()
        enableEdgeToEdge()
        setContent {
            LexicoTheme {
                AppNavHost(navController = rememberNavController())
            }
        }
    }

    private fun setupSplashScreen() {
        val splashscreen = installSplashScreen()
        var keepSplashScreen = true
        splashscreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            splashViewModel.isSplashShow.collect { isSplashFlow ->
                if (!isSplashFlow) {
                    keepSplashScreen = false
                }
            }
        }
    }
}
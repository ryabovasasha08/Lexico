package com.oriabova.lexico.root

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.oriabova.lexico.navigation.AppNavHost
import com.oriabova.lexico.splash.SplashViewModel
import com.oriabova.lexico.theme.LexicoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContent {
            LexicoTheme {
                val isSplashScreenVisible by splashViewModel.isSplashShow.collectAsStateWithLifecycle()
                AppNavHost(
                    isSplashScreenVisible = isSplashScreenVisible,
                    navController = rememberNavController()
                )
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
package com.oriabova.lexico.splash

import androidx.lifecycle.ViewModel
import com.oriabova.lexico.splash.SplashConstants.ANIMATION_DURATION_SECONDS
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

private const val MILLIS_IN_SECONDS = 1000L

class SplashViewModel() : ViewModel() {
    val isSplashShow = flow {
        emit(true)
        delay(ANIMATION_DURATION_SECONDS * MILLIS_IN_SECONDS)
        emit(false)
    }
}
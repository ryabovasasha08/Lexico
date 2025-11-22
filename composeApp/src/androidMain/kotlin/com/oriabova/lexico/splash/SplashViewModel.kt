package com.oriabova.lexico.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.splash.SplashConstants.ANIMATION_DURATION_SECONDS
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val MILLIS_IN_SECONDS = 1000L

class SplashViewModel() : ViewModel() {
    private val splashShowFlow = MutableStateFlow(true)
    val isSplashShow = splashShowFlow.asStateFlow()

    init {
        viewModelScope.launch {
            delay(ANIMATION_DURATION_SECONDS * MILLIS_IN_SECONDS) // will be later replaced with necessary loadings
            splashShowFlow.value = false
        }
    }
}
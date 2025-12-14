package com.oriabova.lexico.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.navigation.NavigationItem
import com.oriabova.lexico.setup.domain.ObserveSetupStateUseCase
import com.oriabova.lexico.setup.domain.SetupState
import com.oriabova.lexico.splash.SplashConstants.ANIMATION_DURATION_SECONDS
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

private const val MILLIS_IN_SECONDS = 1000L

class AppViewModel(
    observeSetupStateUseCase: ObserveSetupStateUseCase,
) : ViewModel() {

    private val shouldDisplaySplash = flow {
        emit(true)
        delay(ANIMATION_DURATION_SECONDS * MILLIS_IN_SECONDS)
        emit(false)
    }

    val uiState: StateFlow<AppUiState> = combine(
        shouldDisplaySplash,
        observeSetupStateUseCase()
    ) { showSplash, setupState ->
        val startDestination = when (setupState) {
            SetupState.COMPLETED -> NavigationItem.Home
            SetupState.NOT_COMPLETED -> NavigationItem.Setup
        }

        AppUiState(
            showSplash = showSplash,
            startDestination = startDestination
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        AppUiState(
            showSplash = true,
            startDestination = NavigationItem.Setup
        )
    )
}
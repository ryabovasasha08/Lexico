package com.oriabova.lexico.setup.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.setup.domain.ObserveSetupDetailsUseCase
import com.oriabova.lexico.setup.domain.StoreSetupDetailsUseCase
import com.oriabova.lexico.setup.domain.model.SetupDetails
import com.oriabova.lexico.setup.domain.model.SetupFrequency
import com.oriabova.lexico.setup.domain.model.SetupLevel
import com.oriabova.lexico.setup.view.model.SetupState
import com.oriabova.lexico.setup.view.model.SetupUiEvent
import com.oriabova.lexico.utils.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class SetupViewModel(
    observeSetupDetailsUseCase: ObserveSetupDetailsUseCase,
    private val storeSetupDetailsUseCase: StoreSetupDetailsUseCase,
) : ViewModel() {

    private val isWelcomeCompleted = MutableStateFlow(false)
    private val setupDetails = observeSetupDetailsUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        SetupDetails.initial()
    )

    val setupState = combine(
        setupDetails,
        isWelcomeCompleted
    ) { setupDetails, isWelcomeCompleted -> getSetupState(setupDetails, isWelcomeCompleted) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            SetupState.WELCOME
        )

    fun handleUiEvent(uiEvent: SetupUiEvent) {
        when (uiEvent) {
            is SetupUiEvent.CompletedWelcome -> completeWelcomeScreen()
            is SetupUiEvent.LanguageSelected -> completeLanguageSetup(uiEvent.language)
            is SetupUiEvent.LevelSelected -> completeLevelSetup(uiEvent.level)
            is SetupUiEvent.FrequencySelected -> completeFrequencySetup(uiEvent.frequency)
            is SetupUiEvent.NotificationPermissionGranted -> completeNotificationPermission()
            is SetupUiEvent.SetupComplete -> { /* No-op */ }
        }
    }

    private fun completeWelcomeScreen() {
        isWelcomeCompleted.value = true
    }

    private fun completeLanguageSetup(language: Language) {
        storeSetupDetails(setupDetails.value.copy(languageToLearn = language))
    }

    private fun completeLevelSetup(level: SetupLevel) {
        storeSetupDetails(setupDetails.value.copy(level = level))
    }

    private fun completeFrequencySetup(frequency: SetupFrequency) {
        storeSetupDetails(setupDetails.value.copy(frequency = frequency))
    }

    private fun completeNotificationPermission() {
        storeSetupDetails(setupDetails.value.copy(notificationPermissionGranted = true))
    }

    private fun getSetupState(setupDetails: SetupDetails, isWelcomeCompleted: Boolean) = when {
        !isWelcomeCompleted -> SetupState.WELCOME
        setupDetails.languageToLearn == null -> SetupState.LANGUAGE_CHOICE
        setupDetails.level == null -> SetupState.LEVEL_CHOICE
        setupDetails.frequency == null -> SetupState.FREQUENCY_CHOICE
        !setupDetails.notificationPermissionGranted -> SetupState.NOTIFICATION_PERMISSION
        else -> SetupState.COMPLETE
    }

    private fun storeSetupDetails(newSetupDetails: SetupDetails) {
        viewModelScope.launch {
            storeSetupDetailsUseCase(newSetupDetails)
        }
    }

}

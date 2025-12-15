package com.oriabova.lexico.setup.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.setup.domain.StoreSetupDetailsUseCase
import com.oriabova.lexico.setup.domain.model.SetupDetails
import com.oriabova.lexico.setup.domain.model.SetupFrequency
import com.oriabova.lexico.setup.domain.model.SetupLevel
import com.oriabova.lexico.setup.view.model.SetupState
import com.oriabova.lexico.setup.view.model.SetupUiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SetupViewModel(
    private val storeSetupDetailsUseCase: StoreSetupDetailsUseCase,
) : ViewModel() {

    val setupState = MutableStateFlow(SetupState.WELCOME)
    private var setupDetails = SetupDetails()

    fun handleUiEvent(uiEvent: SetupUiEvent) {
        when (uiEvent) {
            is SetupUiEvent.CompletedWelcome -> openNextStep()
            is SetupUiEvent.LanguageSelected -> completeLanguageSetup(uiEvent.language)
            is SetupUiEvent.LevelSelected -> completeLevelSetup(uiEvent.level)
            is SetupUiEvent.FrequencySelected -> completeFrequencySetup(uiEvent.frequency)
        }
    }

    private fun completeLanguageSetup(language: String) {
        setupDetails = setupDetails.copy(languageToLearn = language)
        openNextStep()
    }

    private fun completeLevelSetup(level: SetupLevel) {
        setupDetails = setupDetails.copy(level = level)
        openNextStep()
    }

    private fun completeFrequencySetup(frequency: SetupFrequency) {
        setupDetails = setupDetails.copy(frequency = frequency)
        openNextStep()
    }

    private fun openNextStep() {
        val currentSetupState = setupState.value
        val newSetupState = SetupState.entries[currentSetupState.ordinal + 1]
        if (newSetupState == SetupState.COMPLETE) {
            saveSetupDetails()
        }
        setupState.value = newSetupState
    }

    private fun saveSetupDetails() {
        viewModelScope.launch {
            storeSetupDetailsUseCase(setupDetails)
        }
    }

}
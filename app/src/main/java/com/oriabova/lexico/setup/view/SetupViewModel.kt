package com.oriabova.lexico.setup.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.setup.data.SetupDetails
import com.oriabova.lexico.setup.domain.StoreSetupDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val storeSetupDetailsUseCase: StoreSetupDetailsUseCase,
) : ViewModel() {

    val setupState = MutableStateFlow(SetupState.Welcome)
    private var setupDetails = SetupDetails("", "", "")

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

    private fun completeLevelSetup(level: String) {
        setupDetails = setupDetails.copy(level = level)
        openNextStep()
    }

    private fun completeFrequencySetup(frequency: String) {
        setupDetails = setupDetails.copy(frequency = frequency)
        openNextStep()
    }

    private fun openNextStep() {
        val currentSetupState = setupState.value
        val newSetupState = SetupState.entries[currentSetupState.ordinal + 1]
        if (newSetupState == SetupState.Complete) {
            saveSetupDetails()
        }
        setupState.value = newSetupState
    }

    private fun saveSetupDetails() {
        viewModelScope.launch {
            storeSetupDetailsUseCase(SetupDetails("UA", "Advanced", "5/day"))
        }
    }

}

// Order is important
enum class SetupState {
    Welcome,
    Language_Choice,
    Level_Choice,
    Frequency_Choice,
    Complete
}

sealed class SetupUiEvent {
    data object CompletedWelcome : SetupUiEvent()
    data class LanguageSelected(val language: String) : SetupUiEvent()
    data class LevelSelected(val level: String) : SetupUiEvent()
    data class FrequencySelected(val frequency: String) : SetupUiEvent()
}
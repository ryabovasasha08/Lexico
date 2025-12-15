package com.oriabova.lexico.setup.view.model

import com.oriabova.lexico.setup.domain.model.SetupFrequency
import com.oriabova.lexico.setup.domain.model.SetupLevel

sealed class SetupUiEvent {
    data object CompletedWelcome : SetupUiEvent()
    data class LanguageSelected(val language: String) : SetupUiEvent()
    data class LevelSelected(val level: SetupLevel) : SetupUiEvent()
    data class FrequencySelected(val frequency: SetupFrequency) : SetupUiEvent()
}
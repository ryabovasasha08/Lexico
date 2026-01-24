package com.oriabova.lexico.setup.view.model

import com.oriabova.lexico.setup.domain.model.SetupFrequency
import com.oriabova.lexico.setup.domain.model.SetupLevel
import com.oriabova.lexico.utils.Language

internal sealed class SetupUiEvent {
    data object CompletedWelcome : SetupUiEvent()
    data class LanguageSelected(val language: Language) : SetupUiEvent()
    data class LevelSelected(val level: SetupLevel) : SetupUiEvent()
    data class FrequencySelected(val frequency: SetupFrequency) : SetupUiEvent()
    data object NotificationPermissionGranted : SetupUiEvent()
    data object SetupComplete : SetupUiEvent()
}

package com.oriabova.lexico.setup.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oriabova.lexico.setup.view.SetupState
import com.oriabova.lexico.setup.view.SetupUiEvent
import com.oriabova.lexico.setup.view.SetupViewModel

@Composable
fun SetupScreen(
    setupViewModel: SetupViewModel = hiltViewModel<SetupViewModel>(),
    popBackStack: () -> Unit
) {
    val setupState by setupViewModel.setupState.collectAsStateWithLifecycle()

    when (setupState) {
        SetupState.Welcome -> SetupWelcomeScreen {
            setupViewModel.handleUiEvent(SetupUiEvent.CompletedWelcome)
        }

        SetupState.Language_Choice -> SetupLanguageScreen { language ->
            setupViewModel.handleUiEvent(SetupUiEvent.LanguageSelected(language))
        }

        SetupState.Level_Choice -> SetupLevelScreen { level ->
            setupViewModel.handleUiEvent(SetupUiEvent.LevelSelected(level))
        }

        SetupState.Frequency_Choice -> SetupFrequencyScreen { frequency ->
            setupViewModel.handleUiEvent(SetupUiEvent.FrequencySelected(frequency))
        }

        SetupState.Complete -> popBackStack()
    }
}
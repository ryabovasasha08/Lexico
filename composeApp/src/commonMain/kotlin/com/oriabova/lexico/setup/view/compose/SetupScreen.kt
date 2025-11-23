package com.oriabova.lexico.setup.view.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oriabova.lexico.setup.view.SetupState
import com.oriabova.lexico.setup.view.SetupUiEvent
import com.oriabova.lexico.setup.view.SetupViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SetupScreen(
    setupViewModel: SetupViewModel = koinViewModel(),
    popBackStack: () -> Unit
) {
    val setupState by setupViewModel.setupState.collectAsStateWithLifecycle()

    AnimatedContent(
        targetState = setupState,
        label = "SetupScreen",
        transitionSpec = { fadeIn().togetherWith(fadeOut()) }
    ) { state ->
        when (state) {
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
}
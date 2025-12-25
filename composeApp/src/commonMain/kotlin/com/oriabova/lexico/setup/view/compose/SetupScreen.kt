package com.oriabova.lexico.setup.view.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oriabova.lexico.setup.view.SetupViewModel
import com.oriabova.lexico.setup.view.model.SetupState
import com.oriabova.lexico.setup.view.model.SetupUiEvent
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
            SetupState.WELCOME -> SetupWelcomeScreen {
                setupViewModel.handleUiEvent(SetupUiEvent.CompletedWelcome)
            }

            SetupState.LANGUAGE_CHOICE -> SetupLanguageScreen { language ->
                setupViewModel.handleUiEvent(SetupUiEvent.LanguageSelected(language))
            }

            SetupState.LEVEL_CHOICE -> SetupLevelScreen { level ->
                setupViewModel.handleUiEvent(SetupUiEvent.LevelSelected(level))
            }

            SetupState.FREQUENCY_CHOICE -> SetupFrequencyScreen { frequency ->
                setupViewModel.handleUiEvent(SetupUiEvent.FrequencySelected(frequency))
            }

            SetupState.NOTIFICATION_PERMISSION -> SetupNotificationPermissionScreen {
                    setupViewModel.handleUiEvent(SetupUiEvent.NotificationPermissionGranted)
            }

            SetupState.COMPLETE -> popBackStack()
        }
    }
}

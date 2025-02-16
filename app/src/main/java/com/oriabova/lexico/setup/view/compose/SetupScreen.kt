package com.oriabova.lexico.setup.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oriabova.lexico.setup.view.SetupState
import com.oriabova.lexico.setup.view.SetupViewModel

@Composable
fun SetupScreen(
    setupViewModel: SetupViewModel = hiltViewModel<SetupViewModel>(),
    popBackStack: () -> Unit
) {
    val setupState by setupViewModel.setupState.collectAsStateWithLifecycle()

    when (setupState) {
        SetupState.Welcome -> SetupWelcomeScreen {
            setupViewModel.saveSetup()
            popBackStack()
        }

        else -> {}
    }// NOT IMPLEMENTED
}
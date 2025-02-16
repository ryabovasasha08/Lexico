package com.oriabova.lexico.setup.view.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.oriabova.lexico.setup.view.SetupViewModel

@Composable
fun SetupScreen(
    setupViewModel: SetupViewModel = hiltViewModel<SetupViewModel>(),
    popBackStack: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Setup Screen")
        Button(onClick = {
            setupViewModel.saveSetup()
            popBackStack()
        }) {
            Text(text = "Save Setup")
        }
    }
}
package com.oriabova.lexico.home.view.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oriabova.lexico.root.view.theme.LexicoTheme

@Composable
fun HomeScreen() {
   Box(
         contentAlignment = Alignment.Center,
         modifier = Modifier.fillMaxSize()
    ) {
         Text(text = "Home Screen")
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    LexicoTheme {
        HomeScreen()
    }
}
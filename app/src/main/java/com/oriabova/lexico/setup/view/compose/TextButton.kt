package com.oriabova.lexico.setup.view.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.root.view.theme.Colors
import com.oriabova.lexico.root.view.theme.LexicoFont

@Composable
internal fun TextButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Colors.ColorPrimaryLight,
            contentColor = Colors.ColorPrimaryDark,
        ),
        content = {
            Text(
                text = text,
                style = LexicoFont.f200Highlight(color = Colors.ColorPrimaryDark),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )
        }
    )
}
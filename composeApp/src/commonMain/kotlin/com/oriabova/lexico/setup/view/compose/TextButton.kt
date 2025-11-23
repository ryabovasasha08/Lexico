package com.oriabova.lexico.setup.view.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oriabova.lexico.root.view.theme.Colors
import com.oriabova.lexico.theme.LexicoFont

private const val DisabledAlpha = 0.3f

@Composable
internal fun TextButton(text: String, isEnabled: Boolean = true, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Colors.primary030,
            contentColor = Colors.primary500,
            disabledContentColor = Colors.primary500.copy(DisabledAlpha),
            disabledContainerColor = Colors.primary030.copy(DisabledAlpha),
        ),
        content = {
            Text(
                text = text,
                style = LexicoFont.f200Highlight(color = Colors.primary500),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )
        }
    )
}
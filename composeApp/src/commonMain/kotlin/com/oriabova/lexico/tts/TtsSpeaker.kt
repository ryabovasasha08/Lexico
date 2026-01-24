package com.oriabova.lexico.tts

import androidx.compose.runtime.Composable

interface TtsSpeaker {
    fun speak(text: String, localeTag: String)
    fun shutdown()
}

@Composable
expect fun rememberTtsSpeaker(): TtsSpeaker

package com.oriabova.lexico.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

private class AndroidTtsSpeaker(
    context: Context,
) : TtsSpeaker, TextToSpeech.OnInitListener {
    private val textToSpeech = TextToSpeech(context, this)
    private var isReady = false
    private var pendingText: String? = null
    private var pendingLocaleTag: String? = null

    override fun onInit(status: Int) {
        isReady = status == TextToSpeech.SUCCESS
        if (isReady) {
            pendingText?.let { text ->
                speak(text, pendingLocaleTag ?: Locale.getDefault().toLanguageTag())
                pendingText = null
                pendingLocaleTag = null
            }
        }
    }

    override fun speak(text: String, localeTag: String) {
        if (!isReady) {
            pendingText = text
            pendingLocaleTag = localeTag
            return
        }
        textToSpeech.language = Locale.forLanguageTag(localeTag)
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "lexico-tts")
    }

    override fun shutdown() {
        textToSpeech.shutdown()
    }
}

@Composable
actual fun rememberTtsSpeaker(): TtsSpeaker {
    val context = LocalContext.current
    val speaker = remember(context) { AndroidTtsSpeaker(context) }
    DisposableEffect(speaker) {
        onDispose { speaker.shutdown() }
    }
    return speaker
}

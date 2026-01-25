package com.oriabova.lexico.tts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import platform.AVFAudio.AVSpeechBoundary
import platform.AVFAudio.AVSpeechSynthesisVoice
import platform.AVFAudio.AVSpeechSynthesizer
import platform.AVFAudio.AVSpeechUtterance
import platform.AVFAudio.AVSpeechUtteranceDefaultSpeechRate

private class IosTtsSpeaker : TtsSpeaker {
    private val synthesizer = AVSpeechSynthesizer()

    override fun speak(text: String, localeTag: String) {
        val utterance = AVSpeechUtterance.speechUtteranceWithString(text)
        utterance.rate = AVSpeechUtteranceDefaultSpeechRate
        AVSpeechSynthesisVoice.voiceWithLanguage(localeTag)?.let { utterance.voice = it }
        synthesizer.speakUtterance(utterance)
    }

    override fun shutdown() {
        synthesizer.stopSpeakingAtBoundary(AVSpeechBoundary.AVSpeechBoundaryImmediate)
    }
}

@Composable
actual fun rememberTtsSpeaker(): TtsSpeaker {
    val speaker = remember { IosTtsSpeaker() }
    DisposableEffect(speaker) {
        onDispose { speaker.shutdown() }
    }
    return speaker
}

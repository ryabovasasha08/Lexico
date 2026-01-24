package com.oriabova.lexico.ai.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GeneratedWord(
    val word: String,
    val translation: String,
    val example: String,
    val partOfSpeech: String,
    val ipa: String,
)

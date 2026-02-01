package com.oriabova.lexico.home.data.model

import com.oriabova.lexico.utils.Language
import kotlinx.serialization.Serializable

@Serializable
internal data class GeneratedWordEntity(
    val word: String,
    val wordPronunciation: String,
    val translation: String,
    val example: String,
    val exampleTranslation: String,
    val insteadOf: String,
    val insteadOfPronunciation: String,
    val nuance: String,
    val visualPrompt: String,
    val language: Language,
)

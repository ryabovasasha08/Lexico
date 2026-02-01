package com.oriabova.lexico.ai.domain.model

import com.oriabova.lexico.utils.Language

data class GeneratedWord(
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

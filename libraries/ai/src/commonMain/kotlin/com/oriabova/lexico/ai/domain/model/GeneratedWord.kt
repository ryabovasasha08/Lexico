package com.oriabova.lexico.ai.domain.model

import com.oriabova.lexico.utils.Language

data class GeneratedWord(
    val word: String,
    val translation: String,
    val example: String,
    val partOfSpeech: String,
    val ipa: String,
    val language: Language,
)

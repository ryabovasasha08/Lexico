package com.oriabova.lexico.ai.data.model

import com.oriabova.lexico.utils.Language
import kotlinx.serialization.Serializable

@Serializable
data class WordGenerationRequest(
    val language: Language,
    val level: String,
    val targetLanguage: String,
    val topic: String? = null,
    val recentWords: List<String> = emptyList(),
)
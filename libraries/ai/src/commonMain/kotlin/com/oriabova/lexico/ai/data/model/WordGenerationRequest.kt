package com.oriabova.lexico.ai.data.model

import com.oriabova.lexico.utils.Language
import kotlinx.serialization.Serializable

@Serializable
internal data class WordGenerationRequest(
    val targetLanguage: Language,
    val level: String,
    val originalLanguage: Language,
    val topic: String? = null,
    val recentWords: List<String> = emptyList(),
)
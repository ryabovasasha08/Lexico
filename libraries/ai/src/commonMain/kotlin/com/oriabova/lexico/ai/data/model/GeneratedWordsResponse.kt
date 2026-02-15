package com.oriabova.lexico.ai.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class GeneratedWordsResponse(
    val words: List<GeneratedWord>,
)

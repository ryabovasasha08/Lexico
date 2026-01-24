package com.oriabova.lexico.ai.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class GenerateContentRequest(
    val contents: List<GeneratedContent>,
    val systemInstruction: GeneratedContent? = null,
    val generationConfig: GenerationConfig? = null,
)
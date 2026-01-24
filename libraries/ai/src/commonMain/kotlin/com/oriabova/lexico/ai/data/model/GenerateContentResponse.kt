package com.oriabova.lexico.ai.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class GenerateContentResponse(
    val candidates: List<GeneratedCandidate> = emptyList(),
)

@Serializable
internal data class GeneratedCandidate(
    val content: GeneratedContent? = null,
)

@Serializable
internal data class GeneratedContent(
    val role: String? = null,
    val parts: List<GeneratedPart>,
)

@Serializable
internal data class GeneratedPart(
    val text: String,
)

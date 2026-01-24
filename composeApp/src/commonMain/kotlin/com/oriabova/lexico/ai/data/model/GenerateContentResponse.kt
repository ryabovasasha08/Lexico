package com.oriabova.lexico.ai.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenerateContentResponse(
    val candidates: List<GeneratedCandidate> = emptyList(),
)

@Serializable
data class GeneratedCandidate(
    val content: GeneratedContent? = null,
)

@Serializable
data class GeneratedContent(
    val role: String? = null,
    val parts: List<GeneratedPart>,
)

@Serializable
data class GeneratedPart(
    val text: String,
)

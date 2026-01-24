package com.oriabova.lexico.ai.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GenerationConfig(
    @SerialName("responseMimeType")
    val responseMimeType: String? = null,
    val temperature: Double? = null,
    val topP: Double? = null,
    val maxOutputTokens: Int? = null,
)
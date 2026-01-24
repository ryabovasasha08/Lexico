package com.oriabova.lexico.ai.data.model

internal data class AiConfig(
    val apiKey: String,
    val model: String = "gemini-2.5-flash-lite",
)
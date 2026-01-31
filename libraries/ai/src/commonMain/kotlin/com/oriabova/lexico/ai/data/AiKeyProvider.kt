package com.oriabova.lexico.ai.data

interface AiKeyProvider {
    suspend fun getGeminiApiKey(): String
}

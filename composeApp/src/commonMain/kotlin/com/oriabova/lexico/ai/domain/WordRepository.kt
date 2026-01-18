package com.oriabova.lexico.ai.domain

import com.oriabova.lexico.ai.data.model.WordGenerationRequest
import com.oriabova.lexico.ai.domain.model.GeneratedWord

interface WordRepository {
    suspend fun generateWord(request: WordGenerationRequest): GeneratedWord
}

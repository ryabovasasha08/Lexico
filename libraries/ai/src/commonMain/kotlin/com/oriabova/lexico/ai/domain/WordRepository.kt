package com.oriabova.lexico.ai.domain

import com.oriabova.lexico.ai.data.model.WordGenerationRequest
import com.oriabova.lexico.ai.domain.model.GeneratedWord

internal interface WordRepository {
    suspend fun generateWords(request: WordGenerationRequest): List<GeneratedWord>
}

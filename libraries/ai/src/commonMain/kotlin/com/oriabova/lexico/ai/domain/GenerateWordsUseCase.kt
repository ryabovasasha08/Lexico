package com.oriabova.lexico.ai.domain

import com.oriabova.lexico.ai.domain.model.GeneratedWord

interface GenerateWordsUseCase {
    suspend operator fun invoke(recentWords: List<String>, count: Int): List<GeneratedWord>
}

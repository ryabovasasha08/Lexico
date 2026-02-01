package com.oriabova.lexico.ai.domain

import com.oriabova.lexico.ai.domain.model.GeneratedWord

interface GenerateWordUseCase {
    suspend operator fun invoke(recentWords: List<String>): GeneratedWord
}

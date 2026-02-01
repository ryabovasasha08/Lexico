package com.oriabova.lexico.home.domain

import com.oriabova.lexico.ai.domain.model.GeneratedWord

internal interface SkipWordUseCase {
    suspend operator fun invoke(word: GeneratedWord)
}

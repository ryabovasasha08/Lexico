package com.oriabova.lexico.home.domain

import com.oriabova.lexico.ai.domain.model.GeneratedWord

internal interface SaveWordUseCase {
    suspend operator fun invoke(word: GeneratedWord)
}

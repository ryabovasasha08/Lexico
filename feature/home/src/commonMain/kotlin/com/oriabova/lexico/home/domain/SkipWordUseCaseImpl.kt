package com.oriabova.lexico.home.domain

import com.oriabova.lexico.ai.domain.model.GeneratedWord

internal class SkipWordUseCaseImpl(
    private val repository: WordsRepository,
) : SkipWordUseCase {
    override suspend fun invoke(word: GeneratedWord) {
        repository.skipWord(word)
    }
}

package com.oriabova.lexico.home.domain

import com.oriabova.lexico.ai.domain.model.GeneratedWord

internal class SaveWordUseCaseImpl(
    private val repository: WordsRepository,
) : SaveWordUseCase {
    override suspend fun invoke(word: GeneratedWord) {
        repository.saveWord(word)
    }
}

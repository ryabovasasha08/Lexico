package com.oriabova.lexico.home.domain

import com.oriabova.lexico.ai.domain.model.GeneratedWord
import kotlinx.coroutines.flow.Flow

internal class ObserveSavedWordsUseCaseImpl(
    private val repository: WordsRepository,
) : ObserveSavedWordsUseCase {
    override fun invoke(): Flow<List<GeneratedWord>> {
        return repository.observeSavedWords()
    }
}

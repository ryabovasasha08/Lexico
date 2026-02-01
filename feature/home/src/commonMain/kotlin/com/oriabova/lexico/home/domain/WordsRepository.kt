package com.oriabova.lexico.home.domain

import com.oriabova.lexico.ai.domain.model.GeneratedWord
import kotlinx.coroutines.flow.Flow

internal interface WordsRepository {
    suspend fun saveWord(word: GeneratedWord)
    suspend fun skipWord(word: GeneratedWord)
    fun observeSavedWords(): Flow<List<GeneratedWord>>
}

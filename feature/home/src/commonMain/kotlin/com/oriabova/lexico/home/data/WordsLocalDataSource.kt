package com.oriabova.lexico.home.data

import com.oriabova.lexico.ai.domain.model.GeneratedWord
import kotlinx.coroutines.flow.Flow

internal interface WordsLocalDataSource {
    suspend fun storeSavedWord(word: GeneratedWord)
    suspend fun storeSkippedWord(word: GeneratedWord)
    fun observeSavedWords(): Flow<List<GeneratedWord>>
}

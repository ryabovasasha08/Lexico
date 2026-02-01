package com.oriabova.lexico.home.data

import com.oriabova.lexico.ai.domain.model.GeneratedWord
import com.oriabova.lexico.home.domain.WordsRepository
import kotlinx.coroutines.flow.Flow

internal class WordsRepositoryImpl(
    private val localDataSource: WordsLocalDataSource,
) : WordsRepository {
    override suspend fun saveWord(word: GeneratedWord) {
        localDataSource.storeSavedWord(word)
    }

    override suspend fun skipWord(word: GeneratedWord) {
        localDataSource.storeSkippedWord(word)
    }

    override fun observeSavedWords(): Flow<List<GeneratedWord>> {
        return localDataSource.observeSavedWords()
    }
}

package com.oriabova.lexico.home.data

import com.oriabova.lexico.ai.domain.model.GeneratedWord
import com.oriabova.lexico.home.data.mapper.GeneratedWordEntityToDomainMapper
import com.oriabova.lexico.home.data.mapper.GeneratedWordToEntityMapper
import com.oriabova.lexico.home.data.model.StoredWords
import com.oriabova.lexico.localstorage.LocalStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val SAVED_WORDS_KEY = "saved_words"
private const val SKIPPED_WORDS_KEY = "skipped_words"

internal class WordsLocalDataSourceImpl(
    private val localStorage: LocalStorage,
    private val domainToEntityMapper: GeneratedWordToEntityMapper,
    private val entityToDomainMapper: GeneratedWordEntityToDomainMapper,
) : WordsLocalDataSource {
    override suspend fun storeSavedWord(word: GeneratedWord) {
        storeWord(SAVED_WORDS_KEY, word)
    }

    override suspend fun storeSkippedWord(word: GeneratedWord) {
        storeWord(SKIPPED_WORDS_KEY, word)
    }

    override fun observeSavedWords(): Flow<List<GeneratedWord>> {
        return observeWords(SAVED_WORDS_KEY)
    }

    private suspend fun storeWord(key: String, word: GeneratedWord) {
        localStorage.updateData(key, StoredWords.Empty, StoredWords::class) { stored ->
            stored.copy(words = stored.words + domainToEntityMapper.map(word))
        }
    }

    private fun observeWords(key: String): Flow<List<GeneratedWord>> {
        return localStorage.observeData(key, StoredWords::class, StoredWords.Empty)
            .map { stored -> stored.words.map { entityToDomainMapper.map(it) } }
    }
}

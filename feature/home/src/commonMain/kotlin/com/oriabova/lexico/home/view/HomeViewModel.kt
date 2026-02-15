package com.oriabova.lexico.home.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.ai.domain.GenerateWordsUseCase
import com.oriabova.lexico.ai.domain.model.GeneratedWord
import com.oriabova.lexico.home.domain.ObserveSavedWordsUseCase
import com.oriabova.lexico.home.domain.SaveWordUseCase
import com.oriabova.lexico.home.domain.SkipWordUseCase
import com.oriabova.lexico.home.view.model.HomeUiEvent
import com.oriabova.lexico.home.view.model.HomeUiState
import com.oriabova.lexico.home.view.model.VocabularyCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val MAX_SAVED_COUNT = 5
private const val BATCH_SIZE = 10
private const val REFILL_THRESHOLD = 3

internal class HomeViewModel(
    private val generateWordsUseCase: GenerateWordsUseCase,
    private val saveWordUseCase: SaveWordUseCase,
    private val skipWordUseCase: SkipWordUseCase,
    observeSavedWordsUseCase: ObserveSavedWordsUseCase,
) : ViewModel() {

    private val isLoadingBatch = MutableStateFlow(false)
    private val pendingWords = MutableStateFlow<List<GeneratedWord>>(emptyList())
    private val savedWords =
        observeSavedWordsUseCase().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val uiState: StateFlow<HomeUiState> = combine(
        pendingWords,
        savedWords,
        isLoadingBatch
    ) { queuedWords, savedWords, isLoading ->
        when {
            queuedWords.isNotEmpty() -> HomeUiState.Content(
                savedCount = savedWords.size,
                maxSavedCount = MAX_SAVED_COUNT,
                currentCard = queuedWords.first().toCard(),
                nextCard = queuedWords.getOrNull(1)?.toCard()
            )

            isLoading -> HomeUiState.Loading(
                savedCount = savedWords.size,
                maxSavedCount = MAX_SAVED_COUNT
            )

            else -> HomeUiState.Empty(
                savedCount = savedWords.size,
                maxSavedCount = MAX_SAVED_COUNT
            )
        }
    }
        .onStart { loadBatchIfNeeded(force = true) }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            HomeUiState.Loading(0, MAX_SAVED_COUNT)
        )

    fun handleUiEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnSaveWord -> saveCurrentWord()
            HomeUiEvent.OnSkipWord -> skipCurrentWord()
        }
    }

    private fun saveCurrentWord() {
        consumeCurrentWord { generatedWord -> saveWordUseCase(generatedWord) }
    }

    private fun skipCurrentWord() {
        consumeCurrentWord { generatedWord -> skipWordUseCase(generatedWord) }
    }

    private fun consumeCurrentWord(action: suspend (GeneratedWord) -> Unit) {
        viewModelScope.launch {
            val currentWord = pendingWords.value.firstOrNull() ?: return@launch
            action(currentWord)
            pendingWords.update { words -> words.drop(1) }

            if (pendingWords.value.size <= REFILL_THRESHOLD) {
                loadBatchIfNeeded()
            }
        }
    }

    private suspend fun loadBatchIfNeeded(force: Boolean = false) {
        if (!force && isQueueFull()) return
        if (!isLoadingBatch.compareAndSet(expect = false, update = true)) return

        try {
            val queue = pendingWords.value.toMutableList()
            val excludedWordSet = buildExcludedWordSet(queue)
            val missingCount = BATCH_SIZE - queue.size
            if (missingCount <= 0) return

            val generatedWords = generateWordsUseCase(
                recentWords = excludedWordSet.toList(),
                count = missingCount
            )

            generatedWords.forEach { generatedWord ->
                if (excludedWordSet.add(generatedWord.word)) {
                    queue += generatedWord
                }
            }

            pendingWords.value = queue
        } finally {
            isLoadingBatch.value = false
        }
    }

    private fun isQueueFull(): Boolean = pendingWords.value.size >= BATCH_SIZE

    private fun buildExcludedWordSet(currentQueue: List<GeneratedWord>): MutableSet<String> {
        return buildSet {
            addAll(savedWords.value.map { it.word })
            addAll(currentQueue.map { it.word })
        }.toMutableSet()
    }

    private fun GeneratedWord.toCard(): VocabularyCard {
        return VocabularyCard(
            word = this.word,
            translation = this.translation,
            insteadOf = this.insteadOf,
            example = this.example,
            nuance = this.nuance,
            imageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee", // TODO: get real image url
            visualPrompt = this.visualPrompt,
            language = this.language
        )
    }
}

package com.oriabova.lexico.home.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.ai.domain.GenerateWordUseCase
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
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val generateWordUseCase: GenerateWordUseCase,
    private val saveWordUseCase: SaveWordUseCase,
    private val skipWordUseCase: SkipWordUseCase,
    observeSavedWordsUseCase: ObserveSavedWordsUseCase,
) : ViewModel() {

    private val wordToDisplay = MutableStateFlow<GeneratedWord?>(null)
    private val savedWords =
        observeSavedWordsUseCase().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val uiState: StateFlow<HomeUiState> = combine(
        wordToDisplay,
        savedWords
    ) { generatedWord, savedWords ->
        HomeUiState(
            isLoading = generatedWord == null,
            savedCount = savedWords.size,
            maxSavedCount = 5,
            currentCard = generatedWord?.toCard()
        )
    }
        .onStart { loadNextWord() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, createInitialState())

    fun handleUiEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnSaveWord -> saveCurrentWord()
            HomeUiEvent.OnSkipWord -> skipCurrentWord()
        }
    }

    private fun createInitialState(): HomeUiState {
        return HomeUiState(
            isLoading = true,
            savedCount = 0,
            maxSavedCount = 5,
            currentCard = null
        )
    }

    private fun saveCurrentWord() {
        viewModelScope.launch {
            saveWordUseCase(wordToDisplay.value!!)
            loadNextWord()
        }
    }

    private fun skipCurrentWord() {
        viewModelScope.launch {
            skipWordUseCase(wordToDisplay.value!!)
            loadNextWord()
        }
    }

    private suspend fun loadNextWord() {
        wordToDisplay.value = generateWordUseCase(savedWords.value.map { it.word })
    }

    private fun GeneratedWord.toCard(): VocabularyCard {
        return VocabularyCard(
            word = this.word,
            insteadOf = this.insteadOf,
            example = this.example,
            nuance = this.nuance,
            imageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee", // TODO: get real image url
            visualPrompt = this.visualPrompt,
            language = this.language
        )
    }
}

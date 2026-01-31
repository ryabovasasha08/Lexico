package com.oriabova.lexico.home.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.ai.domain.GenerateWordUseCase
import com.oriabova.lexico.ai.domain.model.GeneratedWord
import com.oriabova.lexico.home.view.model.HomeUiEvent
import com.oriabova.lexico.home.view.model.HomeUiState
import com.oriabova.lexico.home.view.model.VocabularyCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val generateWordUseCase: GenerateWordUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            val newWord = generateWordUseCase()
            _uiState.value = newWord.toUiState()
        }
    }

    private val _uiState = MutableStateFlow(dummyState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun handleUiEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnSaveWord -> Unit // TODO: save the word.
            HomeUiEvent.OnSkipWord -> Unit // TODO: skip the word.
            HomeUiEvent.OnAudioPlay -> Unit // TODO: play audio.
        }
    }

    private fun dummyState(): HomeUiState {
        return HomeUiState(
            isLoading = false,
            savedCount = 1,
            maxSavedCount = 3,
            currentCard = VocabularyCard(
                word = "Serendipity",
                insteadOf = "Lucky",
                example = "Finding that tiny cafe was pure serendipity on the trip.",
                imageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee"
            )
        )
    }

    private fun GeneratedWord.toUiState(): HomeUiState {
        return HomeUiState(
            isLoading = false,
            savedCount = 0,
            maxSavedCount = 5,
            currentCard = VocabularyCard(
                word = this.word,
                insteadOf = this.translation,
                example = this.example,
                imageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee" // TODO: get real image url
            )
        )
    }
}

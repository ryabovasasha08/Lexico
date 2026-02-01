package com.oriabova.lexico.home.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.ai.domain.GenerateWordUseCase
import com.oriabova.lexico.ai.domain.model.GeneratedWord
import com.oriabova.lexico.home.view.model.HomeUiEvent
import com.oriabova.lexico.home.view.model.HomeUiState
import com.oriabova.lexico.home.view.model.VocabularyCard
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

internal class HomeViewModel(
    private val generateWordUseCase: GenerateWordUseCase,
) : ViewModel() {

    private val _uiState = flow {
        emit(generateWordUseCase().toUiState())
    }

    val uiState: StateFlow<HomeUiState> = _uiState.stateIn(
        viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = createInitialState()
    )

    fun handleUiEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnSaveWord -> Unit // TODO: save the word.
            HomeUiEvent.OnSkipWord -> Unit // TODO: skip the word.
        }
    }

    private fun createInitialState(): HomeUiState {
        return HomeUiState(
            isLoading = true,
            savedCount = 0,
            maxSavedCount = 0,
            currentCard = null
        )
    }

    private fun GeneratedWord.toUiState(): HomeUiState {
        return HomeUiState(
            isLoading = false,
            savedCount = 0,
            maxSavedCount = 5,
            currentCard = VocabularyCard(
                word = this.word,
                insteadOf = this.insteadOf,
                example = this.example,
                nuance = this.nuance,
                imageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee", // TODO: get real image url
                visualPrompt = this.visualPrompt,
                language = this.language
            )
        )
    }
}

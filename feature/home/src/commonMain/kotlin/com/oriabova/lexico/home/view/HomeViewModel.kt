package com.oriabova.lexico.home.view

import androidx.lifecycle.ViewModel
import com.oriabova.lexico.home.view.model.HomeUiEvent
import com.oriabova.lexico.home.view.model.HomeUiState
import com.oriabova.lexico.home.view.model.VocabularyCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class HomeViewModel : ViewModel() {

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
}

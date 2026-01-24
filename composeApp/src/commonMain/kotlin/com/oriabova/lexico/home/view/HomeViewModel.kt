package com.oriabova.lexico.home.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.ai.domain.GenerateWordUseCase
import com.oriabova.lexico.home.view.model.HomeUiEvent
import com.oriabova.lexico.home.view.model.HomeUiState
import com.oriabova.lexico.home.view.model.WordCardUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val STREAK_BANNER_TIMEOUT_MS = 6_000L

class HomeViewModel(
    private val generateWordUseCase: GenerateWordUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(dummyState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(STREAK_BANNER_TIMEOUT_MS)
            _uiState.update { it.copy(showStreakBanner = false) }
        }
    }

    fun handleUiEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.UseWordClick -> generateNewWord()
            HomeUiEvent.PracticeNowClick -> Unit
            HomeUiEvent.ReviewRecentClick -> Unit
            HomeUiEvent.AdjustScheduleClick -> Unit
        }
    }

    private fun generateNewWord() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val generated = generateWordUseCase()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        currentWord = WordCardUiState(
                            word = generated.word,
                            pronunciation = generated.ipa,
                            partOfSpeech = generated.partOfSpeech,
                            definition = generated.translation,
                            example = generated.example,
                            isNew = true,
                            languageCode = generated.language.code,
                        )
                    )
                }
            } catch (error: Throwable) {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun dummyState(): HomeUiState {
        val currentWord = WordCardUiState(
            word = "Serendipity",
            pronunciation = "seh-ren-DIP-ih-tee",
            partOfSpeech = "noun",
            definition = "A pleasant surprise found by chance.",
            example = "Meeting an old friend in the city was pure serendipity.",
            isNew = true,
            languageCode = "en-US",
        )
        return HomeUiState(
            isLoading = false,
            currentWord = currentWord,
            streakDays = 4,
            deliveredToday = 2,
            dailyGoal = 5,
            nextDropIn = "32 min",
            tip = "Use today's word in a short voice note.",
            showStreakBanner = true
        )
    }
}

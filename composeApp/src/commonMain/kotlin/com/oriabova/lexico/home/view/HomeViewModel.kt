package com.oriabova.lexico.home.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.home.view.model.HomeUiEvent
import com.oriabova.lexico.home.view.model.HomeUiState
import com.oriabova.lexico.home.view.model.WordCardUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

private const val STREAK_BANNER_TIMEOUT_MS = 6_000L

class HomeViewModel : ViewModel() {

    val uiState: StateFlow<HomeUiState> = flow {
        val initial = dummyState()
        emit(initial)
        delay(STREAK_BANNER_TIMEOUT_MS)
        emit(initial.copy(showStreakBanner = false))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), initialValue = dummyState())

    fun handleUiEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.UseWordClick -> Unit
            HomeUiEvent.PracticeNowClick -> Unit
            HomeUiEvent.ReviewRecentClick -> Unit
            HomeUiEvent.AdjustScheduleClick -> Unit
        }
    }

    private fun dummyState(): HomeUiState {
        val currentWord = WordCardUiState(
            word = "Serendipity",
            pronunciation = "seh-ren-DIP-ih-tee",
            partOfSpeech = "noun",
            definition = "A pleasant surprise found by chance.",
            example = "Meeting an old friend in the city was pure serendipity.",
            isNew = true
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

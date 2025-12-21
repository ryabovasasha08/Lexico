package com.oriabova.lexico.home.view.model

sealed class HomeUiEvent {
    data class UseWordClick(val word: WordCardUiState) : HomeUiEvent()
    data object PracticeNowClick : HomeUiEvent()
    data object ReviewRecentClick : HomeUiEvent()
    data object AdjustScheduleClick : HomeUiEvent()
}

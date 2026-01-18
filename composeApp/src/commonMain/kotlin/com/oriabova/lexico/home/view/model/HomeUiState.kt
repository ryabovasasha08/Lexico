package com.oriabova.lexico.home.view.model

data class HomeUiState(
    val isLoading: Boolean,
    val currentWord: WordCardUiState,
    val streakDays: Int,
    val deliveredToday: Int,
    val dailyGoal: Int,
    val nextDropIn: String,
    val tip: String,
    val showStreakBanner: Boolean
) {
    val progressFraction: Float
        get() = if (dailyGoal == 0) 0f else (deliveredToday.coerceAtMost(dailyGoal)).toFloat() / dailyGoal
}

data class WordCardUiState(
    val word: String,
    val pronunciation: String,
    val partOfSpeech: String,
    val definition: String,
    val example: String,
    val isNew: Boolean,
    val languageCode: String,
)

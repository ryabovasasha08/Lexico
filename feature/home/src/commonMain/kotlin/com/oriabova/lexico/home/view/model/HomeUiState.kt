package com.oriabova.lexico.home.view.model

internal data class HomeUiState(
    val currentCard: VocabularyCard?,
    val savedCount: Int,
    val maxSavedCount: Int,
    val isLoading: Boolean,
)

internal data class VocabularyCard(
    val word: String,
    val insteadOf: String,
    val example: String,
    val nuance: String,
    val imageUrl: String,
    val visualPrompt: String,
)

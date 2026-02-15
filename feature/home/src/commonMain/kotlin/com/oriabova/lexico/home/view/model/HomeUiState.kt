package com.oriabova.lexico.home.view.model

import com.oriabova.lexico.utils.Language

internal sealed class HomeUiState {
    abstract val savedCount: Int
    abstract val maxSavedCount: Int

    data class Content(
        val currentCard: VocabularyCard,
        override val savedCount: Int,
        override val maxSavedCount: Int
    ) : HomeUiState()

    data class Empty(
        override val savedCount: Int,
        override val maxSavedCount: Int
    ) : HomeUiState()

    data class Loading(
        override val savedCount: Int,
        override val maxSavedCount: Int
    ) : HomeUiState()
}

internal data class VocabularyCard(
    val word: String,
    val translation: String,
    val insteadOf: String,
    val example: String,
    val nuance: String,
    val imageUrl: String,
    val visualPrompt: String,
    val language: Language,
)

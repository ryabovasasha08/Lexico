package com.oriabova.lexico.home.domain

import com.oriabova.lexico.ai.domain.model.GeneratedWord
import kotlinx.coroutines.flow.Flow

internal interface ObserveSavedWordsUseCase {
    operator fun invoke(): Flow<List<GeneratedWord>>
}

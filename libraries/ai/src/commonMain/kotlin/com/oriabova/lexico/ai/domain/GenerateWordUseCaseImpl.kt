package com.oriabova.lexico.ai.domain

import com.oriabova.lexico.ai.data.model.WordGenerationRequest
import com.oriabova.lexico.ai.domain.model.GeneratedWord
import com.oriabova.lexico.setup.domain.GetSetupDetailsUseCase
import com.oriabova.lexico.setup.domain.model.SetupLevel
import com.oriabova.lexico.utils.Language

internal class GenerateWordUseCaseImpl(
    private val wordRepository: WordRepository,
    private val getSetupDetailsUseCase: GetSetupDetailsUseCase,
) : GenerateWordUseCase {
    override suspend fun invoke(): GeneratedWord {
        val setup = getSetupDetailsUseCase()
        val fallbackLanguage = Language("en-GB","English")
        val targetLanguage = setup.languageToLearn ?: fallbackLanguage
        val level = mapToCefr(setup.level ?: SetupLevel.BEGINNER)
        val request = WordGenerationRequest(
            targetLanguage = targetLanguage,
            level = level,
            originalLanguage = fallbackLanguage,
        )
        return wordRepository.generateWord(request)
    }

    private fun mapToCefr(level: SetupLevel): String {
        return when (level) {
            SetupLevel.BEGINNER -> "A1"
            SetupLevel.PREINTERMEDIATE -> "A2"
            SetupLevel.INTERMEDIATE -> "B1"
            SetupLevel.UPPERINTERMEDIATE -> "B2"
            SetupLevel.ADVANCED -> "C1"
        }
    }
}

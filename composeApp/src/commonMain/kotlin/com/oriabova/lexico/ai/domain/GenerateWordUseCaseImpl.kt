package com.oriabova.lexico.ai.domain

import com.oriabova.lexico.ai.data.model.WordGenerationRequest
import com.oriabova.lexico.ai.domain.model.GeneratedWord
import com.oriabova.lexico.setup.domain.GetSetupDetailsUseCase
import com.oriabova.lexico.setup.domain.model.SetupLevel
import com.oriabova.lexico.utils.Language

class GenerateWordUseCaseImpl(
    private val wordRepository: WordRepository,
    private val getSetupDetailsUseCase: GetSetupDetailsUseCase,
) : GenerateWordUseCase {
    override suspend fun invoke(): GeneratedWord {
        val setup = getSetupDetailsUseCase()
        val language = setup.languageToLearn ?: Language("en-GB", "English")
        val level = mapToCefr(setup.level ?: SetupLevel.BEGINNER)
        val request = WordGenerationRequest(
            language = language,
            level = level,
            targetLanguage = "English",
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

package com.oriabova.lexico.ai.domain

import com.oriabova.lexico.ai.data.model.WordGenerationRequest
import com.oriabova.lexico.ai.domain.model.GeneratedWord
import com.oriabova.lexico.setup.domain.GetSetupDetailsUseCase
import com.oriabova.lexico.setup.domain.model.SetupLevel
import com.oriabova.lexico.utils.Language

internal class GenerateWordsUseCaseImpl(
    private val wordRepository: WordRepository,
    private val getSetupDetailsUseCase: GetSetupDetailsUseCase,
) : GenerateWordsUseCase {
    override suspend fun invoke(recentWords: List<String>, count: Int): List<GeneratedWord> {
        require(count > 0) { "count must be greater than 0." }

        val setup = getSetupDetailsUseCase()
        val fallbackLanguage = Language("en-GB","English")
        val targetLanguage = setup.languageToLearn ?: fallbackLanguage
        val level = mapToCefr(setup.level ?: SetupLevel.BEGINNER)
        val request = WordGenerationRequest(
            targetLanguage = targetLanguage,
            level = level,
            count = count,
            originalLanguage = fallbackLanguage,
            recentWords = recentWords,
        )
        return wordRepository.generateWords(request)
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

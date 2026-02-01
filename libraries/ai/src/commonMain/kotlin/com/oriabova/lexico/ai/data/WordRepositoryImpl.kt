package com.oriabova.lexico.ai.data

import com.oriabova.lexico.ai.data.model.WordGenerationRequest
import com.oriabova.lexico.ai.domain.WordRepository
import com.oriabova.lexico.ai.domain.model.GeneratedWord

internal class WordRepositoryImpl(
    private val aiApi: AiApi,
) : WordRepository {
    override suspend fun generateWord(request: WordGenerationRequest): GeneratedWord {
        val result = aiApi.generateWord(request)

        return GeneratedWord(
            word = result.word,
            translation = result.translation,
            example = result.example,
            exampleTranslation = result.exampleTranslation,
            wordPronunciation = result.wordPronunciation,
            insteadOfPronunciation = result.insteadOfPronunciation,
            insteadOf = result.insteadOf,
            nuance = result.nuance,
            visualPrompt = result.visualPrompt,
            language = request.language,
        )
    }
}
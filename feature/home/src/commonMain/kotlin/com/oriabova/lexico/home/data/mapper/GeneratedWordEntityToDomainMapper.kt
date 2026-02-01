package com.oriabova.lexico.home.data.mapper

import com.oriabova.lexico.ai.domain.model.GeneratedWord
import com.oriabova.lexico.home.data.model.GeneratedWordEntity

internal class GeneratedWordEntityToDomainMapper {
    fun map(entity: GeneratedWordEntity): GeneratedWord {
        return GeneratedWord(
            word = entity.word,
            wordPronunciation = entity.wordPronunciation,
            translation = entity.translation,
            example = entity.example,
            exampleTranslation = entity.exampleTranslation,
            insteadOf = entity.insteadOf,
            insteadOfPronunciation = entity.insteadOfPronunciation,
            nuance = entity.nuance,
            visualPrompt = entity.visualPrompt,
            language = entity.language
        )
    }
}

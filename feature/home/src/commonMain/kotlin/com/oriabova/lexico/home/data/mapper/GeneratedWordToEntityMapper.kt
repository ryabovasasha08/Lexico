package com.oriabova.lexico.home.data.mapper

import com.oriabova.lexico.ai.domain.model.GeneratedWord
import com.oriabova.lexico.home.data.model.GeneratedWordEntity

internal class GeneratedWordToEntityMapper {
    fun map(word: GeneratedWord): GeneratedWordEntity {
        return GeneratedWordEntity(
            word = word.word,
            wordPronunciation = word.wordPronunciation,
            translation = word.translation,
            example = word.example,
            exampleTranslation = word.exampleTranslation,
            insteadOf = word.insteadOf,
            insteadOfPronunciation = word.insteadOfPronunciation,
            nuance = word.nuance,
            visualPrompt = word.visualPrompt,
            language = word.language
        )
    }
}

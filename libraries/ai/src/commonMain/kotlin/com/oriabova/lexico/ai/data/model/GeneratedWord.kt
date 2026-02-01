package com.oriabova.lexico.ai.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GeneratedWord(
    val word: String,
    @SerialName("word_pronunciation") val wordPronunciation: String,
    val translation: String,

    val example: String,
    @SerialName("example_translation") val exampleTranslation: String,

    @SerialName("instead_of") val insteadOf: String,
    @SerialName("instead_of_pronunciation") val insteadOfPronunciation: String,
    val nuance: String,

    @SerialName("visual_prompt")
    val visualPrompt: String
)

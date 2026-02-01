package com.oriabova.lexico.ai.data.model

internal object GeminiPrompt {
    fun userPrompt(request: WordGenerationRequest): String {
        val topicPart = request.topic?.takeIf { it.isNotBlank() }?.let { " Focus on topic: $it." } ?: ""
        val recentWordsPart = request.recentWords.take(20).let {
            if (it.isNotEmpty()) "Avoid: ${it.joinToString(", ")}." else ""
        }

        return """
        Generate one 'Level-Up' vocabulary item for ${request.language.name} (Level ${request.level}).
        $topicPart
        $recentWordsPart

        STRICT INSTRUCTIONS:
        1. 'nuance' must be written in ${request.language.name}.
        2. 'word_pronunciation' and 'instead_of_pronunciation' should use ${request.language.name} orthography to describe the sound (e.g., for English 'Nature', a German learner might see 'Neit-tscher'). No IPA.
        3. Provide translations in ${request.targetLanguage} only for the specific translation fields.

        Return JSON:
        {
          "word": "The advanced word (e.g., 'Exquisite')",
          "word_pronunciation": "Phonetic spelling in ${request.language.name}",
          "instead_of": "The basic word it replaces (e.g., 'Very good')",
          "instead_of_pronunciation": "Phonetic spelling in ${request.language.name}",
          "translation": "Word translation in ${request.language.name}",
          "nuance": "Explanation in ${request.language.name} of why this word is better than the basic one.",
          "example": "Natural sentence in ${request.targetLanguage} under 12 words. No proper nouns.",
          "example_translation": "Example translation in ${request.language.name}",
          "visual_prompt": "A descriptive prompt for an image generator (photography style)."
        }
    """.trimIndent()
    }
}
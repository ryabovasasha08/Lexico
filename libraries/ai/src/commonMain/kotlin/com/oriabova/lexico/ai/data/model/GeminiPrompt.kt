package com.oriabova.lexico.ai.data.model

internal object GeminiPrompt {
    fun userPrompt(request: WordGenerationRequest): String {
        val topicPart = request.topic?.takeIf { it.isNotBlank() }?.let { " Focus on topic: $it." } ?: ""
        val recentWordsPart = request.recentWords.take(20).let {
            if (it.isNotEmpty()) "Avoid: ${it.joinToString(", ")}." else ""
        }

        return """
        Generate ${request.count} different 'Level-Up' vocabulary items for ${request.targetLanguage.name} (Level ${request.level}).
        $topicPart
        $recentWordsPart

        STRICT INSTRUCTIONS:
        0. Return exactly ${request.count} items and all words must be unique.
        1. 'nuance' must be written in ${request.originalLanguage.name}.
        2. 'word_pronunciation' and 'instead_of_pronunciation' should use ${request.originalLanguage.name} orthography to describe the sound (e.g., for English 'Nature', a German learner might see 'Neit-tscher'). No IPA.
        3. Provide translations in ${request.originalLanguage.name} only for the specific translation fields.

        Return JSON:
        {
          "words": [
            {
              "word": "Only the advanced word in ${request.targetLanguage.name}",
              "word_pronunciation": "Phonetic spelling in ${request.originalLanguage.name}",
              "instead_of": "The basic word it replaces in ${request.targetLanguage.name}",
              "instead_of_pronunciation": "Phonetic spelling in ${request.originalLanguage.name}",
              "translation": "Word translation in ${request.originalLanguage.name}",
              "nuance": "Explanation in ${request.originalLanguage.name} under 10 words of why this word is better than the basic one.",
              "example": "Natural sentence in ${request.targetLanguage.name} under 12 words. No proper nouns.",
              "example_translation": "Example translation in ${request.targetLanguage.name}",
              "visual_prompt": "A descriptive prompt for an image generator (photography style)."
            }
          ]
        }        
    """.trimIndent()
    }
}
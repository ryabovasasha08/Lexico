package com.oriabova.lexico.ai.data.model

internal object GeminiPrompt {
    fun userPrompt(request: WordGenerationRequest): String {
        val topicPart = request.topic?.takeIf { it.isNotBlank() }?.let { " Topic: $it." } ?: ""
        val recentWordsPart = request.recentWords
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .distinct()
            .take(20)
            .takeIf { it.isNotEmpty() }
            ?.joinToString(", ")
            ?.let { "Do NOT use any of these words or their inflected forms: $it.\n\n" }
            ?: ""

        return buildString {
            append(
                "Generate one vocabulary item for ${request.language.name} at CEFR level ${request.level}."
            )
            append(" Provide a translation to ${request.targetLanguage}.")
            append(" Include one short, natural example sentence in ${request.language.name}, appropriate for the level.")
            append(" Keep the example under 12 words. Avoid proper nouns.")
            append(topicPart)
            append("\n\n")
            append(recentWordsPart)
            append(
                "Return JSON exactly with:\n" +
                    "- word\n" +
                    "- translation\n" +
                    "- example\n" +
                    "- partOfSpeech\n" +
                    "- ipa"
            )
        }
    }
}
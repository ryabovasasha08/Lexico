package com.oriabova.lexico.ai.data

import com.oriabova.lexico.ai.data.model.GeminiPrompt
import com.oriabova.lexico.ai.data.model.GenerateContentRequest
import com.oriabova.lexico.ai.data.model.GenerateContentResponse
import com.oriabova.lexico.ai.data.model.GeneratedContent
import com.oriabova.lexico.ai.data.model.GeneratedPart
import com.oriabova.lexico.ai.data.model.GeneratedWord
import com.oriabova.lexico.ai.data.model.GenerationConfig
import com.oriabova.lexico.ai.data.model.WordGenerationRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

internal interface AiApi {
    suspend fun generateWord(request: WordGenerationRequest): GeneratedWord
}

internal class GeminiApi(
    private val httpClient: HttpClient,
    private val json: Json,
    private val aiKeyProvider: AiKeyProvider,
) : AiApi {
    private val systemInstruction: String = """
    You are a linguistic specialist for 'Lexico', an app for A2-C1 language learners.
    Your mission is to provide 'Level-Up' vocabulary that replaces basic words with sophisticated, natural alternatives.
    
    RULES:
    1. Output MUST be valid JSON.
    2. Focus on high-utility 'nuance' rather than obscure academic words.
    3. Ensure the 'instead_of' field contains a very common, basic synonym.
    4. The 'visual_prompt' must describe a concrete, high-quality photographic scene representing the word.
""".trimIndent()

    override suspend fun generateWord(request: WordGenerationRequest): GeneratedWord {
        val apiKey = aiKeyProvider.getGeminiApiKey()
        require(apiKey.isNotBlank()) { "Gemini API key is missing." }

        val payload = GenerateContentRequest(
            contents = listOf(
                GeneratedContent(
                    role = "user",
                    parts = listOf(GeneratedPart(text = GeminiPrompt.userPrompt(request))),
                )
            ),
            systemInstruction = GeneratedContent(
                parts = listOf(GeneratedPart(text = systemInstruction)),
            ),
            generationConfig = GenerationConfig(
                responseMimeType = "application/json",
                temperature = 0.7,
                topP = 0.9,
                maxOutputTokens = 256,
            ),
        )

        val response: GenerateContentResponse = httpClient.post(
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent"
        ) {
            url { parameters.append("key", apiKey) }
            contentType(ContentType.Application.Json)
            setBody(payload)
        }.body()

        val text = response.candidates
            .firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text
            ?.trim()

        require(!text.isNullOrEmpty()) { "Gemini response was empty." }
        return json.decodeFromString(GeneratedWord.serializer(), text)
    }
}

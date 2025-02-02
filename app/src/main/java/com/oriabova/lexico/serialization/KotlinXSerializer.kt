package com.oriabova.lexico.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import javax.inject.Inject

private const val NULL_CONTENT = "null"

class KotlinXSerializer @Inject constructor(
    private val json: Json,
) : JsonSerializer {
    override fun <T> fromJson(content: String, cls: Class<T>): T? {
        if (content.isBlank() || content == NULL_CONTENT) {
            return null
        }
        return json.decodeFromString(cls.serializer(), content)
    }

    override fun <T> toJson(data: T, cls: Class<T>): String {
        return json.encodeToString(cls.serializer(), data)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> Class<T>.serializer(): KSerializer<T> {
        val kSerializer = json.serializersModule.serializer(this) as KSerializer<T>
        return kSerializer
    }
}
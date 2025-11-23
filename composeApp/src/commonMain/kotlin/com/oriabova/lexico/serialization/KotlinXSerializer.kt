package com.oriabova.lexico.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

private const val NULL_CONTENT = "null"

class KotlinXSerializer(
    private val json: Json,
) : JsonSerializer {
    override fun <T : Any> fromJson(content: String, cls: KClass<T>): T? {
        if (content.isBlank() || content == NULL_CONTENT) {
            return null
        }
        return json.decodeFromString(cls.serializer(), content)
    }

    override fun <T : Any> toJson(data: T, cls: KClass<T>): String {
        return json.encodeToString(cls.serializer(), data)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> KClass<T>.serializer(): KSerializer<T> {
        val kSerializer = json.serializersModule.getContextual(this) as KSerializer<T>
        return kSerializer
    }
}
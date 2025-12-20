package com.oriabova.lexico.serialization

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

private const val NULL_CONTENT = "null"

@OptIn(InternalSerializationApi::class)
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
}

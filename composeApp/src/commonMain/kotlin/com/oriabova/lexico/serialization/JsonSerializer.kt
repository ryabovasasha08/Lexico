package com.oriabova.lexico.serialization

import kotlin.reflect.KClass

interface JsonSerializer {
    fun <T : Any> fromJson(content: String, cls: KClass<T>): T?
    fun <T : Any> toJson(data: T, cls: KClass<T>): String
}
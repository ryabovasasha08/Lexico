package com.oriabova.lexico.serialization

interface JsonSerializer {
    fun <T> fromJson(content: String, cls: Class<T>): T?
    fun <T> toJson(data: T, cls: Class<T>): String
}
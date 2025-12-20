package com.oriabova.lexico.serialization

import kotlinx.serialization.json.Json
import org.koin.dsl.module

val serializationModule = module {
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            isLenient = true
        }
    }

    single<JsonSerializer> { KotlinXSerializer(get()) }
}
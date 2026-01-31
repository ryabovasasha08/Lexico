package com.oriabova.lexico.ai.di

import com.oriabova.lexico.ai.data.AiKeyProvider
import com.oriabova.lexico.ai.data.AndroidAiKeyProvider
import org.koin.dsl.module

actual fun getAiKeyProviderModule() = module {
    single<AiKeyProvider> { AndroidAiKeyProvider() }
}
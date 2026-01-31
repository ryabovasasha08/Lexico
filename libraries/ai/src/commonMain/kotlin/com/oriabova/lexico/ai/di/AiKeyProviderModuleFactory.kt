package com.oriabova.lexico.ai.di

import com.oriabova.lexico.ai.data.AiKeyProvider
import org.koin.core.module.Module
import org.koin.dsl.module

fun aiKeyProviderModule(provider: AiKeyProvider): Module = module {
    single<AiKeyProvider> { provider }
}

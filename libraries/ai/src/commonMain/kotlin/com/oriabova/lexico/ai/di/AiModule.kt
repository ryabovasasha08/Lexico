package com.oriabova.lexico.ai.di

import com.oriabova.lexico.ai.data.AiApi
import com.oriabova.lexico.ai.data.GeminiApi
import com.oriabova.lexico.ai.data.WordRepositoryImpl
import com.oriabova.lexico.ai.data.getAiApiKey
import com.oriabova.lexico.ai.data.model.AiConfig
import com.oriabova.lexico.ai.domain.GenerateWordUseCase
import com.oriabova.lexico.ai.domain.GenerateWordUseCaseImpl
import com.oriabova.lexico.ai.domain.WordRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

private val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(get())
            }
        }
    }

    single { AiConfig(apiKey = getAiApiKey()) }
    single<AiApi> { GeminiApi(get(), get(), get()) }
    single<WordRepository> { WordRepositoryImpl(get()) }
}

private val domainModule = module {
    factory { GenerateWordUseCaseImpl(get(), get()) as GenerateWordUseCase }
}

val aiModule = module {
    includes(dataModule, domainModule)
}

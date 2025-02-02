package com.oriabova.lexico.serialization

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
abstract class SerializationModule {

    @Binds
    internal abstract fun bindKotlinXSerializer(impl: KotlinXSerializer): JsonSerializer

    companion object {
        @Provides
        fun providesKotlinXSerialization(): Json {
            return Json {
                ignoreUnknownKeys = true
                explicitNulls = false
                isLenient = true
            }
        }
    }
}
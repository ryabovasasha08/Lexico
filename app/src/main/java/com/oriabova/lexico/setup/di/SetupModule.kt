package com.oriabova.lexico.setup.di

import com.oriabova.lexico.setup.data.SetupRepositoryImpl
import com.oriabova.lexico.setup.domain.SetupRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SetupModule {
    @Binds
    abstract fun bindSetupRepository(setupRepositoryImpl: SetupRepositoryImpl): SetupRepository
}
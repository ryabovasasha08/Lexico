package com.oriabova.lexico.setup.di

import com.oriabova.lexico.setup.domain.ObserveSetupStateUseCase
import com.oriabova.lexico.setup.domain.ObserveSetupStateUseCaseImpl
import com.oriabova.lexico.setup.domain.StoreSetupDetailsUseCase
import com.oriabova.lexico.setup.domain.StoreSetupDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SetupUseCasesModule {
    @Binds
    abstract fun bindGetSetupStateUseCase(impl: ObserveSetupStateUseCaseImpl): ObserveSetupStateUseCase

    @Binds
    abstract fun bindStoreSetupDetailsUseCase(impl: StoreSetupDetailsUseCaseImpl): StoreSetupDetailsUseCase
}
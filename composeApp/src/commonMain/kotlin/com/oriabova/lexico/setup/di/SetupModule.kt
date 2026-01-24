package com.oriabova.lexico.setup.di

import com.oriabova.lexico.setup.data.SetupLocalDataSource
import com.oriabova.lexico.setup.data.SetupRepositoryImpl
import com.oriabova.lexico.setup.domain.GetSetupDetailsUseCase
import com.oriabova.lexico.setup.domain.GetSetupDetailsUseCaseImpl
import com.oriabova.lexico.setup.domain.ObserveSetupDetailsUseCase
import com.oriabova.lexico.setup.domain.ObserveSetupDetailsUseCaseImpl
import com.oriabova.lexico.setup.domain.SetupRepository
import com.oriabova.lexico.setup.domain.StoreSetupDetailsUseCase
import com.oriabova.lexico.setup.domain.StoreSetupDetailsUseCaseImpl
import com.oriabova.lexico.setup.view.SetupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val dataModule = module {
    single { SetupLocalDataSource(get()) }
    single<SetupRepository> { SetupRepositoryImpl(get()) }
}

private val domainModule = module {
    factory { GetSetupDetailsUseCaseImpl(get()) as GetSetupDetailsUseCase }
    factory { ObserveSetupDetailsUseCaseImpl(get()) as ObserveSetupDetailsUseCase }
    factory { StoreSetupDetailsUseCaseImpl(get()) as StoreSetupDetailsUseCase }
}

private val viewModule = module {
    viewModelOf(::SetupViewModel)
}

val setupModule = module {
    includes(dataModule, domainModule, viewModule)
}
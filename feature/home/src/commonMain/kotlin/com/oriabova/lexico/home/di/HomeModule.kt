package com.oriabova.lexico.home.di

import com.oriabova.lexico.home.data.WordsLocalDataSource
import com.oriabova.lexico.home.data.WordsLocalDataSourceImpl
import com.oriabova.lexico.home.data.WordsRepositoryImpl
import com.oriabova.lexico.home.data.mapper.GeneratedWordEntityToDomainMapper
import com.oriabova.lexico.home.data.mapper.GeneratedWordToEntityMapper
import com.oriabova.lexico.home.domain.ObserveSavedWordsUseCase
import com.oriabova.lexico.home.domain.ObserveSavedWordsUseCaseImpl
import com.oriabova.lexico.home.domain.SaveWordUseCase
import com.oriabova.lexico.home.domain.SaveWordUseCaseImpl
import com.oriabova.lexico.home.domain.SkipWordUseCase
import com.oriabova.lexico.home.domain.SkipWordUseCaseImpl
import com.oriabova.lexico.home.domain.WordsRepository
import com.oriabova.lexico.home.view.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val dataModule = module {
    factory { GeneratedWordToEntityMapper() }
    factory { GeneratedWordEntityToDomainMapper() }
    single<WordsLocalDataSource> { WordsLocalDataSourceImpl(get(), get(), get()) }
    single<WordsRepository> { WordsRepositoryImpl(get()) }
}

private val domainModule = module {
    factory<SaveWordUseCase> { SaveWordUseCaseImpl(get()) }
    factory<SkipWordUseCase> { SkipWordUseCaseImpl(get()) }
    factory<ObserveSavedWordsUseCase> { ObserveSavedWordsUseCaseImpl(get()) }
}

private val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}

val homeModule = module {
    includes(dataModule, domainModule, viewModelModule)
}

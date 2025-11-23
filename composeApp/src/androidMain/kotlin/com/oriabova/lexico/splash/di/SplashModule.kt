package com.oriabova.lexico.splash.di

import com.oriabova.lexico.splash.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val splashModule = module {
    includes(splashViewModelModule)
}

val splashViewModelModule = module {
    viewModelOf(::SplashViewModel)
}
package com.oriabova.lexico.core

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appViewModelModule = module {
    viewModelOf(::AppViewModel)
}

val appModule = module {
    includes(appViewModelModule)
}
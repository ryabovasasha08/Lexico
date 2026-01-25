package com.oriabova.lexico.home.di

import com.oriabova.lexico.home.view.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}

val homeModule = module {
    includes(viewModelModule)
}

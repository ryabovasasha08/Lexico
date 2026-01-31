package com.oriabova.lexico.di

import com.oriabova.lexico.core.modules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun startKoinIos(extraModules: List<Module>) {
    startKoin {
        modules(modules + extraModules)
    }
}

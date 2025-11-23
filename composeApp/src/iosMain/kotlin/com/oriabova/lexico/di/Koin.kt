package com.oriabova.lexico.di

import com.oriabova.lexico.modules
import org.koin.core.context.startKoin

fun startKoinIos() {
    startKoin {
        modules(modules)
    }
}
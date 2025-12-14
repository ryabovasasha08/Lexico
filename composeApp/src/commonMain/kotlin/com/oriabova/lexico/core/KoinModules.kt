package com.oriabova.lexico.core

import com.oriabova.lexico.home.di.homeModule
import com.oriabova.lexico.localstorage.localStorageModule
import com.oriabova.lexico.serialization.serializationModule
import com.oriabova.lexico.setup.di.setupModule
import org.koin.core.module.Module

val modules: List<Module> = listOf(
    serializationModule,
    localStorageModule,
    homeModule,
    setupModule,
    appModule,
)
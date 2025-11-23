package com.oriabova.lexico

import org.koin.core.module.Module
import com.oriabova.lexico.serialization.serializationModule
import com.oriabova.lexico.localstorage.localStorageModule
import com.oriabova.lexico.setup.di.setupModule
import com.oriabova.lexico.home.di.homeModule

val modules: List<Module> = listOf(
    serializationModule,
    localStorageModule,
    homeModule,
    setupModule,
)
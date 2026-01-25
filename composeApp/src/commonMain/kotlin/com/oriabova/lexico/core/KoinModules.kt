package com.oriabova.lexico.core

import com.oriabova.lexico.ai.di.aiModule
import com.oriabova.lexico.home.di.homeModule
import com.oriabova.lexico.localstorage.di.localStorageModule
import com.oriabova.lexico.permissions.di.notificationPermissionModule
import com.oriabova.lexico.serialization.serializationModule
import com.oriabova.lexico.setup.di.setupDataModule
import com.oriabova.lexico.setup.di.setupFeatureModule
import org.koin.core.module.Module

val modules: List<Module> = listOf(
    serializationModule,
    aiModule,
    localStorageModule,
    notificationPermissionModule,
    homeModule,
    setupDataModule,
    setupFeatureModule,
    appModule,
)

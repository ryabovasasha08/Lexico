package com.oriabova.lexico.localstorage.di

import org.koin.core.module.Module

expect fun getDatastoreModule(): Module

internal const val dataStoreFileName = "dice.preferences_pb"
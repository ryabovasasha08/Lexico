package com.oriabova.lexico.localstorage.di

import com.oriabova.lexico.localstorage.LocalStorage
import com.oriabova.lexico.localstorage.LocalStorageImpl
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun getDatastoreModule(): Module
internal const val dataStoreFileName = "dice.preferences_pb"

val localStorageModule = module {
    includes(getDatastoreModule())
    single<LocalStorage> { LocalStorageImpl(get(), get()) }
}
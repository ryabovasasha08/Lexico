package com.oriabova.lexico.localstorage

import com.oriabova.lexico.localstorage.di.getDatastoreModule
import org.koin.dsl.module

val localStorageModule = module {
    includes(getDatastoreModule())
    single<LocalStorage> { LocalStorageImpl(get(), get()) }
}
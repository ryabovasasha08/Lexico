package com.oriabova.lexico.localstorage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.oriabova.lexico.localstorage.di.getDatastoreModule
import org.koin.core.module.Module
import org.koin.dsl.module

private const val DATA_STORE_NAME = "LEXICO_DATASTORE"

val localStorageModule = module {
    includes(getDatastoreModule())
    single<LocalStorage> { LocalStorageImpl(get(), get()) }
}
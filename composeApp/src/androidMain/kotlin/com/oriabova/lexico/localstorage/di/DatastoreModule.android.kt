package com.oriabova.lexico.localstorage.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun getDatastoreModule(): Module = module {
    single<DataStore<Preferences>> { createDatastore(androidContext()) }
}

private fun createDatastore(context: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = {
            context.filesDir.resolve(dataStoreFileName).absolutePath.toPath()
        }
    )
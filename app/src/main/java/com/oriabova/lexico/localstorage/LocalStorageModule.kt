package com.oriabova.lexico.localstorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATA_STORE_NAME = "LXC_DATASTORE"

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalStorageModule {

    @Binds
    internal abstract fun bindLocalStorage(impl: LocalStorageImpl): LocalStorage

    companion object {
        @Provides
        @Singleton
        internal fun provideDataStore(
            @ApplicationContext context: Context,
        ): DataStore<Preferences> = PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(DATA_STORE_NAME)
        }
    }
}
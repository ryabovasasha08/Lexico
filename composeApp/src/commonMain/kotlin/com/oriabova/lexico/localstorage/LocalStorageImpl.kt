package com.oriabova.lexico.localstorage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.oriabova.lexico.serialization.JsonSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlin.reflect.KClass

internal class LocalStorageImpl(
    private val dataStore: DataStore<Preferences>,
    private val jsonSerializer: JsonSerializer,
) : LocalStorage {

    override suspend fun getString(key: String, default: String): String = getValue(key, default) { it }
    override suspend fun getStringOrNull(key: String): String? = getValueIfAvailable(key) { it }
    override fun observeString(key: String, default: String): Flow<String> = observeValue(key, default) { it }
    override fun observeStringOrNull(key: String): Flow<String?> = observeValueIfAvailable(key) { it }
    override suspend fun putString(key: String, value: String?) = putValue(key, value)
    override suspend fun updateString(key: String, default: String, transformation: (String) -> String): Boolean =
        updateValue(key, default, transformation) { it }

    override suspend fun getInt(key: String, default: Int): Int = getValue(key, default) { it.toIntOrNull() }
    override fun observeInt(key: String, default: Int): Flow<Int> = observeValue(key, default) { it.toIntOrNull() }
    override suspend fun putInt(key: String, value: Int?) = putValue(key, value)
    override suspend fun updateInt(key: String, default: Int, transformation: (Int) -> Int): Boolean =
        updateValue(key, default, transformation) { it.toIntOrNull() }

    override suspend fun getFloat(key: String, default: Float): Float = getValue(key, default) { it.toFloatOrNull() }
    override fun observeFloat(key: String, default: Float): Flow<Float> = observeValue(key, default) { it.toFloatOrNull() }
    override suspend fun putFloat(key: String, value: Float?) = putValue(key, value)
    override suspend fun updateFloat(key: String, default: Float, transformation: (Float) -> Float): Boolean =
        updateValue(key, default, transformation) { it.toFloatOrNull() }

    override suspend fun getLong(key: String, default: Long): Long = getValue(key, default) { it.toLongOrNull() }
    override suspend fun getLongOrNull(key: String): Long? = getValueIfAvailable(key) { it.toLongOrNull() }
    override fun observeLong(key: String, default: Long): Flow<Long> = observeValue(key, default) { it.toLongOrNull() }
    override suspend fun putLong(key: String, value: Long?) = putValue(key, value)
    override suspend fun updateLong(key: String, default: Long, transformation: (Long) -> Long): Boolean =
        updateValue(key, default, transformation) { it.toLongOrNull() }

    override suspend fun getBoolean(key: String, default: Boolean): Boolean = getValue(key, default) { it.toBooleanStrictOrNull() }
    override suspend fun getBooleanOrNull(key: String): Boolean? = getValueIfAvailable(key) { it.toBooleanStrictOrNull() }
    override fun observeBoolean(key: String, default: Boolean): Flow<Boolean> = observeValue(key, default) { it.toBooleanStrictOrNull() }
    override suspend fun putBoolean(key: String, value: Boolean?) = putValue(key, value)
    override suspend fun updateBoolean(key: String, default: Boolean, transformation: (Boolean) -> Boolean): Boolean =
        updateValue(key, default, transformation) { it.toBooleanStrictOrNull() }


    override suspend fun <T : Any> getData(key: String, cls: KClass<T>, default: T): T =
        getValue(key, default) { jsonSerializer.fromJson(it, cls) }

    override suspend fun <T : Any> getDataOrNull(key: String, cls: KClass<T>): T? =
        getValueIfAvailable(key) { jsonSerializer.fromJson(it, cls) }

    override fun <T : Any> observeData(key: String, cls: KClass<out T>, default: T): Flow<T> = observeValue(key, default) {
        jsonSerializer.fromJson(it, cls)
    }

    override suspend fun <T : Any> putData(key: String, value: T?, cls: KClass<T>): Boolean {
        val jsonValue = value?.let { jsonSerializer.toJson(it, cls) }
        return putValue(key, jsonValue)
    }

    override suspend fun <T : Any> updateData(key: String, default: T, cls: KClass<T>, transformation: (T) -> T) = updateValue(
        key,
        default,
        transformation,
        transformAfterGet = { jsonSerializer.fromJson(it, cls) },
        transformBeforePut = { jsonSerializer.toJson(it, cls) }
    )

    override suspend fun <T : Any> updateNullableData(key: String, cls: KClass<T>, transformation: (T?) -> T?) = updateValueOrNull(
        key,
        transformation,
        transformAfterGet = { jsonSerializer.fromJson(it, cls) },
        transformBeforePut = { jsonSerializer.toJson(it, cls) }
    )

    override suspend fun filterKeys(predicate: (String) -> Boolean): List<String> {
        return dataStore.data.map {
            it.asMap().filterKeys { key -> predicate(key.name) }.map { it.key.name }
        }.firstOrNull().orEmpty()
    }

    private suspend fun <T> updateValueOrNull(
        key: String,
        transformToNewValue: (T?) -> T?,
        transformBeforePut: (T) -> String = { it.toString() },
        transformAfterGet: (String) -> T?
    ): Boolean {
        val prevValue = getValueIfAvailable(key, transformAfterGet)
        val newValue = transformToNewValue(prevValue)
        val stringValue = newValue?.let(transformBeforePut)
        return putValue(key, stringValue)
    }

    private suspend fun <T> updateValue(
        key: String,
        default: T,
        transformToNewValue: (T) -> T,
        transformBeforePut: (T) -> String = { it.toString() },
        transformAfterGet: (String) -> T?
    ): Boolean {
        val prevValue = getValue(key, default, transformAfterGet)
        val newValue = transformToNewValue(prevValue)
        val stringValue = transformBeforePut(newValue)
        return putValue(key, stringValue)
    }

    private suspend fun <T> getValue(
        key: String,
        default: T,
        transformStringValue: (String) -> T?
    ): T {
        val preferenceKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return getValue(preferences, preferenceKey, default, transformStringValue)
    }

    private suspend fun <T> getValueIfAvailable(
        key: String,
        transformStringValue: (String) -> T?
    ): T? {
        val preferenceKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return getValueIfAvailable(preferences, preferenceKey, transformStringValue)
    }

    private fun <T> observeValue(
        key: String,
        default: T,
        transformStringValue: (String) -> T?
    ): Flow<T> {
        val preferenceKey = stringPreferencesKey(key)
        return dataStore.data.map { preferences ->
            getValue(preferences, preferenceKey, default, transformStringValue)
        }
    }

    private fun <T> observeValueIfAvailable(
        key: String,
        transformStringValue: (String) -> T?
    ): Flow<T?> {
        val preferenceKey = stringPreferencesKey(key)
        return dataStore.data.map { preferences ->
            getValueIfAvailable(preferences, preferenceKey, transformStringValue)
        }
    }

    private fun <T> getValue(
        preferences: Preferences,
        key: Preferences.Key<String>,
        default: T,
        transformStringValue: (String) -> T?
    ): T {
        val value = preferences[key] ?: return default
        return transformStringValue(value) ?: default

    }

    private fun <T> getValueIfAvailable(
        preferences: Preferences,
        key: Preferences.Key<String>,
        transformStringValue: (String) -> T?
    ): T? {
        val value = preferences[key] ?: return null
        return transformStringValue(value)
    }

    private suspend fun putValue(key: String, value: Any?): Boolean {
        val stringPreferencesKey = stringPreferencesKey(key)

        return try {
            dataStore.edit { preferences ->
                if (value != null) {
                    preferences[stringPreferencesKey] = value.toString()
                } else {
                    preferences.remove(stringPreferencesKey)
                }
            }
            true
        } catch (e: Exception) {
            println("Error putting value to local storage: ${e.message ?: ""} $e")
            false
        }
    }
}
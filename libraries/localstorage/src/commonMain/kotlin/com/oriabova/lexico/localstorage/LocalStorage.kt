package com.oriabova.lexico.localstorage

import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

interface LocalStorage {
    suspend fun getString(key: String, default: String): String
    suspend fun getStringOrNull(key: String): String?
    fun observeString(key: String, default: String): Flow<String>
    fun observeStringOrNull(key: String): Flow<String?>
    suspend fun putString(key: String, value: String?): Boolean
    suspend fun updateString(key: String, default: String, transformation: (String) -> String): Boolean

    suspend fun getInt(key: String, default: Int): Int
    fun observeInt(key: String, default: Int): Flow<Int>
    suspend fun putInt(key: String, value: Int?): Boolean
    suspend fun updateInt(key: String, default: Int, transformation: (Int) -> Int): Boolean

    suspend fun getFloat(key: String, default: Float): Float
    fun observeFloat(key: String, default: Float): Flow<Float>
    suspend fun putFloat(key: String, value: Float?): Boolean
    suspend fun updateFloat(key: String, default: Float, transformation: (Float) -> Float): Boolean

    suspend fun getLong(key: String, default: Long): Long
    suspend fun getLongOrNull(key: String): Long?
    fun observeLong(key: String, default: Long): Flow<Long>
    suspend fun putLong(key: String, value: Long?): Boolean
    suspend fun updateLong(key: String, default: Long, transformation: (Long) -> Long): Boolean

    suspend fun getBoolean(key: String, default: Boolean): Boolean
    suspend fun getBooleanOrNull(key: String): Boolean?
    fun observeBoolean(key: String, default: Boolean): Flow<Boolean>
    suspend fun putBoolean(key: String, value: Boolean?): Boolean
    suspend fun updateBoolean(key: String, default: Boolean, transformation: (Boolean) -> Boolean): Boolean

    suspend fun <T : Any> getData(key: String, cls: KClass<T>, default: T): T
    suspend fun <T : Any> getDataOrNull(key: String, cls: KClass<T>): T?
    fun <T : Any> observeData(key: String, cls: KClass<out T>, default: T): Flow<T>
    suspend fun <T : Any> putData(key: String, value: T?, cls: KClass<T>): Boolean
    suspend fun <T : Any> updateData(key: String, default: T, cls: KClass<T>, transformation: (T) -> T): Boolean
    suspend fun <T : Any> updateNullableData(key: String, cls: KClass<T>, transformation: (T?) -> T?): Boolean

    suspend fun filterKeys(predicate: (String) -> Boolean): List<String>
}
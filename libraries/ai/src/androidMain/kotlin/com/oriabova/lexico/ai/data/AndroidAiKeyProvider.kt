package com.oriabova.lexico.ai.data

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

internal class AndroidAiKeyProvider() : AiKeyProvider {
    override suspend fun getGeminiApiKey(): String {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val settings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(0)
            .build()
        remoteConfig.setConfigSettingsAsync(settings)

        fetchAndActivate(remoteConfig)

        return remoteConfig.getString("gemini_api_key").trim()
    }
}

private suspend fun fetchAndActivate(remoteConfig: FirebaseRemoteConfig): Boolean =
    suspendCancellableCoroutine { continuation ->
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (continuation.isActive) {
                continuation.resume(task.isSuccessful && task.result == true)
            }
        }
    }

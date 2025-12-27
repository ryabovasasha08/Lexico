package com.oriabova.lexico.notiifcations

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.UserNotifications.UNMutableNotificationContent
import platform.UserNotifications.UNNotificationRequest
import platform.UserNotifications.UNNotificationSound
import platform.UserNotifications.UNNotificationTrigger
import platform.UserNotifications.UNUserNotificationCenter
import kotlin.coroutines.resume

class NotificationPoster(
    private val center: UNUserNotificationCenter = UNUserNotificationCenter.Companion.currentNotificationCenter()
) {
    fun post(
        identifier: String,
        title: String,
        body: String,
        sound: UNNotificationSound?,
        trigger: UNNotificationTrigger
    ) {
        val content = UNMutableNotificationContent().apply {
            setTitle(title)
            setBody(body)
            if (sound != null) {
                setSound(sound)
            }
        }
        val request = UNNotificationRequest.Companion.requestWithIdentifier(
            identifier,
            content,
            trigger
        )

        center.addNotificationRequest(request, null)
    }

    suspend fun cancelByPrefix(prefix: String) {
        val identifiers = pendingIdentifiers(prefix)
        if (identifiers.isNotEmpty()) {
            center.removePendingNotificationRequestsWithIdentifiers(identifiers)
        }
    }

    private suspend fun pendingIdentifiers(prefix: String): List<String> =
        suspendCancellableCoroutine { continuation ->
            center.getPendingNotificationRequestsWithCompletionHandler { requests ->
                val ids = mutableListOf<String>()

                requests?.forEach { request ->
                    (request as? UNNotificationRequest)?.identifier?.let {
                        if (it.startsWith(prefix)) {
                            ids.add(it)
                        }
                    }
                }

                continuation.resume(ids)
            }
        }
}
package com.oriabova.lexico.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.oriabova.lexico.R

private const val NotificationChannelId = "lexico_daily_words"
private const val NotificationId = 1001

class NotificationsPoster(private val context: Context) {

    // Shows dummy notification for now. Will be improved by passing a payload to the function
    fun showNotification() {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        ensureChannel(manager)

        val notification = NotificationCompat.Builder(context, NotificationChannelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Your daily words are ready")
            .setContentText("Open Lexico to learn something new")
            .setAutoCancel(true)
            .build()

        manager.notify(NotificationId, notification)
    }

    private fun ensureChannel(manager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationChannelId,
                "Daily words",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Reminders to learn new words on your schedule."
            }
            manager.createNotificationChannel(channel)
        }
    }
}
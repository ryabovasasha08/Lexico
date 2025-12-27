package com.oriabova.lexico.newwordscheduler.platform

import com.oriabova.lexico.newwordscheduler.DayOfWeekMapper
import com.oriabova.lexico.newwordscheduler.domain.NewWordScheduler
import com.oriabova.lexico.newwordscheduler.domain.TimesPerDayCalculator
import com.oriabova.lexico.newwordscheduler.domain.model.NewWordSchedule
import com.oriabova.lexico.newwordscheduler.domain.model.TimeOfDay
import com.oriabova.lexico.notiifcations.NotificationPoster
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateComponents
import platform.UserNotifications.UNCalendarNotificationTrigger
import platform.UserNotifications.UNNotificationSound

private const val NotificationIdentifierPrefix = "lexico.schedule.newword."

internal class IosNewWordScheduler(
    private val dayOfWeekMapper: DayOfWeekMapper,
    private val notificationPoster: NotificationPoster
) : NewWordScheduler {
    override suspend fun schedule(newWordSchedule: NewWordSchedule) {
        cancel()

        val title = "Lexico"
        val body = "Your daily words are ready."
        val sound = UNNotificationSound.defaultSound()

        val times = TimesPerDayCalculator
            .calculateTimes(newWordSchedule.timesPerDay, newWordSchedule.startTime)

        val weekdays = newWordSchedule.days.map(dayOfWeekMapper::map)

        for (weekday in weekdays) {
            for (time in times) {
                notificationPoster.post(
                    buildIdentifier(weekday, time),
                    title,
                    body,
                    sound,
                    buildTrigger(weekday, time),
                )
            }
        }
    }

    override suspend fun cancel() {
        notificationPoster.cancelByPrefix(NotificationIdentifierPrefix)
    }

    private fun buildTrigger(
        weekday: Int,
        time: TimeOfDay
    ): UNCalendarNotificationTrigger {
        val components = NSDateComponents().apply {
            hour = time.hour.toLong()
            minute = time.minute.toLong()
            this.weekday = weekday.toLong()
            calendar = NSCalendar.currentCalendar
        }

        return UNCalendarNotificationTrigger
            .triggerWithDateMatchingComponents(
                dateComponents = components,
                repeats = true
            )
    }

    private fun buildIdentifier(
        weekday: Int,
        time: TimeOfDay,
    ): String {
        val dayPart = weekday.toString()
        return "$NotificationIdentifierPrefix$dayPart.${time.hour}.${time.minute}"
    }
}

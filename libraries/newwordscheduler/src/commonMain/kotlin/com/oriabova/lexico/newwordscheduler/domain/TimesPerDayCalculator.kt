package com.oriabova.lexico.newwordscheduler.domain

import com.oriabova.lexico.newwordscheduler.domain.model.TimeOfDay

private const val MinutesPerDay = 24 * 60
private const val MinutesInHour = 60

internal object TimesPerDayCalculator {
    fun calculateTimes(
        timesPerDay: Int,
        startTime: TimeOfDay = TimeOfDay.Default
    ): List<TimeOfDay> {
        check(timesPerDay > 0) { "timesPerDay must be greater than 0" }

        val intervalMinutes = MinutesPerDay / timesPerDay
        val startMinutes = startTime.hour * MinutesInHour + startTime.minute

        val minuteOffsets = (0 until timesPerDay).map { index ->
            (startMinutes + index * intervalMinutes) % MinutesPerDay
        }

        return minuteOffsets
            .distinct()
            .sorted()
            .map { minutes ->
                TimeOfDay(
                    hour = minutes / MinutesInHour,
                    minute = minutes % MinutesInHour
                )
            }
    }
}

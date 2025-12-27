package com.oriabova.lexico.newwordscheduler.platform

import com.oriabova.lexico.newwordscheduler.domain.TimesPerDayCalculator
import com.oriabova.lexico.newwordscheduler.domain.model.NewWordSchedule
import com.oriabova.lexico.newwordscheduler.domain.model.TimeOfDay
import com.oriabova.lexico.newwordscheduler.mappers.DayOfWeekMapper
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

internal class TriggerTimeCalculator(private val dayOfWeekMapper: DayOfWeekMapper) {
    fun nextTriggerTime(
        newWordSchedule: NewWordSchedule,
        now: ZonedDateTime,
        zoneId: ZoneId = ZoneId.systemDefault()
    ): ZonedDateTime {
        val times = TimesPerDayCalculator
            .calculateTimes(newWordSchedule.timesPerDay, newWordSchedule.startTime)
            .map { LocalTime.of(it.hour, it.minute) }
            .sorted()

        val allowedDays = newWordSchedule.days.map { dayOfWeekMapper.map(it) }

        for (offset in 0..7) {
            val date = LocalDate.now(zoneId).plusDays(offset.toLong())
            if (date.dayOfWeek !in allowedDays) continue
            for (time in times) {
                val candidate = ZonedDateTime.of(date, time, zoneId)
                if (candidate.isAfter(now)) {
                    return candidate
                }
            }
        }

        val fallbackDate = LocalDate.now(zoneId).plusDays(1)
        val fallbackTime = times.firstOrNull() ?: getFallbackTime()
        return ZonedDateTime.of(fallbackDate, fallbackTime, zoneId)
    }

    private fun getFallbackTime() = with(TimeOfDay.Default) { LocalTime.of(hour, minute) }

}

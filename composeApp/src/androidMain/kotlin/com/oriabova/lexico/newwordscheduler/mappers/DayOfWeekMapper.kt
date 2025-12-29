package com.oriabova.lexico.newwordscheduler.mappers

import com.oriabova.lexico.newwordscheduler.domain.model.DayOfWeek
import java.time.DayOfWeek as JavaDayOfWeek

internal class DayOfWeekMapper {
    fun map(from: DayOfWeek): JavaDayOfWeek = when (from) {
        DayOfWeek.MONDAY -> JavaDayOfWeek.MONDAY
        DayOfWeek.TUESDAY -> JavaDayOfWeek.TUESDAY
        DayOfWeek.WEDNESDAY -> JavaDayOfWeek.WEDNESDAY
        DayOfWeek.THURSDAY -> JavaDayOfWeek.THURSDAY
        DayOfWeek.FRIDAY -> JavaDayOfWeek.FRIDAY
        DayOfWeek.SATURDAY -> JavaDayOfWeek.SATURDAY
        DayOfWeek.SUNDAY -> JavaDayOfWeek.SUNDAY
    }
}
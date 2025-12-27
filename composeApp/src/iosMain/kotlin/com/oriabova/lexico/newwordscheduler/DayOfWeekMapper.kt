package com.oriabova.lexico.newwordscheduler

import com.oriabova.lexico.newwordscheduler.domain.model.DayOfWeek

internal class DayOfWeekMapper {
    fun map(dayOfWeek: DayOfWeek): Int = when (dayOfWeek) {
        DayOfWeek.MONDAY -> 2
        DayOfWeek.TUESDAY -> 3
        DayOfWeek.WEDNESDAY -> 4
        DayOfWeek.THURSDAY -> 5
        DayOfWeek.FRIDAY -> 6
        DayOfWeek.SATURDAY -> 7
        DayOfWeek.SUNDAY -> 1
    }
}
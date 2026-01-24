package com.oriabova.lexico.newwordscheduler.domain.model

internal data class NewWordSchedule(
    val days: Set<DayOfWeek>,
    val timesPerDay: Int,
    val startTime: TimeOfDay = TimeOfDay.Default,
)
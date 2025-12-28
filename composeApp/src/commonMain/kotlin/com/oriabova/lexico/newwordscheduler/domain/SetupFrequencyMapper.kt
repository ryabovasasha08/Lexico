package com.oriabova.lexico.newwordscheduler.domain

import com.oriabova.lexico.newwordscheduler.domain.model.DayOfWeek
import com.oriabova.lexico.newwordscheduler.domain.model.NewWordSchedule
import com.oriabova.lexico.setup.domain.model.SetupFrequency

internal class SetupFrequencyToWordScheduleMapper {
    fun map(frequency: SetupFrequency): NewWordSchedule = when (frequency) {
        SetupFrequency.LIGHT_DAILY -> NewWordSchedule(
            days = DayOfWeek.entries.toSet(),
            timesPerDay = 1,
        )

        SetupFrequency.HEAVY_DAILY -> NewWordSchedule(
            days = DayOfWeek.entries.toSet(),
            timesPerDay = 5
        )

        SetupFrequency.WEEKDAYS -> NewWordSchedule(
            days = setOf(
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY
            ),
            timesPerDay = 1,
        )

        SetupFrequency.WEEKENDER -> NewWordSchedule(
            days = setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
            timesPerDay = 1,
        )
    }
}

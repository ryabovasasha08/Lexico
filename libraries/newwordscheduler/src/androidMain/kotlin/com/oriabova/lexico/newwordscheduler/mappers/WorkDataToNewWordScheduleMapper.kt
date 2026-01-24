package com.oriabova.lexico.newwordscheduler.mappers

import androidx.work.Data
import com.oriabova.lexico.newwordscheduler.domain.model.DayOfWeek
import com.oriabova.lexico.newwordscheduler.domain.model.NewWordSchedule
import com.oriabova.lexico.newwordscheduler.domain.model.TimeOfDay
import com.oriabova.lexico.newwordscheduler.platform.utils.WorkDataKeys.SCHEDULE_DAYS_KEY
import com.oriabova.lexico.newwordscheduler.platform.utils.WorkDataKeys.SCHEDULE_START_HOUR_KEY
import com.oriabova.lexico.newwordscheduler.platform.utils.WorkDataKeys.SCHEDULE_START_MINUTE_KEY
import com.oriabova.lexico.newwordscheduler.platform.utils.WorkDataKeys.SCHEDULE_TIMES_PER_DAY_KEY

internal class WorkDataToNewWordScheduleMapper {
    fun map(data: Data): NewWordSchedule = with(data) {
        val days = getStringArray(SCHEDULE_DAYS_KEY)
            ?.mapNotNull { DayOfWeek.valueOf(it) }
            ?.toSet()
            ?: emptySet()
        val timesPerDay = getInt(SCHEDULE_TIMES_PER_DAY_KEY, 1)
        val startHour = getInt(SCHEDULE_START_HOUR_KEY, TimeOfDay.Default.hour)
        val startMinute = getInt(SCHEDULE_START_MINUTE_KEY, TimeOfDay.Default.minute)

        NewWordSchedule(
            days = days,
            timesPerDay = timesPerDay,
            startTime = TimeOfDay(startHour, startMinute)
        )
    }
}

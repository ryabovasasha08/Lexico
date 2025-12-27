package com.oriabova.lexico.newwordscheduler.mappers

import androidx.work.Data
import com.oriabova.lexico.newwordscheduler.domain.model.NewWordSchedule
import com.oriabova.lexico.newwordscheduler.platform.utils.WorkDataKeys.SCHEDULE_DAYS_KEY
import com.oriabova.lexico.newwordscheduler.platform.utils.WorkDataKeys.SCHEDULE_START_HOUR_KEY
import com.oriabova.lexico.newwordscheduler.platform.utils.WorkDataKeys.SCHEDULE_START_MINUTE_KEY
import com.oriabova.lexico.newwordscheduler.platform.utils.WorkDataKeys.SCHEDULE_TIMES_PER_DAY_KEY

internal class NewWordScheduleToWorkDataMapper {
    fun toData(schedule: NewWordSchedule): Data = with (schedule) {
        Data.Builder()
            .putStringArray(SCHEDULE_DAYS_KEY, days.map { it.toString() }.toTypedArray())
            .putInt(SCHEDULE_TIMES_PER_DAY_KEY, timesPerDay)
            .putInt(SCHEDULE_START_HOUR_KEY, startTime.hour)
            .putInt(SCHEDULE_START_MINUTE_KEY, startTime.minute)
            .build()
    }
}

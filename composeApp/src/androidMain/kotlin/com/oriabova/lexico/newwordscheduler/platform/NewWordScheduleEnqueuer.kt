package com.oriabova.lexico.newwordscheduler.platform

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.oriabova.lexico.newwordscheduler.domain.model.NewWordSchedule
import com.oriabova.lexico.newwordscheduler.mappers.NewWordScheduleToWorkDataMapper
import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

private const val NewWordScheduleWorkName = "lexico_notification_schedule"

internal class NewWordScheduleEnqueuer(
    private val context: Context,
    private val timeCalculator: TriggerTimeCalculator,
    private val wordScheduleToDataMapper: NewWordScheduleToWorkDataMapper
) {
    fun scheduleNext(schedule: NewWordSchedule) {
        val now = ZonedDateTime.now(ZoneId.systemDefault())
        val nextTime = timeCalculator.nextTriggerTime(schedule, now)
        val delayMillis = Duration.between(now, nextTime).toMillis().coerceAtLeast(0)

        val request = OneTimeWorkRequestBuilder<NewWordScheduleWorker>()
            .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS)
            .setInputData(wordScheduleToDataMapper.toData(schedule))
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(NewWordScheduleWorkName, ExistingWorkPolicy.REPLACE, request)
    }

    fun cancel() {
        WorkManager.getInstance(context).cancelUniqueWork(NewWordScheduleWorkName)
    }
}

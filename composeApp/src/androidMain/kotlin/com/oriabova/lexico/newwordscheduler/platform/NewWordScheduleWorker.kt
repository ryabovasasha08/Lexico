package com.oriabova.lexico.newwordscheduler.platform

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.oriabova.lexico.newwordscheduler.mappers.WorkDataToNewWordScheduleMapper
import com.oriabova.lexico.notifications.NotificationsPoster
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class NewWordScheduleWorker(
    appContext: Context,
    params: WorkerParameters,
) : CoroutineWorker(appContext, params), KoinComponent {
    private val enqueuer: NewWordScheduleEnqueuer by inject()
    private val dataToNewWordScheduleMapper: WorkDataToNewWordScheduleMapper by inject()
    private val notificationsPoster: NotificationsPoster by inject()

    override suspend fun doWork(): Result {
        val newWordSchedule = dataToNewWordScheduleMapper.map(inputData)

        notificationsPoster.showNotification()
        enqueuer.scheduleNext(newWordSchedule)

        return Result.success()
    }
}

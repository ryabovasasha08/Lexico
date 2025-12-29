package com.oriabova.lexico.newwordscheduler.platform

import com.oriabova.lexico.newwordscheduler.domain.NewWordScheduler
import com.oriabova.lexico.newwordscheduler.domain.model.NewWordSchedule

internal class AndroidNewWordScheduler(
    private val enqueuer: NewWordScheduleEnqueuer
) : NewWordScheduler {
    override suspend fun schedule(newWordSchedule: NewWordSchedule) {
        enqueuer.scheduleNext(newWordSchedule)
    }

    override suspend fun cancel() {
        enqueuer.cancel()
    }
}

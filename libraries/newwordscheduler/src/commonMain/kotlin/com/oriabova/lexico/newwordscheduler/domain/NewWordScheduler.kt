package com.oriabova.lexico.newwordscheduler.domain

import com.oriabova.lexico.newwordscheduler.domain.model.NewWordSchedule

internal interface NewWordScheduler {
    suspend fun schedule(newWordSchedule: NewWordSchedule)
    suspend fun cancel()
}

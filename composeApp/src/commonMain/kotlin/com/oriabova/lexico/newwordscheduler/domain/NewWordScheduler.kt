package com.oriabova.lexico.newwordscheduler.domain

import com.oriabova.lexico.newwordscheduler.domain.model.NewWordSchedule

interface NewWordScheduler {
    suspend fun schedule(newWordSchedule: NewWordSchedule)
    suspend fun cancel()
}

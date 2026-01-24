package com.oriabova.lexico.newwordscheduler.domain

import com.oriabova.lexico.setup.domain.GetSetupDetailsUseCase

internal class ScheduleNewWordNotificationsUseCaseImpl(
    private val scheduler: NewWordScheduler,
    private val getSetupDetailsUseCase: GetSetupDetailsUseCase,
    private val frequencyToNewWordScheduleMapper: SetupFrequencyToWordScheduleMapper,
): ScheduleNewWordNotificationsUseCase {
    override suspend fun invoke() {
        val setupDetails = getSetupDetailsUseCase()
        val frequency = setupDetails.frequency
        if (frequency == null || !setupDetails.notificationPermissionGranted) {
            scheduler.cancel()
        } else {
            scheduler.schedule(frequencyToNewWordScheduleMapper.map(frequency))
        }
    }
}

package com.oriabova.lexico.newwordscheduler.di

import com.oriabova.lexico.newwordscheduler.domain.ScheduleNewWordNotificationsUseCase
import com.oriabova.lexico.newwordscheduler.domain.ScheduleNewWordNotificationsUseCaseImpl
import com.oriabova.lexico.newwordscheduler.domain.SetupFrequencyToWordScheduleMapper
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun getNewWordSchedulerPlatformModule(): Module

private val domainModule = module {
    single { SetupFrequencyToWordScheduleMapper() }
    factory<ScheduleNewWordNotificationsUseCase> {
        ScheduleNewWordNotificationsUseCaseImpl(get(), get(), get())
    }
}

val newWordSchedulerModule = module {
    includes(getNewWordSchedulerPlatformModule(), domainModule)
}

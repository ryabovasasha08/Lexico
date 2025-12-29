package com.oriabova.lexico.newwordscheduler.di

import com.oriabova.lexico.newwordscheduler.domain.ScheduleNewWordNotificationsUseCase
import com.oriabova.lexico.newwordscheduler.domain.ScheduleNewWordNotificationsUseCaseImpl
import com.oriabova.lexico.newwordscheduler.domain.SetupFrequencyToWordScheduleMapper
import com.oriabova.lexico.setup.domain.GetSetupDetailsUseCase
import com.oriabova.lexico.setup.domain.GetSetupDetailsUseCaseImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun getNewWordSchedulerPlatformModule(): Module

private val domainModule = module {
    single { SetupFrequencyToWordScheduleMapper() }
    factory<GetSetupDetailsUseCase> { GetSetupDetailsUseCaseImpl(get()) }
    factory<ScheduleNewWordNotificationsUseCase> {
        ScheduleNewWordNotificationsUseCaseImpl(get(), get(), get())
    }
}

val newWordSchedulerModule = module {
    includes(getNewWordSchedulerPlatformModule(), domainModule)
}

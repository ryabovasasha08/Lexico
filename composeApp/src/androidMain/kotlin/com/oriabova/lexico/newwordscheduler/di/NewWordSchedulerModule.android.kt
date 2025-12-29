package com.oriabova.lexico.newwordscheduler.di

import com.oriabova.lexico.newwordscheduler.domain.NewWordScheduler
import com.oriabova.lexico.newwordscheduler.mappers.DayOfWeekMapper
import com.oriabova.lexico.newwordscheduler.mappers.NewWordScheduleToWorkDataMapper
import com.oriabova.lexico.newwordscheduler.mappers.WorkDataToNewWordScheduleMapper
import com.oriabova.lexico.newwordscheduler.platform.AndroidNewWordScheduler
import com.oriabova.lexico.newwordscheduler.platform.NewWordScheduleEnqueuer
import com.oriabova.lexico.newwordscheduler.platform.TriggerTimeCalculator
import com.oriabova.lexico.notifications.di.notificationsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun getNewWordSchedulerPlatformModule(): Module = module {
    includes(notificationsModule())

    single { DayOfWeekMapper() }
    single { NewWordScheduleToWorkDataMapper() }
    single { WorkDataToNewWordScheduleMapper() }
    single { TriggerTimeCalculator(get()) }
    single { NewWordScheduleEnqueuer(androidContext(), get(), get()) }
    single<NewWordScheduler> { AndroidNewWordScheduler(get()) }
}

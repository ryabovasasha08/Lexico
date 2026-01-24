package com.oriabova.lexico.newwordscheduler.di

import com.oriabova.lexico.newwordscheduler.DayOfWeekMapper
import com.oriabova.lexico.newwordscheduler.domain.NewWordScheduler
import com.oriabova.lexico.newwordscheduler.platform.IosNewWordScheduler
import com.oriabova.lexico.notifications.notificationsModule
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun getNewWordSchedulerPlatformModule(): Module = module {
    includes(notificationsModule())

    single { DayOfWeekMapper() }
    single<NewWordScheduler> { IosNewWordScheduler(get(), get()) }
}

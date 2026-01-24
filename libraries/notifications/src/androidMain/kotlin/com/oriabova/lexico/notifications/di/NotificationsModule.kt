package com.oriabova.lexico.notifications.di

import com.oriabova.lexico.notifications.NotificationsPoster
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

fun notificationsModule(): Module = module {
    single { NotificationsPoster(androidContext()) }
}
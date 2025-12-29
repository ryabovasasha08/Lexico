package com.oriabova.lexico.notifications

import org.koin.core.module.Module
import org.koin.dsl.module

fun notificationsModule(): Module = module {
    single { NotificationPoster() }
}

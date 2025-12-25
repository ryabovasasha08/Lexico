package com.oriabova.lexico.notifications.di

import com.oriabova.lexico.notifications.data.NotificationPermissionLocalDataSource
import com.oriabova.lexico.notifications.data.NotificationPermissionRepositoryImpl
import com.oriabova.lexico.notifications.domain.NotificationPermissionRepository
import com.oriabova.lexico.notifications.domain.StoreNotificationPermissionRequestedUseCase
import com.oriabova.lexico.notifications.domain.WasNotificationPermissionRequestedUseCase
import org.koin.dsl.module

private val dataModule = module {
    single { NotificationPermissionLocalDataSource(get()) }
    single<NotificationPermissionRepository> {
        NotificationPermissionRepositoryImpl(get())
    }
}

private val domainModule = module {
    factory { StoreNotificationPermissionRequestedUseCase(get()) }
    factory { WasNotificationPermissionRequestedUseCase(get()) }
}

val notificationPermissionModule = module {
    includes(dataModule, domainModule)
}

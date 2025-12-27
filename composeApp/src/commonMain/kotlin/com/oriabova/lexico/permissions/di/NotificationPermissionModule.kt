package com.oriabova.lexico.permissions.di

import com.oriabova.lexico.permissions.data.NotificationPermissionLocalDataSource
import com.oriabova.lexico.permissions.data.NotificationPermissionRepositoryImpl
import com.oriabova.lexico.permissions.domain.NotificationPermissionRepository
import com.oriabova.lexico.permissions.domain.StoreNotificationPermissionRequestedUseCase
import com.oriabova.lexico.permissions.domain.WasNotificationPermissionRequestedUseCase
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

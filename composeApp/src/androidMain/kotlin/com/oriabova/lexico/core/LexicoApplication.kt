package com.oriabova.lexico.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class LexicoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }

    private fun initKoin(app: Application): KoinApplication =
        startKoin {
            androidContext(app)
            androidLogger()
            modules(modules)
        }
}
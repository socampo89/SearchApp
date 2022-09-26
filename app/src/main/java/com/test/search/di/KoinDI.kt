package com.test.search.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinDI(private val application: Application) {
    fun init() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(application)
            androidFileProperties()
            modules(listOf(appModule, searchModule))
        }
    }
}
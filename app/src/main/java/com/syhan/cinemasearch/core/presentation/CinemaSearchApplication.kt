package com.syhan.cinemasearch.core.presentation

import android.app.Application
import com.syhan.cinemasearch.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CinemaSearchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CinemaSearchApplication)
            modules(appModule)
        }
    }
}
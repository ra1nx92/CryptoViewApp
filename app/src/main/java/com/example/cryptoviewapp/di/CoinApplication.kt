package com.example.cryptoviewapp.di

import android.app.Application
import androidx.work.Configuration
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin


class CoinApplication : Application(), KoinComponent, Configuration.Provider {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(applicationModule, screenModule, workerModule)
            androidContext(this@CoinApplication)
        }
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .build()
    }
}
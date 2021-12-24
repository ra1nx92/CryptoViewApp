package com.example.cryptoviewapp.di

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoviewapp.data.workers.RefreshDataWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin


class CoinApplication : Application(), Configuration.Provider {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(applicationModule, screenModule)
            androidContext(this@CoinApplication)
        }
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .build()
    }
}
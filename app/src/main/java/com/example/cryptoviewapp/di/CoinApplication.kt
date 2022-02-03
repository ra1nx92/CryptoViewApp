package com.example.cryptoviewapp.di

import android.app.Application
import com.example.cryptoviewapp.data.workers.RefreshDataWorkerFactory
import javax.inject.Inject

class CoinApplication : Application(),androidx.work.Configuration.Provider {
    @Inject
    lateinit var dataWorkerFactory: RefreshDataWorkerFactory
    val component by lazy {
        DaggerCoinComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): androidx.work.Configuration {
        return androidx.work.Configuration.Builder()
            .setWorkerFactory(dataWorkerFactory)
            .build()
    }
}
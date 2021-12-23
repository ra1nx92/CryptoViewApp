package com.example.cryptoviewapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(applicationModule, screenModule)
            androidContext(this@CoinApplication)
        }
    }
}
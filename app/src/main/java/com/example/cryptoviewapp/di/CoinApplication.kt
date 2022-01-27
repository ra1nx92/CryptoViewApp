package com.example.cryptoviewapp.di

import android.app.Application

class CoinApplication : Application() {
    val component by lazy {
        DaggerCoinComponent.factory().create(this)
    }
}
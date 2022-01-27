package com.example.cryptoviewapp.di

import android.app.Application
import androidx.lifecycle.ViewModel
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class])
interface CoinComponent {
    fun injectViewModel(vm:ViewModel)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application):CoinComponent
    }
}
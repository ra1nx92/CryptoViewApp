package com.example.cryptoviewapp.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.cryptoviewapp.data.workers.RefreshDataWorkerFactory
import com.example.cryptoviewapp.presentation.fragments.CoinInfoFragment
import com.example.cryptoviewapp.presentation.fragments.DetalInfoCoinFragment
import dagger.BindsInstance
import dagger.Component
@ApplicatoinScope
@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface CoinComponent {
    fun inject(fragment: CoinInfoFragment)
    fun inject(fragment: DetalInfoCoinFragment)
    fun inject(application: CoinApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application):CoinComponent
    }
}
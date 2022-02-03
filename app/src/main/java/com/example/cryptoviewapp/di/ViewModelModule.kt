package com.example.cryptoviewapp.di

import androidx.lifecycle.ViewModel
import com.example.cryptoviewapp.presentation.viewmodels.CoinInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinInfoViewModel::class)
    fun bindsCoinInfoViewModel(vm:CoinInfoViewModel):ViewModel
}
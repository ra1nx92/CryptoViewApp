package com.example.cryptoviewapp.di

import com.example.cryptoviewapp.presentation.viewmodels.CoinInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val screenModule = module {
        viewModel<CoinInfoViewModel> { CoinInfoViewModel(get(),get(),get()) }
}
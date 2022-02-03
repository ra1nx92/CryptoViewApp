package com.example.cryptoviewapp.di

import com.example.cryptoviewapp.data.repository.CoinRepositoryImpl
import com.example.cryptoviewapp.domain.CoinRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {
    @Binds
    @ApplicatoinScope
    fun bindsRepository(impl: CoinRepositoryImpl): CoinRepository
}
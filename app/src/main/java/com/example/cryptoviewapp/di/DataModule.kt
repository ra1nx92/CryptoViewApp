package com.example.cryptoviewapp.di

import android.app.Application
import com.example.cryptoviewapp.data.database.AppDatabase
import com.example.cryptoviewapp.data.database.CoinInfoDao
import com.example.cryptoviewapp.data.database.CoinInfoDao_Impl
import com.example.cryptoviewapp.data.mapper.CoinMappers
import com.example.cryptoviewapp.data.network.ApiFact
import com.example.cryptoviewapp.data.network.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    fun binds(impl: CoinMappers.Base):CoinMappers

    companion object{
        @Provides
        @ApplicatoinScope
        fun providesDataBase(application: Application): CoinInfoDao {
                return AppDatabase.getInstance(application).coinPriseInfoDao()
        }

        @Provides
        @ApplicatoinScope
        fun provideApiFactory():ApiService{
            return ApiFact.apiService
        }
    }
}
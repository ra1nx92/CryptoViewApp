package com.example.cryptoviewapp.di

import android.content.Context
import androidx.work.WorkerParameters
import com.example.cryptoviewapp.data.database.AppDatabase
import com.example.cryptoviewapp.data.database.CoinInfoDao
import com.example.cryptoviewapp.data.mapper.CoinMappers
import com.example.cryptoviewapp.data.network.ApiFact
import com.example.cryptoviewapp.data.network.ApiService
import com.example.cryptoviewapp.data.repository.CoinRepositoryImpl
import com.example.cryptoviewapp.data.workers.RefreshDataWorker
import com.example.cryptoviewapp.domain.CoinRepository
import com.example.cryptoviewapp.domain.GetCoinInfoListUseCase
import com.example.cryptoviewapp.domain.GetCoinInfoUseCase
import com.example.cryptoviewapp.domain.GetLoadDataUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val applicationModule = module {
    fun provideApi(): ApiService {
        return ApiFact.apiService
    }

    fun provideDataBase(context: Context): CoinInfoDao {
        return AppDatabase.getInstance(context).coinPriseInfoDao()
    }

    fun provideMapper(): CoinMappers {
        return CoinMappers.Base()
    }

    fun provideRepository(
        context: Context,
        mappers: CoinMappers,
        coinInfoDao: CoinInfoDao
    ): CoinRepository {
        return CoinRepositoryImpl(context, mappers, coinInfoDao)
    }

    fun provideCoinInfoListUseCase(repository: CoinRepository): GetCoinInfoListUseCase {
        return GetCoinInfoListUseCase(repository)
    }

    fun provideCoinInfoUseCase(repository: CoinRepository): GetCoinInfoUseCase {
        return GetCoinInfoUseCase(repository)
    }

    fun provideLoadDataUseCase(repository: CoinRepository): GetLoadDataUseCase {
        return GetLoadDataUseCase(repository)
    }

    single<ApiService> { provideApi() }
    single<CoinInfoDao> { provideDataBase(androidContext()) }
    single<CoinRepository> { provideRepository(androidContext(), get(), get()) }
    factory<CoinMappers> { provideMapper() }
    factory<GetCoinInfoListUseCase> { provideCoinInfoListUseCase(get()) }
    factory<GetCoinInfoUseCase> { provideCoinInfoUseCase(get()) }
    factory<GetLoadDataUseCase> { provideLoadDataUseCase(get()) }

}
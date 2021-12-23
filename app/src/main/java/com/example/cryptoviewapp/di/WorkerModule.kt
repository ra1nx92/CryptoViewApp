package com.example.cryptoviewapp.di

import com.example.cryptoviewapp.data.workers.RefreshDataWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val workerModule = module {
    worker { RefreshDataWorker(get(), get(), get(), androidContext(), get()) }
}

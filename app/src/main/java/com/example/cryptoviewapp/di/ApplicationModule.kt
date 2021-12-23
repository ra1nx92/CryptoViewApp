package com.example.cryptoviewapp.di

import android.content.Context
import com.example.cryptoviewapp.data.database.AppDatabase
import com.example.cryptoviewapp.data.network.ApiFact
import com.example.cryptoviewapp.data.network.ApiService
import org.koin.dsl.module

val applicationModule = module {
    fun provideApi():ApiService{
        return ApiFact.apiService
    }
    fun provideDataBase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}
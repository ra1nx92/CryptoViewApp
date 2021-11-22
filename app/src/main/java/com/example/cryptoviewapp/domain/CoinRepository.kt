package com.example.cryptoviewapp.domain

import androidx.lifecycle.LiveData
//интерфейс репозитория c Use case
//Use case - какая то одна операция бизнес логики которую может вызвать пользователь
//Use case зависят от интерфейса репозитория, а не от конкретной реализации
interface CoinRepository {
    fun getCoinInfoList(): LiveData<List<CoinInfo>>
    fun getCoinInfo(fromSymbol:String):LiveData<CoinInfo>
    fun loadData()
}
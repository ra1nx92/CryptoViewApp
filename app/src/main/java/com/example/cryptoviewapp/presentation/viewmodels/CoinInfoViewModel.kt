package com.example.cryptoviewapp.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptoviewapp.data.network.ApiFact
import com.example.cryptoviewapp.data.database.AppDatabase
import com.example.cryptoviewapp.data.network.model.CoinInfoDto
import com.example.cryptoviewapp.data.network.model.CoinInfoJsonContainerDto
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinInfoViewModel (application: Application): AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriseInfoDao().getPriseList()

    init {
        loadData()
    }

    fun detalInfo(fSym: String): LiveData<CoinInfoDto> {
        return db.coinPriseInfoDao().getPriseInfoAboutCoin(fSym)
    }

    private fun loadData() {
        val disposable = ApiFact.apiService.getTopCoinsInfo(limit = 20)
            .map { it -> it.names?.map { it.coinName?.name }?.joinToString(",") }
            .flatMap { ApiFact.apiService.getFullPriceList(fSyms = it) }
            .map { getPriseListFromRowData(it) }
            .delaySubscription(1, TimeUnit.MINUTES)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinPriseInfoDao().insertPriseList(it)
                Log.d("TEST_OF_LOAD", it.toString())
            }, {
                Log.d("TEST_OF_LOAD", "EX")
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriseListFromRowData(coinInfoJsonContainerDto: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = ArrayList<CoinInfoDto>()
        val jsonObj = coinInfoJsonContainerDto.json ?: return result
        val coinKeySet = jsonObj.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObj.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priseInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priseInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
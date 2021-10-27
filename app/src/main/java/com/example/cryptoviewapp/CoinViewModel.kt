package com.example.cryptoviewapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptoviewapp.api.ApiFact
import com.example.cryptoviewapp.database.AppDatabase
import com.example.cryptoviewapp.pojo.CoinPriseInfo
import com.example.cryptoviewapp.pojo.CoinPriseInfoRawData
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriseInfoDao().getPriseList()
    init {
        loadData()
    }

    fun detalInfo(fSym:String):LiveData<CoinPriseInfo>{
        return db.coinPriseInfoDao().getPriseInfoAboutCoin(fSym)
    }

    private fun loadData() {
        val disposable = ApiFact.apiService.getTopCoinsInfo(limit = 20)
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
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

    private fun getPriseListFromRowData(coinPriseInfoRawData: CoinPriseInfoRawData): List<CoinPriseInfo> {
        val result = ArrayList<CoinPriseInfo>()
        val jsonObj = coinPriseInfoRawData.coinPriseInfoJsonObj ?: return result
        val coinKeySet = jsonObj.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObj.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priseInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriseInfo::class.java
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
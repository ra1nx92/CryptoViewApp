package com.example.cryptoviewapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.cryptoviewapp.api.ApiFact
import com.example.cryptoviewapp.database.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinViewModel(application: Application):AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriseInfoDao().getPriseList()

    fun loadData(){
        val disposable = ApiFact.apiService.getTopCoinInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("qwer", it.toString())
            },{
                Log.d("qwer", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
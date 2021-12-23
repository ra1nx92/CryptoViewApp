package com.example.cryptoviewapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoviewapp.data.database.CoinInfoDao
import com.example.cryptoviewapp.data.mapper.CoinMappers
import com.example.cryptoviewapp.data.workers.RefreshDataWorker
import com.example.cryptoviewapp.domain.CoinInfo
import com.example.cryptoviewapp.domain.CoinRepository

class CoinRepositoryImpl(
    private val context: Context,
    private val mapper:CoinMappers,
    private val coinInfoDao:CoinInfoDao
):CoinRepository {

        //метод будет возвращать сразу обьект LD содержащий коллекцию обьектов из Domain слоя
    override fun getCoinInfoList(): LiveData<List<CoinInfo  >> {
        //для того чтобы мапить обьекты LD, необходимо использовать Transformations.map
        return Transformations.map( coinInfoDao.getPriseList()){ it ->
            it.map {
               mapper.mapDbModelToEntity(it)
           }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
       return Transformations.map(coinInfoDao.getPriseInfoAboutCoin(fromSymbol)){
           mapper.mapDbModelToEntity(it)
       }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(context)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.WORK_NAME, //передаем имя
            ExistingWorkPolicy.REPLACE, //REPLACE - старый воркер будет заменен на новый, если был уже запущен
            RefreshDataWorker.makeRequest()
        )
    }
}
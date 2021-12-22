package com.example.cryptoviewapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoviewapp.data.database.AppDatabase
import com.example.cryptoviewapp.data.mapper.CoinMapper
import com.example.cryptoviewapp.data.workers.RefreshDataWorker
import com.example.cryptoviewapp.domain.CoinInfo
import com.example.cryptoviewapp.domain.CoinRepository

class CoinRepositoryImpl(private val application: Application):CoinRepository {

    //создаем ссылку на интерфейс Dao
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriseInfoDao()
    //мапер для преобразования обьектов в необходимые типы
    private val mapper = CoinMapper()

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
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.WORK_NAME, //передаем имя
            ExistingWorkPolicy.REPLACE, //REPLACE - старый воркер будет заменен на новый, если был уже запущен
            RefreshDataWorker.makeRequest()
        )
    }
}
package com.example.cryptoviewapp.data.workers

import android.content.Context
import androidx.work.*
import com.example.cryptoviewapp.data.database.AppDatabase
import com.example.cryptoviewapp.data.mapper.CoinMapper
import com.example.cryptoviewapp.data.network.ApiFact
import kotlinx.coroutines.delay

class RefreshDataWorker(context: Context,workerParameters: WorkerParameters)
    //чтобы работать с корутинами, наследуемся от CoroutineWorker вместо обычного Worker
    :CoroutineWorker(context,workerParameters) {

    //создаем ссылку на интерфейс Dao
    private val coinInfoDao = AppDatabase.getInstance(context).coinPriseInfoDao()
    //мапер для преобразования обьектов в необходимые типы
    private val mapper = CoinMapper()
    //для загрузки данных
    private val apiService = ApiFact.apiService

    override suspend fun doWork(): Result {
        while (true) {
            //на случай если пропадет связь с интернетом, т.к если не обработать исключение, будет краш
            //это самая простая обработка исключений при работе с корутинами, просто обернуть в try\catch
            try {
                val topCoins =
                    apiService.getTopCoinsInfo(limit = 20) //получаем топ самых популярных валют
                val fSyms = mapper.mapNamesListToString(topCoins) //преобразуем валюты в 1 строку
                val jsonContainer =
                    apiService.getFullPriceList(fSyms = fSyms)//получаем полный прайс лист
                val coinInfoDto =
                    mapper.mapJsonContainerToListCoinInfo(jsonContainer)//преобразуем json контейнер в колекцию обьектов Dto
                val dbModelList =
                    coinInfoDto.map { mapper.mapDtoToDbModel(it) }//преобразуем Dto в обьекты для базы данных
                coinInfoDao.insertPriseList(dbModelList)//кладем в базу
            } catch (e: Exception) {
            }
            delay(30000)// цикл будет обновлять данные каждых 30 секунд
        }
    }

    companion object{
        const val WORK_NAME = "load_data"

        fun makeRequest():OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}
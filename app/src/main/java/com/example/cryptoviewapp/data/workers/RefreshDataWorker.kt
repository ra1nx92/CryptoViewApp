package com.example.cryptoviewapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.cryptoviewapp.data.database.CoinInfoDao
import com.example.cryptoviewapp.data.mapper.CoinMappers
import com.example.cryptoviewapp.data.network.ApiService
import kotlinx.coroutines.delay

class RefreshDataWorker(
    private val apiService: ApiService,
    private val coinInfoDao: CoinInfoDao,
    private val mapper: CoinMappers,
    context: Context,
    workerParameters: WorkerParameters,
    )
//чтобы работать с корутинами, наследуемся от CoroutineWorker вместо обычного Worker
    : CoroutineWorker(context, workerParameters){

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
            delay(15000)// цикл будет обновлять данные каждых 15 секунд
        }
    }

    companion object {
        const val WORK_NAME = "load_data"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}
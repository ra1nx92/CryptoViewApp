package com.example.cryptoviewapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cryptoviewapp.data.database.AppDatabase
import com.example.cryptoviewapp.data.mapper.CoinMapper
import com.example.cryptoviewapp.data.network.ApiFact
import com.example.cryptoviewapp.domain.CoinInfo
import com.example.cryptoviewapp.domain.CoinRepository

class CoinRepositoryImpl(private val application: Application):CoinRepository {

    //создаем ссылку на интерфейс Dao
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriseInfoDao()
    //мапер для преобразования обьектов в необходимые типы
    private val mapper = CoinMapper()
    //для загрузки данных
    private val apiService = ApiFact.apiService

        //метод будет возвращать сразу обьект LD содержащий коллекцию обьектов из Domain слоя
    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
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

    override suspend fun loadData() {
        val topCoins = ApiFact.apiService.getTopCoinsInfo(limit = 20)
        val fSyms = mapper.mapNamesListToString(topCoins)
        val jsonContainer = ApiFact.apiService.getFullPriceList(fSyms = fSyms)
        val coinInfoDto = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
        val dbModelList = coinInfoDto.map { mapper.mapDtoToDbModel(it) }
    }
}
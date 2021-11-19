package com.example.cryptoviewapp.data.mapper

import com.example.cryptoviewapp.data.database.CoinInfoDbModel
import com.example.cryptoviewapp.data.network.model.CoinInfoDto
import com.example.cryptoviewapp.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoviewapp.domain.CoinInfo
import com.google.gson.Gson

//класс содержит методы для преобразования обьектов domain слоя в модель БД, это необходимо для
//сохранения принципа чистой архитектуры, domain слой ничего не должен знать о data слое,
//data слой зависит от domain
class CoinMapper {
    //метод преобразует сущность domain слоя в data слой
    fun mapDtoToDbModel(coinInfoDto: CoinInfo): CoinInfoDbModel {
        return CoinInfoDbModel(
            fromsymbol = coinInfoDto.fromsymbol,
            tosymbol = coinInfoDto.tosymbol,
            price = coinInfoDto.price,
            lastupdate = coinInfoDto.lastupdate,
            highday = coinInfoDto.highday,
            lowday = coinInfoDto.lowday,
            lastmarket = coinInfoDto.lastmarket,
            imageurl = coinInfoDto.imageurl
        )
    }

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto):List<CoinInfoDto>{
        val result = mutableListOf<CoinInfoDto>()//создается пустая коллекция
        val jsonObj = jsonContainer.json ?: return result //проверяем, если придет null, возвращаем пустую коллекцию
        val coinKeySet = jsonObj.keySet()//получаем набор ключей у json обьекта
        for (coinKey in coinKeySet) { //проходимся по всем ключам
            val currencyJson = jsonObj.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priseInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java //получаем обьект CoinInfoDto, и добавляем его в коллекцию
                )
                result.add(priseInfo)
            }
        }
        return result //возвращаем результат, коллекцию со всеми данными
    }
}
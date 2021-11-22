package com.example.cryptoviewapp.data.mapper

import com.example.cryptoviewapp.data.database.CoinInfoDbModel
import com.example.cryptoviewapp.data.network.model.CoinInfoDto
import com.example.cryptoviewapp.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoviewapp.data.network.model.CoinsNameListDto
import com.example.cryptoviewapp.domain.CoinInfo
import com.google.gson.Gson

//класс содержит методы для преобразования обьектов domain слоя в модель БД, это необходимо для
//сохранения принципа чистой архитектуры, domain слой ничего не должен знать о data слое,
//data слой зависит от domain
class CoinMapper {
    //метод преобразует сущность domain слоя в data слой
    fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel = CoinInfoDbModel(
        fromsymbol = dto.fromsymbol,
        tosymbol = dto.tosymbol,
        price = dto.price,
        lastupdate = dto.lastupdate,
        highday = dto.highday,
        lowday = dto.lowday,
        lastmarket = dto.lastmarket,
        imageurl = dto.imageurl ?: ""
    )

    //преобразование json обьекта в коллекцию обьектов CoinInfoDto
    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()//создается пустая коллекция
        val jsonObj = jsonContainer.json
            ?: return result //проверяем, если придет null, возвращаем пустую коллекцию
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

    //на вход приходит контейнер у него одна переменная, в которой лежит список CoinNameContainerDto
    //внутри мапера получаем эту коллекцию, и дальше каждый обьект этой коллекции преобразуем в строку
    fun mapNamesListToString(nameListDto: CoinsNameListDto): String {
        return nameListDto.names?.map {
            it.coinName?.name //из контейнера получаем значение coinName, и из него получаем значение имени
        }?.joinToString(",") ?: "" //всю коллекцию строк соеденяем в одну строку через запятую.
        //если nameListDto.names? придет null, вернется пустая строка
    }

    //преобразуем обьект базы данных в обьект домэйн слоя, для работы в презентейшн слое
    fun mapDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo {
        return CoinInfo(
            fromsymbol = dbModel.fromsymbol,
            tosymbol = dbModel.tosymbol,
            price = dbModel.price,
            lastupdate = dbModel.lastupdate,
            highday = dbModel.highday,
            lowday = dbModel.lowday,
            lastmarket = dbModel.lastmarket,
            imageurl = dbModel.imageurl
        )
    }
}
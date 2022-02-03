package com.example.cryptoviewapp.data.mapper

import com.example.cryptoviewapp.data.database.CoinInfoDbModel
import com.example.cryptoviewapp.data.network.model.CoinInfoDto
import com.example.cryptoviewapp.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoviewapp.data.network.model.CoinsNameListDto
import com.example.cryptoviewapp.domain.CoinInfo
import com.google.gson.Gson
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

interface CoinMappers {
    fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel
    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto>
    fun mapNamesListToString(nameListDto: CoinsNameListDto): String
    fun mapDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo
    fun convertTime(time: Long?): String

    class Base @Inject constructor() : CoinMappers {
        override fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel {
            return CoinInfoDbModel(
                fromsymbol = dto.fromsymbol,
                tosymbol = dto.tosymbol,
                price = dto.price,
                lastupdate = dto.lastupdate,
                highday = dto.highday,
                lowday = dto.lowday,
                lastmarket = dto.lastmarket,
                imageurl = BASE_IMAGE_URL + dto.imageurl
            )
        }

        override fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
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

        override fun mapNamesListToString(nameListDto: CoinsNameListDto): String {
            return nameListDto.names?.map {
                it.coinName?.name //из контейнера получаем значение coinName, и из него получаем значение имени
            }?.joinToString(",") ?: "" //всю коллекцию строк соеденяем в одну строку через запятую.
            //если nameListDto.names? придет null, вернется пустая строка
        }

        override fun mapDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo {
            return CoinInfo(
                fromsymbol = dbModel.fromsymbol,
                tosymbol = dbModel.tosymbol,
                price = dbModel.price,
                lastupdate = convertTime(dbModel.lastupdate),
                highday = dbModel.highday,
                lowday = dbModel.lowday,
                lastmarket = dbModel.lastmarket,
                imageurl = dbModel.imageurl
            )
        }

        override fun convertTime(time: Long?): String {
            if (time == null) return ""
            val stamp = Timestamp(time * 1000)
            val date = Date(stamp.time)
            val pattern = "HH:mm:ss"
            val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            simpleDateFormat.timeZone = TimeZone.getDefault()
            return simpleDateFormat.format(date)
        }

        companion object {
            const val BASE_IMAGE_URL: String = "https://cryptocompare.com"
        }
    }
}
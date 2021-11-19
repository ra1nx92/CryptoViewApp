package com.example.cryptoviewapp.data.network

import com.example.cryptoviewapp.data.network.model.CoinsNameListDto
import com.example.cryptoviewapp.data.network.model.CoinInfoJsonContainerDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
//Аннотация GET означает, что HTTP-запрос для top/totalvolfull должен быть типа GET. А в качестве
// параметра для этой аннотации нам необходимо указать имя метода на сервере.
// здесь не фигурирует базовый URL. Его будем указывать позже, в билдере.
interface ApiService {
    //получаем список самых популярных криптовалют
    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        //получаем необходимые параметры
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ):CoinsNameListDto
//загружаем полную информацию
    @GET("pricemultifull")
    suspend fun getFullPriceList(
    //получаем параметры
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ):CoinInfoJsonContainerDto

    companion object {
        //параметры для связи с сервером
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}
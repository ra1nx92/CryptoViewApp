package com.example.cryptoviewapp.api

import com.example.cryptoviewapp.pojo.CoinListOfData
import com.example.cryptoviewapp.pojo.CoinPriseInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    fun getTopCoinInfo(
        @Query(QUERY_PARAM_TO_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 0,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): Single<CoinListOfData>

    @GET("pricemultifull")
    fun getFullPriseList(
        @Query(QUERY_PARAM_TO_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String
    ): Single<CoinPriseInfoRawData>


    companion object {
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tSym"
        private const val QUERY_PARAM_TO_API_KEY = "api_key"
        private const val QUERY_PARAM_TO_SYMBOLS = "tSyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fSyms"

        private const val CURRENCY = "USD"
    }
}
package com.example.cryptoviewapp.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriseInfoRawData(
    @SerializedName("RAW")
    @Expose
    val coinPriseInfoJsonObj: JsonObject? = null
)

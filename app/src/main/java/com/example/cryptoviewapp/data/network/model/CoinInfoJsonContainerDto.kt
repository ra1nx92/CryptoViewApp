package com.example.cryptoviewapp.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//класс содержит JsonObject с информацией о валюте
data class CoinInfoJsonContainerDto(
    @SerializedName("RAW")//по ключу получаем JsonObject
    @Expose
    val json: JsonObject? = null
)

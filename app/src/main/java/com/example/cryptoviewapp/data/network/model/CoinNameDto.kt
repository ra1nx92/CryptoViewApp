package com.example.cryptoviewapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//обькт с информацией о валюте
data class CoinNameDto(
    @SerializedName("Name")
    @Expose
    val name: String? = null
)
package com.example.cryptoviewapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//класс содержит коллекцию имен, каждое название валюты заключено в контейнер
data class CoinsNameListDto(
    @SerializedName("Data")//по данному ключу придет массив обьектов CoinNameContainerDto
    @Expose
    val names: List<CoinNameContainerDto>? = null
)
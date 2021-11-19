package com.example.cryptoviewapp.domain

//слой domain - слой в котором содержится вся бизнес логика приложения
//слой domain не зависит от других слоев
//для каждого метода бизнес логики создается отдельник класс
data class CoinInfo(
    val fromsymbol: String,
    val tosymbol: String?,
    val price: Double?,
    val lastupdate: Long?,
    val highday: Double?,
    val lowday: Double?,
    val lastmarket: String?,
    val imageurl: String
) {

}
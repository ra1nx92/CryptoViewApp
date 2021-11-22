package com.example.cryptoviewapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//
object ApiFact {

    private const val BASE_URL: String = "https://min-api.cryptocompare.com/data/"
    const val BASE_IMAGE_URL: String = "https://cryptocompare.com"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())//передаем конвертер Json
        .baseUrl(BASE_URL)//передаем адрес сервера где будем брать данные
        .build()

    val apiService = retrofit.create(ApiService::class.java)//передаем интерфейс с методами
}
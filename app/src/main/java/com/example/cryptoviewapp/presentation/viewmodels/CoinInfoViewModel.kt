package com.example.cryptoviewapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoviewapp.data.repository.CoinRepositoryImpl
import com.example.cryptoviewapp.domain.GetCoinInfoListUseCase
import com.example.cryptoviewapp.domain.GetCoinInfoUseCase
import com.example.cryptoviewapp.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinInfoViewModel (application: Application): AndroidViewModel(application) {
    //так делать нельзя, необходимо использовать DI
    private val repository = CoinRepositoryImpl(application)
    //добавляем юзкейсы
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()
    init {
            loadDataUseCase()
    }

    fun detailInfo(fSym: String) = getCoinInfoUseCase(fSym)
}
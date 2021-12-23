package com.example.cryptoviewapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.cryptoviewapp.domain.GetCoinInfoListUseCase
import com.example.cryptoviewapp.domain.GetCoinInfoUseCase
import com.example.cryptoviewapp.domain.GetLoadDataUseCase

class CoinInfoViewModel (
    getCoinInfoListUseCase:GetCoinInfoListUseCase,
    private val getCoinInfoUseCase:GetCoinInfoUseCase,
    getLoadDataUseCase:GetLoadDataUseCase
        ): ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()
    init {
            getLoadDataUseCase()
    }

    fun detailInfo(fSym: String) = getCoinInfoUseCase.invoke(fSym)
}
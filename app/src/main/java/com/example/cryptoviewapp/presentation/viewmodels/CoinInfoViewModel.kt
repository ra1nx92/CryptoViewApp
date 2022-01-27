package com.example.cryptoviewapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.cryptoviewapp.domain.GetCoinInfoListUseCase
import com.example.cryptoviewapp.domain.GetCoinInfoUseCase
import com.example.cryptoviewapp.domain.GetLoadDataUseCase
import javax.inject.Inject

class CoinInfoViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val getLoadDataUseCase: GetLoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    init {
        getLoadDataUseCase()
    }

    fun detailInfo(fSym: String) = getCoinInfoUseCase.invoke(fSym)
}
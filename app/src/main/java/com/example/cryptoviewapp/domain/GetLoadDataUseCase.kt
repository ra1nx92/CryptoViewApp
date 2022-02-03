package com.example.cryptoviewapp.domain

import javax.inject.Inject

class GetLoadDataUseCase @Inject constructor(private val repository: CoinRepository) {
   operator fun invoke() = repository.loadData()
}
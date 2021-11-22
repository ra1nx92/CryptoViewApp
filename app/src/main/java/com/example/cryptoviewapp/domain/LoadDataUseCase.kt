package com.example.cryptoviewapp.domain

class LoadDataUseCase(private val repository: CoinRepository) {
   operator fun invoke() = repository.loadData()
}
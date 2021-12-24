package com.example.cryptoviewapp.domain

class GetLoadDataUseCase(private val repository: CoinRepository) {
   operator fun invoke() = repository.loadData()
}
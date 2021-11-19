package com.example.cryptoviewapp.domain

//operator fun invoke можно применить если класс делает что то одно и нужно создать функцию
//с таким же названием как имя класса
//(operator fun invoke() == fun GetCoinInfoUseCase())
class GetCoinInfoUseCase(private val repository: CoinRepository) {
    operator fun invoke(fromSymbol:String) = repository.getCoinInfo(fromSymbol)
}
//для каждого метода бизнес логики создается отдельник класс
// первый принцип SOLID - принцип единой ответственнсти
//Use case - какая то одна операция бизнес логики которую может вызвать пользователь
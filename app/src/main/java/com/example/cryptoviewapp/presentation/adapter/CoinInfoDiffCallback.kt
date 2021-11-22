package com.example.cryptoviewapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoviewapp.domain.CoinInfo

//этот класс показывает как сравнивать обьекты. Так же можно делать это через DiffUtils, но с
// ListAdapter работать проще, и он работает в отдельном потоке
class CoinInfoDiffCallback():DiffUtil.ItemCallback<CoinInfo>() {
    //первый метод сравнивает один и тот же ли это обьект
    //сюда прилетают уже готовые обьекты, остается только сравнить их
    //т.к fromsymbol используется в качестве ID
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromsymbol == newItem.fromsymbol
    }

//сравниваем обьект чтобы понять изменилось ли его содержимое, и если оно изменилось, его необходимо перерисовать
    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem //так как CoinInfo - data class
        //у него уже переопределен метод equals
    }
}
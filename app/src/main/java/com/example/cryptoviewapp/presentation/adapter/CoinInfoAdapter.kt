package com.example.cryptoviewapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoviewapp.R
import com.example.cryptoviewapp.domain.CoinInfo
import com.example.cryptoviewapp.presentation.fragments.CoinInfoFragment
import com.squareup.picasso.Picasso

//адаптер, необходим для заполнения данными списка RV
//обновление списка реализуется через ListAdapter, с ним проще работать чем в DiffUtils, и он работает в отдельном потоке
class CoinInfoAdapter(private val context: CoinInfoFragment) :
    ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback()) {
    var onCoinClick: onCoinClickListener? = null

    //создание элемента списка, прикрипление макета
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    //запонение элемента макета данными
    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder) {
            val symblos =
                context.resources.getString(R.string.symb_temp) //текстовые ресурсы для формата вывода данных в списке
            val lastUpd = context.resources.getString(R.string.last_upd)
            val priseFormat = context.getString(R.string.price_format)
            binding.tvSymbols.text =
                String.format(symblos, coin.fromsymbol, coin.tosymbol) //название криптовалюты
            binding.tvCoinPrise.text = String.format(priseFormat, coin.price) //цена по курсу обмена
            binding.tvLastUpd.text = String.format(
                lastUpd,
                coin.lastupdate
            ) // время последнего обновления списка

            //библиотека для загрузки изображений из интернета
            Picasso
                .get()
                .load(coin.imageurl)
                .into(binding.ivCoinLogotype)

            //метод клика по элементу списка
            itemView.setOnClickListener {
                onCoinClick?.onCoinClisk(coin)
            }
        }
    }
    //----------------------------------------------------------------------
    //реализация ListAdapter позволяет не переопределять метод getItemCount
    //----------------------------------------------------------------------

    //интерфейс для функции клика по элементу списка
    interface onCoinClickListener {
        fun onCoinClisk(coinPriseInfo: CoinInfo)
    }
}
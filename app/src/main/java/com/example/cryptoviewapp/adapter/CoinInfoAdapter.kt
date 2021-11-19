package com.example.cryptoviewapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoviewapp.R
import com.example.cryptoviewapp.databinding.ItemCoinInfoBinding
import com.example.cryptoviewapp.fragments.CoinInfoFragment
import com.example.cryptoviewapp.pojo.CoinPriseInfo
import com.squareup.picasso.Picasso
//адаптер, необходим для заполнения данными списка RV
class CoinInfoAdapter(private val context: CoinInfoFragment) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    var onCoinClick: onCoinClickListener? = null
    var coinInfoList: List<CoinPriseInfo> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //создание элемента списка, прикрипление макета
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }
    //запонение элемента макета данными
    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            val symblos = context.resources.getString(R.string.symb_temp) //текстовые ресурсы для формата вывода данных в списке
            val lastUpd = context.resources.getString(R.string.last_upd)
            val priseFormat = context.getString(R.string.price_format)
            binding.tvSymbols.text = String.format(symblos, coin.fromsymbol, coin.tosymbol) //название криптовалюты
            binding.tvCoinPrise.text = String.format(priseFormat, coin.price) //цена по курсу обмена
            binding.tvLastUpd.text = String.format(lastUpd, coin.getFormatedTime()) // время последнего обновления списка
            //библиотека для загрузки изображений из интернета
            Picasso
                .get()
                .load(coin.getImageUrl())
                .into(binding.ivCoinLogotype)

                //метод клика по элементу списка
            itemView.setOnClickListener {
                onCoinClick?.onCoinClisk(coin)
            }
        }
    }
        //тут количество элементов в списке, указывается сам список и его метод size
    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCoinInfoBinding.bind(itemView)
    }
    //интерфейс для функции клика по элементу списка
    interface onCoinClickListener {
        fun onCoinClisk(coinPriseInfo: CoinPriseInfo)
    }
}
package com.example.cryptoviewapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoviewapp.R
import com.example.cryptoviewapp.databinding.ItemCoinInfoBinding
import com.example.cryptoviewapp.pojo.CoinPriseInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    var onCoinClick: onCoinClickListener? = null
    var coinInfoList: List<CoinPriseInfo> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            val symblos = context.resources.getString(R.string.symb_temp)
            val lastUpd = context.resources.getString(R.string.last_upd)
            val priseFormat = context.getString(R.string.price_format)
            binding.tvSymbols.text = String.format(symblos, coin.fromsymbol, coin.tosymbol)
            binding.tvCoinPrise.text = String.format(priseFormat, coin.price)
            binding.tvLastUpd.text = String.format(lastUpd, coin.getFormatedTime())
            Picasso
                .get()
                .load(coin.getImageUrl())
                .into(binding.ivCoinLogotype)

            itemView.setOnClickListener {
                onCoinClick?.onCoinClisk(coin)
            }
        }
    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCoinInfoBinding.bind(itemView)
    }

    interface onCoinClickListener {
        fun onCoinClisk(coinPriseInfo: CoinPriseInfo)
    }
}
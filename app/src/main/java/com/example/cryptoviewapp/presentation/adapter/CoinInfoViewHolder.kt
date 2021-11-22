package com.example.cryptoviewapp.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoviewapp.databinding.ItemCoinInfoBinding

//класс-хранилище для View, которые будут использованы в адапере RV
class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemCoinInfoBinding.bind(itemView)
}
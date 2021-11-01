package com.example.cryptoviewapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.cryptoviewapp.R
import com.example.cryptoviewapp.databinding.FragmentDetalInfoCoinBinding
import com.squareup.picasso.Picasso


class DetalInfoCoinFragment : Fragment(R.layout.fragment_detal_info_coin) {
    private val viewModel:CoinInfoViewModel by viewModels()
    private lateinit var binding: FragmentDetalInfoCoinBinding
    private val args:DetalInfoCoinFragmentArgs by navArgs() //для приема данных из первого фрагмента

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetalInfoCoinBinding.bind(view)
        //принимаем данные args.coinPriseInfo
        viewModel.detalInfo(args.coinPriseInfo).observe(viewLifecycleOwner, Observer {
            //устанавливаем данные
            with(binding){
                tvPrice.text = it.price.toString()
                tvMinPrice.text = it.lowday.toString()
                tvMaxPrice.text = it.highday.toString()
                tvLastMarket.text = it.lastmarket.toString()
                tvLastUpdate.text = it.getFormatedTime()
                tvFromSymbol.text = it.fromsymbol
                tvToSymbol.text = it.tosymbol
                //загрузка изображения
                Picasso.get().load(it.getImageUrl()).into(ivLogoCoin)

            }
        })
    }
}
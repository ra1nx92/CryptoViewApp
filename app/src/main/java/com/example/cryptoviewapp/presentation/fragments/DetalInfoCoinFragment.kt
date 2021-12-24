package com.example.cryptoviewapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.cryptoviewapp.R
import com.example.cryptoviewapp.databinding.FragmentDetalInfoCoinBinding
import com.example.cryptoviewapp.presentation.viewmodels.CoinInfoViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetalInfoCoinFragment : Fragment(R.layout.fragment_detal_info_coin) {
    private val viewModel: CoinInfoViewModel by viewModel()
    private lateinit var binding: FragmentDetalInfoCoinBinding
    private val args:DetalInfoCoinFragmentArgs by navArgs() //для приема данных из первого фрагмента

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetalInfoCoinBinding.bind(view)
        //принимаем данные args.coinPriseInfo
        viewModel.detailInfo(args.coinPriseInfo).observe(viewLifecycleOwner, {
            //устанавливаем данные
            with(binding) {
                tvPrice.text = it.price.toString()
                tvMinPrice.text = it.lowday.toString()
                tvMaxPrice.text = it.highday.toString()
                tvLastMarket.text = it.lastmarket.toString()
                tvLastUpdate.text = it.lastupdate
                tvFromSymbol.text = it.fromsymbol
                tvToSymbol.text = it.tosymbol
                //загрузка изображения
                Picasso.get().load(it.imageurl).into(ivLogoCoin)
            }
        })
    }
}
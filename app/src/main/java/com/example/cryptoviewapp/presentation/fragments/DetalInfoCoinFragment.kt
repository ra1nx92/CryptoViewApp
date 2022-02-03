package com.example.cryptoviewapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.cryptoviewapp.R
import com.example.cryptoviewapp.databinding.FragmentDetalInfoCoinBinding
import com.example.cryptoviewapp.di.CoinApplication
import com.example.cryptoviewapp.presentation.viewmodels.CoinInfoViewModel
import com.example.cryptoviewapp.presentation.viewmodels.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject


class DetalInfoCoinFragment : Fragment(R.layout.fragment_detal_info_coin) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: CoinInfoViewModel
    private lateinit var binding: FragmentDetalInfoCoinBinding
    private val args: DetalInfoCoinFragmentArgs by navArgs() //для приема данных из первого фрагмента
    private val component by lazy {
        //у фрагмента нет апликейшн, получаем его из активити
        (requireActivity().application as CoinApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetalInfoCoinBinding.bind(view)
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinInfoViewModel::class.java]
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
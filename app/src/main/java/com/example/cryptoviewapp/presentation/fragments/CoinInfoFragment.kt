package com.example.cryptoviewapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cryptoviewapp.R
import com.example.cryptoviewapp.presentation.adapter.CoinInfoAdapter
import com.example.cryptoviewapp.databinding.CoinInfoFragmentBinding
import com.example.cryptoviewapp.data.network.model.CoinInfoDto
import com.example.cryptoviewapp.presentation.viewmodels.CoinInfoViewModel

class CoinInfoFragment : Fragment(R.layout.coin_info_fragment) {
    private val viewModel: CoinInfoViewModel by viewModels()
    private lateinit var binding: CoinInfoFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CoinInfoFragmentBinding.bind(view)

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClick = object : CoinInfoAdapter.onCoinClickListener {
            override fun onCoinClisk(coinPriseInfo: CoinInfoDto) {
//в графе навигации устанавливаем аргумент coinPriseInfo типа string, в фрагменте который будет принимать данные
                findNavController().navigate(CoinInfoFragmentDirections
                    .actionCoinInfoFragmentToDetalInfoCoinFragment(coinPriseInfo.fromsymbol))
            }
        }
        binding.rvCoinInfoList.adapter = adapter

        viewModel.priceList.observe(viewLifecycleOwner, {
            adapter.coinInfoList = it
        })
    }
}
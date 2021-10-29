package com.example.cryptoviewapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cryptoviewapp.R
import com.example.cryptoviewapp.adapter.CoinInfoAdapter
import com.example.cryptoviewapp.databinding.CoinInfoFragmentBinding
import com.example.cryptoviewapp.pojo.CoinPriseInfo

class CoinInfoFragment : Fragment(R.layout.coin_info_fragment) {
    private val viewModel: CoinInfoViewModel by viewModels()
    private lateinit var binding: CoinInfoFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CoinInfoFragmentBinding.bind(view)

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClick = object : CoinInfoAdapter.onCoinClickListener {
            override fun onCoinClisk(coinPriseInfo: CoinPriseInfo) {
                findNavController().navigate(CoinInfoFragmentDirections
                    .actionCoinInfoFragmentToDetalInfoCoinFragment())
            }
        }
        binding.rvCoinInfoList.adapter = adapter

        viewModel.priceList.observe(viewLifecycleOwner, {
            adapter.coinInfoList = it
        })
    }
}
package com.example.cryptoviewapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cryptoviewapp.R
import com.example.cryptoviewapp.databinding.FragmentDetalInfoCoinBinding


class DetalInfoCoinFragment : Fragment(R.layout.fragment_detal_info_coin) {
    private val viewModel:CoinInfoViewModel by viewModels()
    private lateinit var binding: FragmentDetalInfoCoinBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetalInfoCoinBinding.bind(view)

        viewModel
    }
}
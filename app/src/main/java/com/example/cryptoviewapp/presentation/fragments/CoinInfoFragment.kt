package com.example.cryptoviewapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.cryptoviewapp.R
import com.example.cryptoviewapp.presentation.adapter.CoinInfoAdapter
import com.example.cryptoviewapp.databinding.CoinInfoFragmentBinding
import com.example.cryptoviewapp.domain.CoinInfo
import com.example.cryptoviewapp.presentation.viewmodels.CoinInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CoinInfoFragment : Fragment(R.layout.coin_info_fragment) {
    private val viewModel: CoinInfoViewModel by viewModel()
    private lateinit var binding: CoinInfoFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CoinInfoFragmentBinding.bind(view)

        val adapter = CoinInfoAdapter(this)
        //анонимная функция обработки клика по элементу списка
        adapter.onCoinClick = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriseInfo: CoinInfo) {
//в графе навигации устанавливаем аргумент coinPriseInfo типа string, в фрагменте который будет принимать данные
                    findNavController().navigate(
                        CoinInfoFragmentDirections
                            .actionCoinInfoFragmentToDetalInfoCoinFragment(coinPriseInfo.fromsymbol)
                    )
            }
        }
        binding.rvCoinInfoList.adapter = adapter

        viewModel.coinInfoList.observe(viewLifecycleOwner, {
            adapter.submitList(it) //реализация с ListAdapter
            //при вызове метода submitList запускается новый поток
            // в котором проходят все вычисления, после которых список RV обновляется
        })
    }
}
package com.example.cryptoviewapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.cryptoviewapp.R
import com.example.cryptoviewapp.databinding.FragmentDetalInfoCoinBinding
import com.example.cryptoviewapp.presentation.viewmodels.CoinInfoViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
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
            with(binding){
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

        val barEntry = arrayListOf<BarEntry>()
        val arabaModel = arrayOf("qwer","qwer2","qwer3","qwer4")

        barEntry.add(BarEntry(0f,50.16f))
        barEntry.add(BarEntry(1f,32.16f))
        barEntry.add(BarEntry(2f,22.16f))
        barEntry.add(BarEntry(3f,12.16f))

        val barDataSet = BarDataSet(barEntry,"AAAAAAAAA")
        barDataSet.valueTextSize = 15f
        barDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)

        val barData = BarData(barDataSet)
        binding.barChart.animateY(1500)
        binding.barChart.setFitBars(true)
        binding.barChart.data = barData
        binding.barChart.description.text = "QQQQQQQQQQQ"
        binding.barChart.xAxis.valueFormatter = IndexAxisValueFormatter(arabaModel)
        binding.barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    }
}
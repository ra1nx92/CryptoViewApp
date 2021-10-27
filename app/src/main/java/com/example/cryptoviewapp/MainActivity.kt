package com.example.cryptoviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoviewapp.adapter.CoinInfoAdapter
import com.example.cryptoviewapp.databinding.ActivityMainBinding
import com.example.cryptoviewapp.pojo.CoinPriseInfo

class MainActivity : AppCompatActivity() {
    private val viewModel: CoinViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClick = object : CoinInfoAdapter.onCoinClickListener {
            override fun onCoinClisk(coinPriseInfo: CoinPriseInfo) {
                Log.d("TEST_OF_LOAD", coinPriseInfo.fromsymbol)
            }
        }
        binding.rvCoinPriseList.adapter = adapter

        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })
    }
}
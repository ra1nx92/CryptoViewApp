package com.example.cryptoviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(CoinViewModel::class.java)
//        viewModel.priceList.observe(this, Observer {
//            Log.d("TEST_OF_LOAD", "success in activity:  $it")
//        })
        viewModel.detalInfo("BTC").observe(this, Observer {
            Log.d("TEST_OF_LOAD", "success in activity BTC:  $it")
        })
    }
}
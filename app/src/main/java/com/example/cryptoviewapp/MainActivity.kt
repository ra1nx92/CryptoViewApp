package com.example.cryptoviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.cryptoviewapp.adapter.CoinInfoAdapter
import com.example.cryptoviewapp.databinding.ActivityMainBinding
import com.example.cryptoviewapp.pojo.CoinPriseInfo

class MainActivity : AppCompatActivity() {
    // достучаться до тулбара, в котором будет устанавливаться название текущего фрагмента
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // достаем навигационный контроллер
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController
        NavigationUI.setupActionBarWithNavController(this, navController)


    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
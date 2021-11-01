package com.example.cryptoviewapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptoviewapp.pojo.CoinPriseInfo

@Dao
interface CoinPriseInfoDao {
    @Query("SELECT * FROM full_prise_list ORDER BY price DESC")
    fun getPriseList():LiveData<List<CoinPriseInfo>>

    @Query("SELECT * FROM full_prise_list WHERE fromsymbol == :fSym LIMIT 1")
    fun getPriseInfoAboutCoin(fSym:String):LiveData<CoinPriseInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriseList(priseList:List<CoinPriseInfo>)
}
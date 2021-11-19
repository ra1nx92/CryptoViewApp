package com.example.cryptoviewapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinInfoDao {
    @Query("SELECT * FROM full_prise_list ORDER BY price DESC")
    fun getPriseList():LiveData<List<CoinInfoDbModel>>

    @Query("SELECT * FROM full_prise_list WHERE fromsymbol == :fSym LIMIT 1")
    fun getPriseInfoAboutCoin(fSym:String):LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriseList(priseList:List<CoinInfoDbModel>)
}
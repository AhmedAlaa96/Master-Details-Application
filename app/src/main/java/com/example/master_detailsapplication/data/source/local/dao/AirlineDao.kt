package com.example.master_detailsapplication.data.source.local.dao

import androidx.room.*
import com.example.master_detailsapplication.domain.models.Airline

@Dao
interface AirlineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(airline: Airline): Long

    @Query("SELECT * FROM Airline")
    fun loadAll(): MutableList<Airline>

    @Query("DELETE FROM Airline")
    fun deleteAll(): Int

}
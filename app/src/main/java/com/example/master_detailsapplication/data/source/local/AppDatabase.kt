package com.example.master_detailsapplication.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.master_detailsapplication.data.source.local.dao.AirlineDao
import com.example.master_detailsapplication.domain.models.Airline

/**
 * To manage data items that can be accessed, updated
 * & maintain relationships between them
 *
 * @Created by ZARA
 */
@Database(entities = [Airline::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val airlineDao: AirlineDao

    companion object {
        private const val DB_NAME = "Airlines.db"
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context) : AppDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, DB_NAME
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}

package com.attia.wazaker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DayHistory::class, Azkaar::class], version = 1, exportSchema = false)
abstract class WazakerDatabase : RoomDatabase() {

    abstract val historyDatabaseDao: HistoryDatabaseDao
    abstract val AzkaarDatabaseDao: AzkaarDatabaseDao


    companion object {

        @Volatile
        private var INSTANCE: WazakerDatabase? = null

        fun getInstance(context: Context) : WazakerDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WazakerDatabase::class.java,
                        "Wazaker_Database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }

        }

    }
}
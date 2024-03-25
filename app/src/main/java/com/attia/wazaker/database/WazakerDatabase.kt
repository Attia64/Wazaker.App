package com.attia.wazaker.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AzkaarHistory::class, Azkaar::class], version = 2, exportSchema = false)
abstract class WazakerDatabase : RoomDatabase() {

    abstract val historyDatabaseDao: HistoryDatabaseDao
    abstract val azkaarDatabaseDao: AzkaarDatabaseDao

}
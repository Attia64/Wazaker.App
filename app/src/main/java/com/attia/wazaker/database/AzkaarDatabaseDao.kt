package com.attia.wazaker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface AzkaarDatabaseDao {

    @Upsert
    suspend fun upsert(newZekr: Azkaar)

    @Query("SELECT * FROM Azkaar_Table ORDER BY zekrId ASC")
    fun getAll(): LiveData<List<Azkaar>>

    @Delete
    suspend fun deletezekr(zekr: Azkaar)

}
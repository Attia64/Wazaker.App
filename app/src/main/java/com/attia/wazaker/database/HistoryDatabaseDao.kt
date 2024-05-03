package com.attia.wazaker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface HistoryDatabaseDao {

    @Upsert
    suspend fun upsertHistoryField(day: AzkaarHistory)

    @Query("SELECT * FROM Azkaar_History_Table ORDER BY zekrId ASC")
    fun getAll(): LiveData<List<AzkaarHistory>>

    @Delete
    suspend fun delete(element: AzkaarHistory)

    @Query("DELETE FROM Azkaar_History_Table")
    suspend fun clear()
}
package com.attia.wazaker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import java.time.LocalDateTime

@Dao
interface HistoryDatabaseDao {

    @Upsert
    fun update(day: DayHistory)

    @Delete
    fun deleteDay(day: DayHistory)

    @Query("DELETE FROM Azkaar_History_Table")
    fun clear()

    @Query("SELECT * FROM Azkaar_History_Table WHERE Local_Date_Time = :day ORDER BY Local_Date_Time ASC")
    fun getDay(day: Long): LiveData<List<DayHistory>>

    @Query("SELECT * FROM Azkaar_History_Table ORDER BY Local_Date_Time ASC LIMIT 30")
    fun getAll(): LiveData<List<DayHistory>>
}
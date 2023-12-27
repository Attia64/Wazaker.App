package com.attia.wazaker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface AzkaarDatabaseDao {

    @Upsert
    fun upsert(newZekr: Azkaar)

    @Query("SELECT * FROM Azkaar_Table ORDER BY zekrId ASC")
    fun getAll(): LiveData<List<Azkaar>>

    @Query("SELECT * FROM Azkaar_Table ORDER BY zekrId DESC LIMIT 1")
    fun getZekr(): Azkaar?

    @Delete
    fun deletezekr(zekr: Azkaar)

    @Query("DELETE FROM AZKAAR_TABLE")
    fun clear()


}
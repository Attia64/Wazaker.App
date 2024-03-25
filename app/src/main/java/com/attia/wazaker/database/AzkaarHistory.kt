package com.attia.wazaker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Azkaar_History_Table")
data class AzkaarHistory(
    @PrimaryKey(autoGenerate = true)
    var zekrId: Long = 0L,

    @ColumnInfo(name = "Zekr_Name")
    val zekrName: String,

    @ColumnInfo(name = "Local_Date_Time")
    val localDateTime: String,

    @ColumnInfo(name = "Number_Of_Counts")
    val numCount: Int
)
